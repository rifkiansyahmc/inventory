package com.rifkiansyah.inventory.model;

import javax.persistence.*;

@Entity
@Table(name = "inventory", catalog = "test")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "username")
    private String username;

    @Column (name = "itemname")
    private String itemname;

    @Column (name = "quantity")
    private String quantity;

    public Inventory() {
    }

    public Inventory(String username, String itemname, String quantity) {
        this.username = username;
        this.itemname = itemname;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}