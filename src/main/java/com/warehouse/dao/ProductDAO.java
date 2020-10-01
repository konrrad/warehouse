package com.warehouse.dao;

import com.warehouse.model.Barcode;
import com.warehouse.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    boolean insertProduct(Product product);
    List<Product> getAllProducts();
    boolean deleteProduct(Product product);
    boolean deleteProduct(Barcode barcode);
    Optional<Product> selectProductByBarcode(Barcode barcode);
}
