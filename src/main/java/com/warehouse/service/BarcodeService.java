package com.warehouse.service;

import com.google.zxing.NotFoundException;
import com.warehouse.model.Barcode;
import com.warehouse.readers.IReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BarcodeService {

    private final IReader reader;


    @Autowired
    public BarcodeService(IReader reader)
    {
        this.reader=reader;
    }

    public String getBarcode(MultipartFile file) throws NotFoundException, IOException
    {
        return reader.read(file.getInputStream());

    }
}
