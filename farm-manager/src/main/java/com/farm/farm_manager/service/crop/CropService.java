package com.farm.farm_manager.service.crop;

import com.farm.farm_manager.dao.CropRepository;
import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.dao.FarmRepository;
import com.farm.farm_manager.dto.request.CropRequest;
import com.farm.farm_manager.entity.Crop;
import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Farm;
import com.farm.farm_manager.mapper.HandleMapper;
import com.farm.farm_manager.service.employee.EmployeeService;
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
    EmployeeRepository employeeRepository;
    FarmRepository farmRepository;

    public List<Crop> getAllCrop(){
        return cropRepository.findAll();
    }
    public void deleteCrop(int cropId){
        cropRepository.deleteById(cropId);
    }
    public Crop getCrop(CropRequest request){
        return cropRepository.findByCropName(request.getCropName());
    }

    public void createCrop(int userId , CropRequest request){
        Employee employee = employeeRepository.findById(userId).orElseThrow();
        Farm farm = farmRepository.findById(employee.getFarm().getFarmId()).orElseThrow();
        Crop crop = HandleMapper.INSTANCE.toCropRequest(request);
        crop.setFarm(farm);
        cropRepository.save(crop);
    }
}
