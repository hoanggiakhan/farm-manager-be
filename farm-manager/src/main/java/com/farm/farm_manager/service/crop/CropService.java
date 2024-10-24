package com.farm.farm_manager.service.crop;

import com.farm.farm_manager.dao.CropRepository;
import com.farm.farm_manager.dto.request.CropRequest;
import com.farm.farm_manager.entity.Crop;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class CropService {
    CropRepository cropRepository;

    public List<Crop> getAllCrop(){
        return cropRepository.findAll();
    }
    public void deleteCrop(int cropId){
        cropRepository.deleteById(cropId);
    }
    public Crop getCrop(CropRequest request){
        return cropRepository.findByCropName(request.getCropName());
    }
}
