package com.farm.farm_manager.service.animal;

import com.farm.farm_manager.dao.AnimalRepository;
import com.farm.farm_manager.dto.response.AnimalResponse;
import com.farm.farm_manager.entity.Animal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class AnimalService {
    AnimalRepository animalRepository;

    public List<Animal> getAllAnimal(){
//        List<Animal> animals = animalRepository.findAll();
//        List<AnimalResponse> animalResponses = new ArrayList<>();
//        for(Animal animal : animals){
//            AnimalResponse animalResponse =
//        }
        return animalRepository.findAll();
    }
}
