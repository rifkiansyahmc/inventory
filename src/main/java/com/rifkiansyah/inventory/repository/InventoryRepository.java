package com.rifkiansyah.inventory.repository;

import com.rifkiansyah.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByUsername(Long username);

    List<Inventory> findByItemcode(Long itemcode);

    Inventory findOneByItemcode(Long itemcode);

    Inventory findOneById(Integer id);
}
