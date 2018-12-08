package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.repository.DiskRepository;
import com.hbvhuwe.musicshop.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/groups")
public class GroupController {
  private final GroupRepository groupRepository;
  private final DiskRepository diskRepository;

  @Autowired
  public GroupController(GroupRepository repository, DiskRepository diskRepository) {
    this.groupRepository = repository;
    this.diskRepository = diskRepository;
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
      @RequestParam(name = "Name") String name,
      @RequestParam(name = "Musician") String musician,
      @RequestParam(name = "Style") String style) {
    Group group = new Group();
    group.setName(name);
    group.setMusician(musician);
    group.setStyle(style);
    groupRepository.save(group);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }

  @DeleteMapping(path = "/delete/{id}")
  @ResponseBody
  public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
    groupRepository.deleteById(id);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }
}
