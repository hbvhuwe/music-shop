package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Checks;
import com.hbvhuwe.musicshop.model.Client;
import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.repository.ChecksRepository;
import com.hbvhuwe.musicshop.repository.ClientRepository;
import com.hbvhuwe.musicshop.repository.CompositionRepository;
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
@RequestMapping(path = "/compositions")
public class CompositionController {
  private final CompositionRepository repository;
  private final ClientRepository clientRepository;
  private final ChecksRepository checksRepository;
  private final DiskRepository diskRepository;

  @Autowired
  public CompositionController(
      CompositionRepository repository,
      ClientRepository clientRepository,
      ChecksRepository checksRepository,
      DiskRepository diskRepository) {
    this.repository = repository;
    this.clientRepository = clientRepository;
    this.checksRepository = checksRepository;
    this.diskRepository = diskRepository;
  }

  @GetMapping(path = "/")
  @ResponseBody
  public Iterable<Composition> findAll() {
    return repository.findAll();
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity<Composition> findById(@PathVariable(name = "id") int id) {
    Optional<Composition> composition = repository.findById(id);
    if (composition.isPresent()) {
      return ResponseEntity.ok(composition.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path = "/library/{id}")
  @ResponseBody
  public ResponseEntity getAllForUser(
      @RequestHeader(value = "Authorization") String auth, @PathVariable(name = "id") int id) {
    String authEncoded = new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    if (id != Integer.parseInt(cred[0])) {
      return ResponseEntity.badRequest()
          .body("{\"status\":\"error\"," + "\"message\":\"Bad request\"}");
    }
    Optional<Client> client =
        clientRepository.findClientByClientIdAndPassword(Integer.parseInt(cred[0]), cred[1]);
    if (client.isPresent()) {
      Iterable<Checks> checks = checksRepository.findAllByClientId(id);
      List<Integer> ids = new ArrayList<>();
      checks.forEach(check -> ids.add(check.getId()));
      List<Disk> disks = diskRepository.findAllById(ids);
      List<Composition> compositions = new ArrayList<>();
      for (Disk disk : disks) {
        compositions.addAll(repository.getAllByDiskId(disk.getDiskId()));
      }
      return ResponseEntity.ok(compositions);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @PutMapping(path = "/add")
  @ResponseBody
  public ResponseEntity<String> add(
      @RequestHeader(value = "Authorization") String auth,
      @RequestParam(name = "Name") String name,
      @RequestParam(name = "Duration") String duration,
      @RequestParam(name = "PresentDate") String presentDate,
      @RequestParam(name = "DiskID") int id) {
    Composition composition = new Composition();
    composition.setName(name);
    composition.setDuration(duration);
    composition.setPresentDate(presentDate);
    composition.setDiskId(id);
    repository.save(composition);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }

  @DeleteMapping(path = "/delete/{id}")
  @ResponseBody
  public ResponseEntity<String> delete(@RequestHeader(value = "Authorization") String auth, @PathVariable(name = "id") int id) {
    repository.deleteById(id);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }
}
