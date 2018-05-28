package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/rest/groups")
public class GroupController {
    private final GroupRepository repository;

    @Autowired
    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<Group> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Group findById(@PathVariable(name = "id") int id) throws Exception {
        Optional<Group> group = repository.findById(id);
        if (group.isPresent()) {
            return group.get();
        } else {
            throw new Exception("Group not found");
        }
    }

    @PutMapping(path = "/add")
    @ResponseBody
    public ResponseEntity<String> add(@RequestParam(name = "Name") String name,
                                      @RequestParam(name = "Musician") String musician,
                                      @RequestParam(name = "Style") String style) {
        Group group = new Group();
        group.setName(name);
        group.setMusician(musician);
        group.setStyle(style);
        repository.save(group);
        return ResponseEntity.ok("{status:\"successfully add new group\"}");
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
        repository.deleteById(id);
        return ResponseEntity.ok("{status:\"successfully delete a group\"}");
    }
}
