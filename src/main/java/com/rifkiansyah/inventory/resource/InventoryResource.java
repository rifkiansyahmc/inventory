package com.rifkiansyah.inventory.resource;

import com.rifkiansyah.inventory.model.Inventory;
import com.rifkiansyah.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/inventory")
public class InventoryResource {

    @Autowired
    RestTemplate restTemplate;

    private InventoryRepository inventoryRepository;

    public InventoryResource(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/{username}")
    public List<String> getInventory(@PathVariable("username")
                                             Long username){

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
            temp.setIdSupplier(inventory.getIdSupplier());
            temp.setItemcode(inventory.getItemcode());
            temp.setSize(inventory.getSize());
            temp.setVariant(inventory.getVariant());

        inventoryRepository.save(temp);
        return getInventory(inventory.getIdSupplier());
    }

    @PostMapping("/update-price/{itemcode}")
    public void addPrice(Long itemcode, Long price){
        Inventory temp = inventoryRepository.findOneByItemcode(itemcode);
        temp.setItemPrice(price);
    }

    @PostMapping("/update-consignation/{itemcode}")
    public void addConsignationPrice(Long itemcode, Long consigPrice){
        Inventory temp = inventoryRepository.findOneByItemcode(itemcode);
        temp.setConsignationPrice(consigPrice);
    }

    @PostMapping("/delete/{username}")
    public boolean deleteUser(@PathVariable("username") Long username){
        List<Inventory> inventory = inventoryRepository.findByUsername(username);
        inventoryRepository.delete((Inventory) inventory);

        return true;
    }

/*    @PostMapping("/update")
    public List<Inventory> updateInventory(@PathVariable("itemcode") String itemcode){
        ResponseEntity<List<String>> inventoryResponse = restTemplate.exchange("http://sales-service/rest/sales" + itemcode, HttpMethod.GET,
            null, new ParameterizedTypeReference<List<String>>(){});

        List<String> inventories = inventoryResponse.getBody();

        return null;
    }*/
}
