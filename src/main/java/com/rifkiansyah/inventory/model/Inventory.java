package com.rifkiansyah.inventory.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inventory", catalog = "test")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "itemcode")
    private Long itemcode;

    @Column (name = "id_supplier")
    private Long idSupplier;

    @Column (name = "itemname")
    private String itemname;

    @Column (name = "variant")
    private String variant;

    @Column (name = "size")
    private String size;

    @Column (name = "quantity")
    private Long quantity;

    @Column (name = "item_price")
    private Long itemPrice;

    @Column (name = "consignation_price")
    private Long consignationPrice;

    public Inventory() {
    }

    public Inventory(Long itemcode, Long idSupplier, String itemname, String variant, String size, Long quantity) {
        this.itemcode = itemcode;
        this.idSupplier = idSupplier;
        this.itemname = itemname;
        this.variant = variant;
        this.size = size;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getItemcode() {
        return itemcode;
    }

    public void setItemcode(Long itemcode) {
        this.itemcode = itemcode;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getConsignationPrice() {
        return consignationPrice;
    }

    public void setConsignationPrice(Long consignationPrice) {
        this.consignationPrice = consignationPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id.equals(inventory.id) &&
                Objects.equals(itemcode, inventory.itemcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemcode);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                ", itemcode=" + itemcode +
                ", idSupplier='" + idSupplier + '\'' +
                ", itemname='" + itemname + '\'' +
                ", variant='" + variant + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", itemPrice=" + itemPrice +
                ", consignationPrice=" + consignationPrice +
                '}';
    }
}


