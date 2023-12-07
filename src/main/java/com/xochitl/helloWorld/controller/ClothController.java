package com.xochitl.helloWorld.controller;

import com.xochitl.helloWorld.model.Cloth;
import com.xochitl.helloWorld.service.ClothService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("catalog")
public class ClothController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ClothService clothService;

    @PostMapping
    public ResponseEntity<Cloth> saveCloth(@RequestBody Cloth cloth) {
        log.info("Entering create() with {}", cloth);
        return clothService.createCloth(cloth).map(newCloth -> new ResponseEntity<>(newCloth, OK))
                .orElse(new ResponseEntity<>(CONFLICT));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cloth> getClothById(@PathVariable("id") String id) {
        return clothService.getClothByID(id).map(newClothData -> new ResponseEntity<>(newClothData, OK))
                .orElse(new ResponseEntity<>(CONFLICT));
    }

    @GetMapping()
    public ResponseEntity<List<Cloth>> getAllCloths() {
        return clothService.getAllCloths().map(newClothData -> new ResponseEntity<>(newClothData, OK))
                .orElse(new ResponseEntity<>(CONFLICT));
    }
}
