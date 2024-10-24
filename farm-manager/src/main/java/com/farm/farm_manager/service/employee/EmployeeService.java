package com.farm.farm_manager.service.employee;

import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.dao.RoleRepository;
import com.farm.farm_manager.dto.response.EmployeeResponse;
import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Role;
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
public class EmployeeService {
     EmployeeRepository employeeRepository;
     RoleRepository roleRepository;
    public List<EmployeeResponse> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for(Employee employee : employees){
            EmployeeResponse employeeResponse = HandleMapper.INSTANCE.toEmployee(employee);
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }

    public void deleteEmployee(int employeeId){
        employeeRepository.deleteById(employeeId);
    }
    public Employee getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        Employee employee = employeeRepository.findByUsername(username);
        return employee;
    }

    public void createEmployee(){

    }
}
