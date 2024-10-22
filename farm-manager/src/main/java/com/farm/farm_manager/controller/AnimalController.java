package com.farm.farm_manager.controller;

import com.farm.farm_manager.entity.Animal;
import com.farm.farm_manager.service.animal.AnimalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnimalController {
    AnimalService animalService;

    @GetMapping
    List<Animal> getAllAnimal(){
        return animalService.getAllAnimal();
    }
}
