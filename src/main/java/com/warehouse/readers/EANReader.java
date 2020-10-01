package com.warehouse.readers;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;


@Component
public class EANReader implements IReader {
    @Override
    public String read(InputStream inputStream) throws NotFoundException, IOException {
        BufferedImage image=ImageIO.read(
                inputStream);
        BufferedImage smallImage=
                Scalr.resize(image,Scalr.Method.ULTRA_QUALITY,
                        153,204,Scalr.OP_ANTIALIAS);


        Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(
                DecodeHintType.class);
        tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS,
                EnumSet.allOf(BarcodeFormat.class));
        tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);


        BinaryBitmap binaryBitmap
                = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                                image)));



        Result result
                = new MultiFormatReader()
                .decode(binaryBitmap,tmpHintsMap);

        return result.getText();
    }
}
