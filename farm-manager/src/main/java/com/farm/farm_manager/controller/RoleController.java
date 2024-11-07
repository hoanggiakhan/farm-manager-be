package com.farm.farm_manager.controller;

import com.farm.farm_manager.entity.Role;
import com.farm.farm_manager.service.role.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class RoleController {
    RoleService roleService;

//    @PostMapping("/{userId}")
//    void createRole(@RequestBody Role role , @PathVariable int userId){
//        roleService.createRole(userId,role);
//    }
}
