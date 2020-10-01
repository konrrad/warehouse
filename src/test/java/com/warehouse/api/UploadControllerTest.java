package com.warehouse.api;

import com.google.zxing.NotFoundException;
import com.warehouse.service.BarcodeService;
import com.warehouse.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UploadControllerTest {

    @Test
    public void shouldReturnView() throws Exception {
        //given
        BarcodeService barcodeService = mock(BarcodeService.class);
        ProductService productService = mock(ProductService.class);
        UploadController uploadController = new UploadController(barcodeService, productService);
        MockMvc mockMvc = standaloneSetup(uploadController).build();
        //when
        mockMvc.perform(get("/barcode"))
                //then
                .andExpect(view().name("upload"));
    }

    @Test
    public void noCodePostedTest() throws Exception {

        //given
        BarcodeService barcodeService = mock(BarcodeService.class);
        ProductService productService = mock(ProductService.class);
        UploadController uploadController = new UploadController(barcodeService, productService);
        MockMultipartFile file=new MockMultipartFile("file","","json","{\"json\": \"someValue\"}".getBytes());
        MockMvc mockMvc=standaloneSetup(uploadController).build();

        //when
        mockMvc.perform(multipart("/barcode").file(file))
                //then
                .andExpect(redirectedUrl("/barcode"))
                .andExpect(flash().attributeExists("code"))
                .andExpect(flash().attribute("code","PROVIDE AN IMAGE"));

    }

    @Test
    public void noCodeFoundTest() throws Exception {
        //given
        BarcodeService barcodeService = mock(BarcodeService.class);
        when(barcodeService.getBarcode(any(MultipartFile.class))).thenThrow(NotFoundException.class);
        ProductService productService = mock(ProductService.class);
        UploadController uploadController = new UploadController(barcodeService, productService);
        MockMultipartFile file=new MockMultipartFile("file","","json","{\"json\": \"someValue\"}".getBytes());
        MockMvc mockMvc=standaloneSetup(uploadController).build();

        //when
        mockMvc.perform(multipart("/barcode").file(file))
            //then
                .andExpect(redirectedUrl("/barcode"))
                .andExpect(flash().attributeExists("code"))
                .andExpect(flash().attribute("code","CODE NOT FOUND, TRY AGAIN"));


    }

    @Test
    public void codeFoundTest() throws Exception
    {
        //given
        BarcodeService barcodeService = mock(BarcodeService.class);
        final String barcode="1234567890";
        when(barcodeService.getBarcode(any(MultipartFile.class))).thenReturn(barcode);
        ProductService productService = mock(ProductService.class);
        UploadController uploadController = new UploadController(barcodeService, productService);
        MockMultipartFile file=new MockMultipartFile("file","","json","{\"json\": \"someValue\"}".getBytes());
        MockMvc mockMvc=standaloneSetup(uploadController).build();

        //when
        mockMvc.perform(multipart("/barcode").file(file))
                //then
                .andExpect(redirectedUrl("/barcode/"+barcode));
    }
}
