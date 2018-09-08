package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Checks;
import com.hbvhuwe.musicshop.model.Client;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.repository.ChecksRepository;
import com.hbvhuwe.musicshop.repository.ClientRepository;
import com.hbvhuwe.musicshop.repository.DiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/disks")
public class DiskController {
  private final DiskRepository repository;
  private final ClientRepository clientRepository;
  private final ChecksRepository checksRepository;

  @Autowired
  public DiskController(
      DiskRepository repository,
      ClientRepository clientRepository,
      ChecksRepository checksRepository) {
    this.repository = repository;
    this.clientRepository = clientRepository;
    this.checksRepository = checksRepository;
  }

  @GetMapping(path = "/library/{id}")
  @ResponseBody
  public ResponseEntity getLibrary(
      @RequestHeader(value = "Authorization", required = false) String auth,
      @PathVariable(name = "id") int id) {
    if (auth != null && !auth.isEmpty()) {
      String authEncoded = new String(Base64.getDecoder().decode(auth.getBytes()));
      String[] cred = authEncoded.split(":");
      if (id != Integer.parseInt(cred[0])) {
        return ResponseEntity.badRequest()
            .body("{\"status\":\"error\"," + "\"message\":\"Bad request\"}");
      }
      Optional<Client> client =
          clientRepository.findClientByClientIdAndName(Integer.parseInt(cred[0]), cred[1]);
      if (client.isPresent()) {
        Iterable<Checks> checks = checksRepository.findAllByClientId(id);
        List<Integer> ids = new ArrayList<>();
        checks.forEach(check -> ids.add(check.getId()));
        return ResponseEntity.ok(repository.findAllById(ids));
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @GetMapping(path = "/")
  @ResponseBody
  public ResponseEntity findAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity findById(@PathVariable(name = "id") int id) {
    Optional<Disk> disk = repository.findById(id);
    if (disk.isPresent()) {
      return ResponseEntity.ok(disk.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(path = "/add")
  @ResponseBody
  public ResponseEntity<String> add(
      @RequestParam(name = "Name") String name,
      @RequestParam(name = "Amount") int amount,
      @RequestParam(name = "PresentDate") String presentDate,
      @RequestParam(name = "Price") double price,
      @RequestParam(name = "GroupID") int id) {
    Disk disk = new Disk();
    disk.setName(name);
    disk.setAmount(amount);
    disk.setPresentDate(presentDate);
    disk.setPrice(price);
    disk.setGroupId(id);
    repository.save(disk);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }

  @DeleteMapping(path = "/delete/{id}")
  @ResponseBody
  public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
    repository.deleteById(id);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }
}
