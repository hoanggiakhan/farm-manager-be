package com.farm.farm_manager.service.task;

import com.farm.farm_manager.dao.*;
import com.farm.farm_manager.dto.request.TaskRequest;
import com.farm.farm_manager.dto.response.TaskResponse;
import com.farm.farm_manager.entity.*;
import com.farm.farm_manager.mapper.HandleMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class TaskService {
    TaskRepository taskRepository;
    CropRepository cropRepository;
    AnimalRepository animalRepository;
    EmployeeRepository employeeRepository;
    FarmRepository farmRepository;
    public List<TaskResponse> getAllTaskByUserId(int userId){
        Employee employee = employeeRepository.findById(userId).orElseThrow();
        List<Task> tasks = employee.getTasks();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            TaskResponse taskResponse = HandleMapper.INSTANCE.toTask(task);
            if (taskResponse != null) {
                taskResponse.setNameEmployee(employee.getFirstName()+" "+employee.getLastName());
                if(task.getAnimal() != null){
                    taskResponse.setNameData(task.getAnimal().getAnimalName());
                }
                if(task.getCrop() != null){
                    taskResponse.setNameData(task.getCrop().getCropName());
                }
                if(task.getAnimal() != null && task.getCrop() != null){
                    throw new RuntimeException("sai cấu trúc");
                }
                taskResponses.add(taskResponse);
            }
        }
        return taskResponses;
    }

    public List<TaskResponse> getAllTaskByFarm(int userId){
         Employee employee = employeeRepository.findById(userId).orElseThrow();
         Farm farm = farmRepository.findById(employee.getFarm().getFarmId()).orElseThrow();
         List<TaskResponse> taskResponses = new ArrayList<>();
          List<Integer> employeeIds = new ArrayList<>();
          for(Employee employee1 : farm.getEmployees()){
              employeeIds.add(employee1.getEmployeeId());
          }
          for(int employeeId : employeeIds){
              for(TaskResponse taskResponse : getAllTaskByUserId(employeeId)){
                  taskResponses.add(taskResponse);
              }
          }
          return taskResponses;
    }

    public void createTask(TaskRequest request){
        Task task = HandleMapper.INSTANCE.toTaskRequest(request);
        Animal animal = animalRepository.findByAnimalName(request.getNameData());
        Crop crop = cropRepository.findByCropName(request.getNameData());
        task.setEmployee(employeeRepository.findByNameEmployee(request.getNameEmployee()));
        if(animal!=null){
            task.setAnimal(animal);
        }
        if(crop!=null){
            task.setCrop(crop);
        }
        taskRepository.save(task);
    }
}
