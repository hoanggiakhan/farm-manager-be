package com.farm.farm_manager.controller;

import com.farm.farm_manager.dto.request.EmployeeRequest;
import com.farm.farm_manager.dto.request.LoginRequest;
import com.farm.farm_manager.dto.response.EmployeeResponse;
import com.farm.farm_manager.dto.response.JwtResponse;
import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Role;
import com.farm.farm_manager.service.employee.EmployeeService;
import com.farm.farm_manager.service.jwt.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;
    AuthenticationManager authenticationManager;
    JwtService jwtUtils;
    @GetMapping
    List<EmployeeResponse> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate (@RequestBody LoginRequest loginRequest) {
        // Xử lý xác thực người dùng
        try{
            // authentication sẽ giúp ta lấy dữ liệu từ db để kiểm tra
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            // Nếu xác thực thành công
            if (authentication.isAuthenticated()) {
                // Tạo token cho người dùng
                final String jwtToken = jwtUtils.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwtToken));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công");
    }
    @DeleteMapping("/delete-employee/{employeeId}")
    void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }
    @GetMapping("/my-info")
    Employee getMyInfo(){
        return employeeService.getMyInfo();
    }

    @PostMapping("/{userId}")
    void createEmployee(@PathVariable int userId , @RequestBody EmployeeRequest request){
        employeeService.createEmployee(userId,request);
    }
}
