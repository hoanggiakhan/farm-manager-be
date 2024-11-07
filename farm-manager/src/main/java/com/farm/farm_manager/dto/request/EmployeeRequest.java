package com.farm.farm_manager.dto.request;

import com.farm.farm_manager.entity.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    String fullName;
    String username;
    String password;
    String address;
    String phoneNumber;
    int age;
    LocalDate joinDate;
    String email;
    double salary;
    String nameRole;
}
