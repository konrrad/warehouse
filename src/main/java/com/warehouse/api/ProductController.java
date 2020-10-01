package com.warehouse.api;

import com.warehouse.model.Barcode;
import com.warehouse.model.Product;
import com.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/barcode/{code}")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping
    public String getProductData(@PathVariable("code")Barcode barcode, Model model)
    {
        Optional<Product> productOptional=productService.selectProductByBarcode(barcode);
        Product newProduct=new Product(barcode);
        if(productOptional.isPresent())
        {
            final Product product=productOptional.get();
            model.addAttribute("product",product);
            newProduct.setName(product.getName());
            newProduct.setStock(product.getStock());
        }

        if(!model.containsAttribute("newProduct"))
            model.addAttribute("newProduct",newProduct);
        return "product";
    }


    @PostMapping
    public String uploadProductData(@Valid @ModelAttribute("newProduct") Product newProduct, BindingResult bindingResult,
                                    Model model, @PathVariable("code")Barcode barcode, RedirectAttributes ra)
    {
        System.out.println(bindingResult);
        if(bindingResult.hasErrors())
        {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.newProduct",bindingResult);
            ra.addFlashAttribute("newProduct",newProduct);
            return "redirect:/barcode/"+barcode;
        }
        newProduct.setBarcode(barcode);
        productService.insertProduct(newProduct);
        return "redirect:/barcode";
    }

}
