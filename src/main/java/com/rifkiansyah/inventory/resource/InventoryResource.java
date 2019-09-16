package com.rifkiansyah.inventory.resource;

import com.rifkiansyah.inventory.model.Inventory;
import com.rifkiansyah.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/inventory")
public class InventoryResource {

    private final Logger log = LoggerFactory.getLogger(InventoryResource.class);

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

    @GetMapping("/{itemcode}")
    public Inventory getItemcode(@PathVariable("itemcode")
                                                Long itemcode){
        return inventoryRepository.findOneByItemcode(itemcode);
    }

    @PutMapping("/update-quantity")
    public Inventory updateItemQuantity(Inventory inventory, Long itemcode){
        log.debug("Request to update quantity : {}", inventory);
        Inventory current = inventoryRepository.findOneByItemcode(itemcode);
        current.setQuantity(inventory.getQuantity());
        return inventoryRepository.save(current);
    }

    @PostMapping("/add")
    public List<String> addInventory(@RequestBody Inventory inventory){
        log.debug("Request to add inventory : {}", inventory);
        Inventory temp = new Inventory();
        //verification here
        if (inventory.getItemname().isEmpty()) {
            temp.setItemname("-");
        } else {
            temp.setItemname(inventory.getItemname());
        }
        if (inventory.getQuantity() != null){
            temp.setQuantity(inventory.getQuantity());
        } else {
            temp.setQuantity((long) 0);
        }
        if (inventory.getIdSupplier() != null){
            temp.setIdSupplier(inventory.getIdSupplier());
        } else {
            temp.setIdSupplier(null);
        }
            temp.setItemcode(inventory.getItemcode());
            temp.setSize(inventory.getSize());
            temp.setVariant(inventory.getVariant());

        inventoryRepository.save(temp);
        return getInventory(inventory.getIdSupplier());
    }

    @PostMapping("/update-price")
    public void addPrice(Long itemcode, Long price){
        log.debug("Request to add price : {}", itemcode);
        Inventory temp = inventoryRepository.findOneByItemcode(itemcode);
        temp.setItemPrice(price);
    }

    @PostMapping("/update-consignation")
    public void addConsignationPrice(Long itemcode, Long consigPrice){
        log.debug("Request to add consignation price : {}", itemcode);
        Inventory temp = inventoryRepository.findOneByItemcode(itemcode);
        temp.setConsignationPrice(consigPrice);
    }

/*    @PostMapping("/update-quantity")
    public void updateQuantity(Long itemcode){
        ResponseEntity<List<String>> inventoryResponse = restTemplate.exchange("http://sales-service/rest/sales/" + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });
    }*/

    @DeleteMapping("/delete/{username}")
    public boolean deleteUser(@PathVariable("username") Long username){
        log.debug("Request to delete all data from user : {}", username);
        List<Inventory> inventory = inventoryRepository.findByUsername(username);
        inventoryRepository.delete((Inventory) inventory);

        return true;
    }

    @PutMapping("/update")
    public void updateInventory(@PathVariable("id") Integer id,
                                @RequestParam( value = "itemcode",       required = false) Long itemcode,
                                @RequestParam( value = "idSupplier",   required = false) Long idSupplier,
                                @RequestParam( value = "itemname",       required = false) String itemname,
                                @RequestParam( value = "variant",          required = false) String variant,
                                @RequestParam( value = "size",   required = false) String size,
                                @RequestParam( value = "quantity", required = false) Long quantity,
                                @RequestParam( value = "itemPrice",   required = false) Long itemPrice,
                                @RequestParam( value = "consignationPrice", required = false) Long consignationPrice
                                ){
        log.debug("Request to update inventory id: {}", id);
        Inventory current = inventoryRepository.findOneById(id);

        if (itemcode != null){
            current.setItemcode(itemcode);
        }
        if (idSupplier != null){
            current.setIdSupplier(idSupplier);
        }
        if (itemname != null){
            current.setItemname(itemname);
        }
        if (variant != null){
            current.setVariant(variant);
        }
        if (size != null){
            current.setSize(size);
        }
        if (quantity != null){
            current.setQuantity(quantity);
        }
        if (itemPrice != null){
            current.setItemPrice(itemPrice);
        }
        if (consignationPrice != null){
            current.setConsignationPrice(consignationPrice);
        }

        inventoryRepository.save(current);
    }
}
