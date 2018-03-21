/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Dell
 */
public class ImgToBase64 {
    
    /**
     * Metoda konwertuje wskazany plik ze zdjęciem do formatu Base64.
     * @param file
     * @return Ciąg znaków odpowiadający zdjęciu.
     */
    public static String encode(File file) {
        String base64Image = "";
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	return base64Image;
    }
    
    /**
     * Metoda dekoduje wskazany ciąg znaków odpowiadający zdjęciu i zwraca go w postaci Image.
     * @param base64Image
     * @return Zdjęcie.
     * @throws IOException 
     */
    public static Image decoder(String base64Image) throws IOException {
        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageByteArray));
        return SwingFXUtils.toFXImage(img, null);
    }
}
