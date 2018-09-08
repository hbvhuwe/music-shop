package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.repository.CompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/compositions")
public class CompositionController {
  private final CompositionRepository repository;

  @Autowired
  public CompositionController(CompositionRepository repository) {
    this.repository = repository;
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

  @PutMapping(path = "/add")
  @ResponseBody
  public ResponseEntity<String> add(
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
  public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
    repository.deleteById(id);
    return ResponseEntity.ok("{\"status\":\"success\"}");
  }
}
