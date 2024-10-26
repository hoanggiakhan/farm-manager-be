package com.farm.farm_manager.service.employee;

import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.dao.FarmRepository;
import com.farm.farm_manager.dao.RoleRepository;
import com.farm.farm_manager.dto.request.EmployeeRequest;
import com.farm.farm_manager.dto.response.EmployeeResponse;
import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Farm;
import com.farm.farm_manager.entity.Role;
import com.farm.farm_manager.mapper.HandleMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EmployeeService {
     EmployeeRepository employeeRepository;
     RoleRepository roleRepository;
     FarmRepository farmRepository;
     PasswordEncoder passwordEncoder;
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponse employeeResponse = HandleMapper.INSTANCE.toEmployee(employee);

            // Kiểm tra null trước khi lấy danh sách vai trò
            if (employee.getRoles() != null) {
                Set<String> nameRole = new HashSet<>();
                for (Role role : employee.getRoles()) {
                    nameRole.add(role.getRoleName());
                }
                employeeResponse.setNameRole(nameRole);
            } else {
                employeeResponse.setNameRole(Collections.emptySet()); // Nếu không có vai trò, gán tập rỗng
            }

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

    public void createEmployee(int userId , EmployeeRequest request){
       Employee employee = employeeRepository.findById(userId).orElseThrow();
        Farm farm = farmRepository.findById(employee.getFarm().getFarmId()).orElseThrow();
        Role role = roleRepository.findByRoleName(request.getNameRole());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        if(employeeRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("username đã tồn tại");
        }
        Employee newEmployee = HandleMapper.INSTANCE.toEmployeeRequest(request);
        newEmployee.setFullName(request.getFullName());
        newEmployee.setFarm(farm);
        newEmployee.setRoles(roles);
        newEmployee.setPassword(passwordEncoder.encode(request.getPassword()));
        employeeRepository.save(newEmployee);
    }
}
