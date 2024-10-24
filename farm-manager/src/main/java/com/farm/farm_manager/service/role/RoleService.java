package com.farm.farm_manager.service.role;

import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.dao.FarmRepository;
import com.farm.farm_manager.dao.RoleRepository;
import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Farm;
import com.farm.farm_manager.entity.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    FarmRepository farmRepository;
    EmployeeRepository employeeRepository;

    public void createRole(int userId , Role role){
        Employee employee = employeeRepository.findById(userId).orElseThrow();
        Farm farm = farmRepository.findById(employee.getFarm().getFarmId()).orElseThrow();
        role.setFarm(farm);
        roleRepository.save(role);
    }
}
