package com.farm.farm_manager.service.user;

import com.farm.farm_manager.dao.EmployeeRepository;
import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class UserSecurityServiceImpl implements UserSecurityService {
    EmployeeRepository employeeRepository;
    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToAuthorities(user.getRoles()));
        return userDetail;
    }

    // Hàm để lấy role
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
