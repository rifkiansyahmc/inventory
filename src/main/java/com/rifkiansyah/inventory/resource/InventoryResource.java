package com.rifkiansyah.inventory.resource;

import com.rifkiansyah.inventory.model.Inventory;
import com.rifkiansyah.inventory.repository.InventoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/inventory")
public class InventoryResource {

    private InventoryRepository inventoryRepository;

    @GetMapping("/{username}")
    public List<String> getInventory(@PathVariable("username")
                                     final String username){

        inventoryRepository.findByUsername(username)
        .stream()
        .map(Inventory::getItemname)
        .collect(Collectors.toList());
        return null;
    }
}
