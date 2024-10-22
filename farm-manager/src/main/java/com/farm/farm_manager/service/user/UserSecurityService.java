package com.farm.farm_manager.service.user;

import com.farm.farm_manager.entity.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    Employee findByUsername(String username);
}
