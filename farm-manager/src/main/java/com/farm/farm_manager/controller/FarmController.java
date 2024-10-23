package com.farm.farm_manager.controller;

import com.farm.farm_manager.dto.request.FarmRequest;
import com.farm.farm_manager.dto.response.AnimalResponse;
import com.farm.farm_manager.dto.response.CropResponse;
import com.farm.farm_manager.dto.response.EmployeeResponse;
import com.farm.farm_manager.dto.response.InventoryResponse;
import com.farm.farm_manager.entity.Crop;
import com.farm.farm_manager.entity.Inventory;
import com.farm.farm_manager.service.farm.FarmService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farm")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class FarmController {
    FarmService farmService;

    @GetMapping("/crops/{userId}")
    List<CropResponse> getAllCropByFarm(@PathVariable int userId){
        return farmService.getAllCropByFarm(userId);
    }
    @GetMapping("/animals/{userId}")
    List<AnimalResponse> getAllAnimalByFarm(@PathVariable int userId){
        return farmService.getAllAnimalByFarm(userId);
    }
    @GetMapping("/employees/{userId}")
    List<EmployeeResponse> getAllEmployeeByFarm(@PathVariable int userId){
        return farmService.getAllEmployeeByFarm(userId);
    }
    @GetMapping("/inventories/{userId}")
    List<InventoryResponse> getALlInventoryByFarm(@PathVariable int userId){
        return farmService.getAllInventoryByFarm(userId);
    }
    @PostMapping
    ResponseEntity<?> createFarm(@RequestBody FarmRequest request){
      return farmService.createFarm(request);
    }
}
