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
            "/inventory/items/**",
            "/task/**"
    };

    public static final String[] publicPost = {
      "/employee/login",
            "/inventory/add-item/**",
            "/task"
    };

    public static final String[] deletePublic = {
            "/inventory/delete-item/**"
    };
}
