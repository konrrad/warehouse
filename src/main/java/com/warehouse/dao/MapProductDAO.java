package com.warehouse.dao;

import com.warehouse.model.Barcode;
import com.warehouse.model.Product;
import com.warehouse.model.Unit;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("mapProductDAO")
public class MapProductDAO implements ProductDAO {

    Map<Barcode,Product> barcodeProductMap=new HashMap<>();

    public MapProductDAO() {
        barcodeProductMap.put(new Barcode("5902078000102"),new Product("Water", new Barcode("5902078000102"), 10, Unit.SZT));
    }

    @Override
    public boolean insertProduct(Product product) {
        deleteProduct(product.getBarcode());
        barcodeProductMap.put(product.getBarcode(),product);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(barcodeProductMap.values());
    }

    @Override
    public boolean deleteProduct(Product product) {
        return deleteProduct(product.getBarcode());
    }

    @Override
    public boolean deleteProduct(Barcode barcode) {
        barcodeProductMap.remove(barcode);
        return true;
    }

    @Override
    public Optional<Product> selectProductByBarcode(Barcode barcode) {
        return Optional.ofNullable(barcodeProductMap.get(barcode));
    }
}
