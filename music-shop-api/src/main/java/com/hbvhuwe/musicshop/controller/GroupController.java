package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Admin;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.repository.AdminRepository;
import com.hbvhuwe.musicshop.repository.DiskRepository;
import com.hbvhuwe.musicshop.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/groups")
public class GroupController {
  private final GroupRepository groupRepository;
  private final DiskRepository diskRepository;
  private final AdminRepository adminRepository;

  @Autowired
  public GroupController(
      GroupRepository repository, DiskRepository diskRepository, AdminRepository adminRepository) {
    this.groupRepository = repository;
    this.diskRepository = diskRepository;
    this.adminRepository = adminRepository;
  }

  @GetMapping(path = "/")
  @ResponseBody
  public ResponseEntity findAll() {
    return ResponseEntity.ok(groupRepository.findAll());
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity findById(@PathVariable(name = "id") int id) {
    Optional<Group> group = groupRepository.findById(id);
    if (group.isPresent()) {
      return ResponseEntity.ok(group.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path = "/{id}/disks")
  @ResponseBody
  public ResponseEntity getDisksById(@PathVariable(name = "id") int id) {
    List<Disk> disks = diskRepository.getAllByGroupId(id);
    return ResponseEntity.ok(disks);
  }

  @PutMapping(path = "/add")
  @ResponseBody
  public ResponseEntity<String> add(
      @RequestHeader(value = "Authorization") String auth,
      @RequestParam(name = "Name") String name,
      @RequestParam(name = "Musician") String musician,
      @RequestParam(name = "Style") String style) {
    String authEncoded = new String(Base64.getDecoder().decode(auth.getBytes()));
    String[] cred = authEncoded.split(":");
    Optional<Admin> admin =
        adminRepository.findAdminByAdminIdAndPassword(Integer.parseInt(cred[0]), cred[1]);
    if (admin.isPresent()) {
      Group group = new Group();
      group.setName(name);
      group.setMusician(musician);
      group.setStyle(style);
      groupRepository.save(group);
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
      groupRepository.deleteById(id);
      return ResponseEntity.ok("{\"status\":\"success\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }
}
