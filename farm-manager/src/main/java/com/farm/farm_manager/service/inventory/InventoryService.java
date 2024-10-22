package com.farm.farm_manager.service.inventory;

import com.farm.farm_manager.dao.InventoryRepository;
import com.farm.farm_manager.dao.ItemsRepository;
import com.farm.farm_manager.dto.request.ItemRequest;
import com.farm.farm_manager.dto.response.ItemResponse;
import com.farm.farm_manager.entity.Inventory;
import com.farm.farm_manager.entity.Items;
import com.farm.farm_manager.mapper.HandleMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class InventoryService {
    InventoryRepository inventoryRepository;
    ItemsRepository itemsRepository;
   public List<ItemResponse> getAllItemByInventory(int inventoryId){
       Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow();
       List<Items> items = inventory.getItems();
       List<ItemResponse> itemResponses = new ArrayList<>();
       for(Items item : items){
           ItemResponse itemResponse = new ItemResponse();
           itemResponse.setItemId(item.getItemId());
           itemResponse.setItemName(item.getItemName());
           itemResponse.setUnit(item.getUnit());
           itemResponse.setType(item.getType());
           itemResponse.setQuantity(item.getQuantity());
           itemResponse.setImportPrice(item.getImportPrice());
           itemResponses.add(itemResponse);
       }
       return itemResponses;
   }

   public void addItem(Items request, int inventoryId){
       Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow();
       request.setInventory(inventory);
       itemsRepository.save(request);
   }


   public void deleteItem(int itemId){
      itemsRepository.deleteById(itemId);
   }
}
