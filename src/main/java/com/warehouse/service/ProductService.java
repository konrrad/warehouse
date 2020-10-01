package com.warehouse.service;


import com.warehouse.dao.ProductDAO;
import com.warehouse.model.Barcode;
import com.warehouse.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(@Qualifier("mapProductDAO") ProductDAO productDAO) {
        this.productDAO=productDAO;
    }

    public boolean insertProduct(Product product)
    {
        return productDAO.insertProduct(product);
    }
    public List<Product> getAllProducts()
    {
        return productDAO.getAllProducts();
    }
    public boolean deleteProduct(Product product)
    {
        return productDAO.deleteProduct(product);
    }
    public boolean deleteProduct(Barcode barcode)
    {
        return productDAO.deleteProduct(barcode);
    }
    public Optional<Product> selectProductByBarcode(Barcode barcode)
    {
        return productDAO.selectProductByBarcode(barcode);
    }

    public boolean isPresent(Barcode barcode)
    {
        return productDAO.selectProductByBarcode(barcode).isPresent();
    }

}
