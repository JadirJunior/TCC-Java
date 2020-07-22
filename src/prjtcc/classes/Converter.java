/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Jadir
 */
public class Converter {
    public static byte[] imageToBytes(BufferedImage image, File file) throws IOException {
        byte[] imagem_byte = null;
       // BufferedImage imagem = ImageIO.read(file);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", stream);
            InputStream in = new ByteArrayInputStream(stream.toByteArray());
            imagem_byte = stream.toByteArray();
            return imagem_byte;
    }
    
    public static void ByteToImage(byte[] imagem_byte) throws IOException {
        InputStream in = new ByteArrayInputStream(imagem_byte);
	BufferedImage buff = ImageIO.read(in);
        Funcionario.setFoto(buff);
    }
}
