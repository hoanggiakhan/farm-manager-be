package com.farm.farm_manager.service.farm;

import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.dao.FarmRepository;
import com.farm.farm_manager.dao.InventoryRepository;
import com.farm.farm_manager.dto.response.AnimalResponse;
import com.farm.farm_manager.dto.response.CropResponse;
import com.farm.farm_manager.dto.response.EmployeeResponse;
import com.farm.farm_manager.dto.response.InventoryResponse;
import com.farm.farm_manager.entity.*;
import com.farm.farm_manager.mapper.HandleMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class FarmService {
    FarmRepository farmRepository;
    EmployeeRepository employeeRepository;
    public List<CropResponse> getAllCropByFarm(int userId){
        Farm farm = getFarm(userId);
        List<Crop> crops = farm.getCrops();
        List<CropResponse> cropResponses = new ArrayList<>();
        for(Crop crop : crops){
//            CropResponse cropResponse = new CropResponse();
//            cropResponse.setCropName(crop.getCropName());
//            cropResponse.setCropId(crop.getCropId());
//            cropResponse.setAge(crop.getAge());
//            cropResponse.setAcreage(crop.getAcreage());
//            cropResponse.setQuantity(crop.getQuantity());
//            cropResponse.setProductivity(crop.getProductivity());
//            cropResponse.setStatus(crop.getStatus());
//            cropResponse.setPlantingDay(crop.getPlantingDay());
//            cropResponse.setSellPrice(crop.getSellPrice());
//            cropResponse.setImportPrice(crop.getImportPrice());
            CropResponse cropResponse = HandleMapper.INSTANCE.toCrop(crop);
            cropResponses.add(cropResponse);
        }
        return cropResponses;
    }
    public List<AnimalResponse> getAllAnimalByFarm(int userId){
        Farm farm = getFarm(userId);
        List<Animal> animals = farm.getAnimals();
        List<AnimalResponse> animalResponses = new ArrayList<>();
        for(Animal animal : animals){
            AnimalResponse animalResponse = HandleMapper.INSTANCE.toAnimal(animal);
            animalResponses.add(animalResponse);
        }
        return animalResponses;
    }

    public List<EmployeeResponse> getAllEmployeeByFarm(int userId){
        Farm farm = getFarm(userId);
        List<Employee> employees = farm.getEmployees();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for(Employee employee : employees){
            EmployeeResponse employeeResponse = HandleMapper.INSTANCE.toEmployee(employee);
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }

    public List<InventoryResponse> getAllInventoryByFarm(int userId){
        Farm farm = getFarm(userId);
        List<Inventory> inventories = farm.getInventories();
        List<InventoryResponse> inventoryResponses = new ArrayList<>();
        for(Inventory inventory : inventories){
            InventoryResponse inventoryResponse = new InventoryResponse();
            inventoryResponse.setInventoryId(inventory.getInventoryId());
            inventoryResponses.add(inventoryResponse);
        }
        return inventoryResponses;
    }
    public Farm getFarm(int userId){
        Employee employee = employeeRepository.findById(userId).orElseThrow();
        Farm farm = farmRepository.findById(employee.getFarm().getFarmId()).orElseThrow(()-> new RuntimeException("Không tiìm thấy farm"));
        return farm;
    }
}
