package com.rifkiansyah.inventory.resource;

import com.rifkiansyah.inventory.model.Inventory;
import com.rifkiansyah.inventory.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/inventory")
public class InventoryResource {

    private InventoryRepository inventoryRepository;

    public InventoryResource(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/{username}")
    public List<String> getInventory(@PathVariable("username")
                                     String username){

        return inventoryRepository.findByUsername(username)
        .stream()
        .map(Inventory::getItemname)
        .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> addInventory(@RequestBody Inventory inventory){

        Inventory temp = new Inventory();
        temp.setItemname(inventory.getItemname());
        temp.setQuantity(inventory.getQuantity());
        temp.setUsername(inventory.getUsername());

        inventoryRepository.save(temp);
        return getInventory(inventory.getUsername());
    }

    @PostMapping("/delete/{username}")
    public boolean deleteUser(@PathVariable("username") String username){
        List<Inventory> inventory = inventoryRepository.findByUsername(username);
        inventoryRepository.delete((Inventory) inventory);

        return true;
    }
}
