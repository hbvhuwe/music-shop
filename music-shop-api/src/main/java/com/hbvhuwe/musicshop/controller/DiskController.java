package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.repository.DiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/rest/disks")
public class DiskController {
    private final DiskRepository repository;

    @Autowired
    public DiskController(DiskRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<Disk> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Disk findById(@PathVariable(name = "id") int id) throws Exception {
        Optional<Disk> disk = repository.findById(id);
        if (disk.isPresent()) {
            return disk.get();
        } else {
            throw new Exception("Disk not found");
        }
    }

    @PutMapping(path = "/add")
    @ResponseBody
    public ResponseEntity<String> add(@RequestParam(name = "Name") String name,
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
        return ResponseEntity.ok("{status:\"successfully add new disk\"}");
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
        repository.deleteById(id);
        return ResponseEntity.ok("{status:\"successfully delete disk\"}");
    }
}
