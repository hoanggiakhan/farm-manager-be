package com.farm.farm_manager.security;
public class Endpoints {
    public static final String[] publicGet = {
            "/animal",
            "/employee/**",
            "/crop",
            "/animals/**",
            "crops/**",
            "/employees/**",
            "/employees",
            "/farms/**",
            "/farm/crops/**",
            "/farm/animals/**",
            "/farm/employees/**",
            "/farm/inventories/**",
            "/farm/role/**",
            "/inventory/items/**",
            "/task/**",
            "/crop/**"
    };

    public static final String[] publicPost = {
      "/employee/login",
            "/inventory/add-item/**",
            "/task",
            "/farm",
            "/inventory/add-inventory/**",
            "/role/**",
    };

    public static final String[] deletePublic = {
            "/inventory/delete-item/**",
            "/inventory/delete-inventory/**",
            "/task/delete-task/**",
            "/employee/delete-employee/**",
            "/crop/delete-crop/**"
    };
}
