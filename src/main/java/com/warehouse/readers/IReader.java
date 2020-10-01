package com.warehouse.readers;

import com.google.zxing.NotFoundException;

import java.io.IOException;
import java.io.InputStream;

public interface IReader {
    String read(InputStream stream) throws NotFoundException, IOException;
}
