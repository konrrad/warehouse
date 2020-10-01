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
//        Optional<String> code= Optional.empty();
//        Optional<Barcode> barcode=Optional.empty();
//        try
//        {
//            code= Optional.of(reader.read(file.getInputStream()));
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        if(code.isEmpty()) return Optional.empty();
//        return Optional.of(new Barcode(code.get()));

//        String code="";
//        code=reader.read(file.getInputStream());
//        System.out.println(code);
//        return new Barcode(code);

//        String code = "";
//        try
//        {
//            code= reader.read(file.getInputStream());
//            System.out.println(code);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return code;
        return reader.read(file.getInputStream());

    }
}
