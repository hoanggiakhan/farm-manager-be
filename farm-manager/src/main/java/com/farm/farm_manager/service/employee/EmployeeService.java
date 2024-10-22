package com.farm.farm_manager.service.employee;

import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.dto.response.EmployeeResponse;
import com.farm.farm_manager.entity.Employee;
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
    public List<EmployeeResponse> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for(Employee employee : employees){
            EmployeeResponse employeeResponse = HandleMapper.INSTANCE.toEmployee(employee);
//            employeeResponse.setEmployeeId(employee.getEmployeeId());
//            employeeResponse.setLastName(employee.getLastName());
//            employeeResponse.setFirstName(employee.getFirstName());
//            employeeResponse.setAge(employee.getAge());
//            employeeResponse.setAddress(employee.getAddress());
//            employeeResponse.setJoinDate(employee.getJoinDate());
//            employeeResponse.setNameFarm(employee.getFarm().getFarmName());
//            employeeResponse.setPhoneNumber(employee.getPhoneNumber());
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }
    
    public Employee getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        Employee employee = employeeRepository.findByUsername(username);
        return employee;
    }
}
