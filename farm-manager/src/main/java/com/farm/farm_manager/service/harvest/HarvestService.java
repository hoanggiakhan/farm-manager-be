package com.farm.farm_manager.service.harvest;

import com.farm.farm_manager.dao.HarvestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class HarvestService {
    HarvestRepository harvestRepository;

}
