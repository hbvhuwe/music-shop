package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.*;
import com.hbvhuwe.musicshop.repository.*;
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
  private final DiskRepository diskRepository;
  private final ClientRepository clientRepository;
  private final ChecksRepository checksRepository;
  private final CompositionRepository compositionRepository;
  private final AdminRepository adminRepository;

  @Autowired
  public DiskController(
      DiskRepository repository,
      ClientRepository clientRepository,
      ChecksRepository checksRepository,
      CompositionRepository compositionRepository,
      AdminRepository adminRepository) {
    this.diskRepository = repository;
    this.clientRepository = clientRepository;
    this.checksRepository = checksRepository;
    this.compositionRepository = compositionRepository;
    this.adminRepository = adminRepository;
  }

  @GetMapping(path = "/library/{id}")
  @ResponseBody
  public ResponseEntity getLibrary(
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
      return ResponseEntity.ok(diskRepository.findAllById(ids));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @GetMapping(path = "/")
  @ResponseBody
  public ResponseEntity findAll() {
    return ResponseEntity.ok(diskRepository.findAll());
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity findById(@PathVariable(name = "id") int id) {
    Optional<Disk> disk = diskRepository.findById(id);
    if (disk.isPresent()) {
      return ResponseEntity.ok(disk.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path = "/{id}/compositions")
  @ResponseBody
  public ResponseEntity getCompositions(@PathVariable(name = "id") int id) {
    List<Composition> c = compositionRepository.getAllByDiskId(id);
    return ResponseEntity.ok(c);
  }

  @PutMapping(path = "/add")
  @ResponseBody
  public ResponseEntity<String> add(
      @RequestHeader(value = "Authorization") String auth,
      @RequestParam(name = "Name") String name,
      @RequestParam(name = "Image") String image,
      @RequestParam(name = "Amount") int amount,
      @RequestParam(name = "PresentDate") String presentDate,
      @RequestParam(name = "Price") double price,
      @RequestParam(name = "GroupID") int id) {
    String authEncoded = new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    Optional<Admin> admin =
        adminRepository.findAdminByAdminIdAndPassword(Integer.parseInt(cred[0]), cred[1]);
    if (admin.isPresent()) {
      Disk disk = new Disk();
      disk.setName(name);
      disk.setImage(image);
      disk.setAmount(amount);
      disk.setPresentDate(presentDate);
      disk.setPrice(price);
      disk.setGroupId(id);
      diskRepository.save(disk);
      return ResponseEntity.ok("{\"status\":\"success\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @DeleteMapping(path = "/delete/{id}")
  @ResponseBody
  public ResponseEntity<String> delete(
      @RequestHeader(value = "Authorization") String auth, @PathVariable(name = "id") int id) {
    String authEncoded = new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    Optional<Admin> admin =
        adminRepository.findAdminByAdminIdAndPassword(Integer.parseInt(cred[0]), cred[1]);
    if (admin.isPresent()) {
      diskRepository.deleteById(id);
      return ResponseEntity.ok("{\"status\":\"success\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }
}
