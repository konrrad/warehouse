package com.warehouse.model;


import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Product {
    @NonNull
    @Size(min=5, max=80)
    private String name;
    private Barcode barcode;
    @Min(value = 0, message = "Stock should be more than 0.")
    private int stock;
    private Unit unit;


    public Product(String name, Barcode barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public Product(String name, Barcode barcode, int stock, Unit unit) {
        this.name = name;
        this.barcode = barcode;
        this.stock = stock;
        this.unit=unit;
    }

    public Product()
    {

    }

    public Product(Barcode barcode)
    {
        this.barcode=barcode;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setBarcode(Barcode barcode){
        this.barcode=barcode;
    }
}
