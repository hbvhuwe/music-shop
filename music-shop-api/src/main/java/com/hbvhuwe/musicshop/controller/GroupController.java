package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/groups")
public class GroupController {
  private final GroupRepository repository;

  @Autowired
  public GroupController(GroupRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/")
  @ResponseBody
  public ResponseEntity findAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity findById(@PathVariable(name = "id") int id) {
    Optional<Group> group = repository.findById(id);
    if (group.isPresent()) {
      return ResponseEntity.ok(group.get());
    } else {
      return ResponseEntity.notFound().build();
    }
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
    repository.save(group);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }

  @DeleteMapping(path = "/delete/{id}")
  @ResponseBody
  public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
    repository.deleteById(id);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }
}
