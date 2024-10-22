package com.farm.farm_manager.mapper;

import com.farm.farm_manager.dto.request.TaskRequest;
import com.farm.farm_manager.dto.response.*;
import com.farm.farm_manager.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HandleMapper {
    HandleMapper INSTANCE = Mappers.getMapper(HandleMapper.class);
    CropResponse toCrop(Crop crop);
    TaskResponse toTask(Task task);
    EmployeeResponse toEmployee(Employee employee);
     AnimalResponse toAnimal(Animal animal);
     Task toTaskRequest(TaskRequest request);
}
