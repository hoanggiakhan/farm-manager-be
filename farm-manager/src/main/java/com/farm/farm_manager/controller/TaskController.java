package com.farm.farm_manager.controller;

import com.farm.farm_manager.dto.request.TaskRequest;
import com.farm.farm_manager.dto.response.TaskResponse;
import com.farm.farm_manager.service.task.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class TaskController {
    TaskService taskService;
    @GetMapping("/{userId}")
    List<TaskResponse> getAllTask(@PathVariable int userId){
        return taskService.getAllTaskByFarm(userId);
    }

    @PostMapping
    void createTask(@RequestBody TaskRequest request){
        taskService.createTask(request);
    }
}