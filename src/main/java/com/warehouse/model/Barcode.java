package com.warehouse.model;

import java.util.Objects;

public class Barcode {
    Long barcode;

    public Barcode(Long barcode) {
        this.barcode = barcode;
    }

    public Barcode(String barcode)
    {
        if(barcode.equals("")) throw new IllegalStateException("Illegal Barcode.");
        this.barcode=Long.decode(barcode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barcode barcode1 = (Barcode) o;
        return barcode.equals(barcode1.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }

    @Override
    public String toString()
    {
        return barcode.toString();
    }
}
