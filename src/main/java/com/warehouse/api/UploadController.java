package com.warehouse.api;


import com.google.zxing.NotFoundException;
import com.warehouse.model.Barcode;
import com.warehouse.service.BarcodeService;
import com.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/barcode")
public class UploadController {

    private final BarcodeService barcodeService;
    private final ProductService productService;

    @Autowired
    public UploadController(BarcodeService barcodeService, ProductService productService)
    {
        this.barcodeService = barcodeService;
        this.productService= productService;
    }


    @GetMapping
    public String getIndex()
    {
        return "upload";
    }


    @PostMapping
    public String postFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes)
    {
        Optional<String> code;
        try
        {
            System.out.println(barcodeService.getBarcode(file));
            //@TODO delete
/*            code=Optional.ofNullable(barcodeService.getBarcode(file));*/
            code=Optional.of(barcodeService.getBarcode(file));

        } catch (IOException|NullPointerException e) {
            redirectAttributes.addFlashAttribute("code","PROVIDE AN IMAGE");
            return "redirect:/barcode";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("code","CODE NOT FOUND, TRY AGAIN");
            return "redirect:/barcode";
        }
        return "redirect:/barcode/"+code.orElse("error");
    }
}
