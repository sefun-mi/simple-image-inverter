package com.salty.inverter;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/*
 * @author sefun-mi
 * @createdOn 26/06/2025
 */

@Service
public class InvertService {

    public byte[] invert(MultipartFile imageFile){
        BufferedImage img = null;

        //inversion code from GeeksForGeeks
        // read image
        try {
            img = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
        }
        catch (IOException e) {
            System.out.println(e);
        }

        // Get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        // Convert to negative
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);
            }
        }

        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "png", outputStream);
            return outputStream.toByteArray();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }



    }
}