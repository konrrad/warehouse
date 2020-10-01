package com.warehouse.readers;

import com.google.zxing.NotFoundException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EANReaderTest {

    EANReader reader=new EANReader();
    final String resourcesPath="./src/test/resources/EANReaderTestResources/";

    @Test
    public void shouldReadStandardCode() throws IOException, NotFoundException {
        //given
        final String path=resourcesPath+"standard.jpg";
        InputStream stream=new FileInputStream(path);
        //when
        String result=reader.read(stream);

        //then
        assertEquals(result,"5902078000102");
    }

    @Test
    public void shouldReadSmallCode() throws IOException, NotFoundException {
        //given
        final String path=resourcesPath+"small_code.png";
        InputStream stream=new FileInputStream(path);
        //when
        String result=reader.read(stream);

        //then
        assertEquals(result,"5902078000102");
    }

    @Test
    public void farIMGTest() throws IOException, NotFoundException {
        //given
        final String path=resourcesPath+"far_code.jpg";
        InputStream stream=new FileInputStream(path);
        //when
        String result=reader.read(stream);

        //then
        assertEquals(result,"5902078000102");
    }

    @Test
    public void noCodeTest() throws IOException{
        //given
        final String path=resourcesPath+"no_code.jpg";
        InputStream stream=new FileInputStream(path);

        //when //then
        assertThrows(NotFoundException.class,()->reader.read(stream));
    }
}

