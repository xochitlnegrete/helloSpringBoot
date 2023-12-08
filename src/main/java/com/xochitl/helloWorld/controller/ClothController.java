package com.xochitl.helloWorld.controller;

import com.xochitl.helloWorld.model.Cloth;
import com.xochitl.helloWorld.service.ClothService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Cloth> addCloth(@RequestBody Cloth cloth) {
        log.info("Entering add() with {}", cloth);
        return clothService.addCloth(cloth).map(newCloth -> new ResponseEntity<>(newCloth, OK))
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

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCloth(@PathVariable("id") String id) {
        return  new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        //return clothService.deleteCloth(id)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@PutMapping()
    public ResponseEntity<Cloth> update(@RequestBody Cloth cloth){
        if(!clothService.existsId(cloth.getId()))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        if(usuarioService.existsNombre(usuarioDto.getNombre()) && !usuarioService.getOne(usuarioDto.getId()).getNombre().equals(usuarioDto.getNombre()))
            return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(usuarioService.update(usuarioDto));
    }*/
}
