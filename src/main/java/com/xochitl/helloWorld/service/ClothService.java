package com.xochitl.helloWorld.service;

import com.xochitl.helloWorld.model.Cloth;
import com.xochitl.helloWorld.repository.ClothRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClothRepository clothRepository;

    public Optional<Cloth> getClothByID(String cloth_id) {
        log.info("Metodo Service getClothById");
        return clothRepository.getClothById(cloth_id);
    }

    public Optional<List<Cloth>> getAllCloths() {
        log.info("Metodo Service getAllCloths");
        return clothRepository.getAllCloths();
    }

    public Optional<Cloth> createCloth(Cloth cloth) {
        log.info("Metodo service createUser");
        clothRepository.save(cloth);
        return Optional.of(cloth);
    }
}
