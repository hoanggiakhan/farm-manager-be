package com.farm.farm_manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int employeeId;
    String firstName;
    String lastName;
    String username;
    String password;
    String address;
    String phoneNumber;
    int age;
    LocalDate joinDate;
    String email;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
    @ManyToOne
    @JoinColumn(name = "farm_id")
    Farm farm;
    @OneToMany(mappedBy = "employee")
    List<Task> tasks;
}
