package com.warehouse.dao;

import com.warehouse.model.Barcode;
import com.warehouse.model.Product;
import com.warehouse.model.Unit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository("fakeProductDB")
public class FakeProductDAO implements ProductDAO {

    List<Product> products=new ArrayList<>(Arrays.asList(new Product("Water", new Barcode("5902078000102"), 10, Unit.SZT)));
    @Override
    public boolean insertProduct(Product product) {
        products.add(product);
        System.out.println("inserted "+product.getName()+product.getBarcode()+product.getUnit());
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public boolean deleteProduct(Product product) {
        products.remove(product);
        return true;
    }

    @Override
    public boolean deleteProduct(Barcode barcode) {
        final Optional<Product> productToDelete=selectProductByBarcode(barcode);
        if(productToDelete.isEmpty()) return false;
        products.remove(productToDelete.get());
        return true;
    }

    @Override
    public Optional<Product> selectProductByBarcode(Barcode barcode) {
        return products.stream().filter(product -> product.getBarcode().equals(barcode)).findFirst();
    }
}
