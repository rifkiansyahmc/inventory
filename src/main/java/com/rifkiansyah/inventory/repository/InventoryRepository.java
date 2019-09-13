package com.rifkiansyah.inventory.repository;

import com.rifkiansyah.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByUsername(String username);
}
