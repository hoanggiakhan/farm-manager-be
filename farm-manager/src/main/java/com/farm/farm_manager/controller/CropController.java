package com.farm.farm_manager.controller;

import com.farm.farm_manager.entity.Crop;
import com.farm.farm_manager.service.crop.CropService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crop")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CropController {
    CropService cropService;
    @GetMapping
    List<Crop> getAllCrop(){
        return cropService.getAllCrop();
    }
}
