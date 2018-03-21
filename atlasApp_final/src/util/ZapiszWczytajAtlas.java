/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AtlasZwierzat;

/**
 *
 * @author Dell
 */
public class ZapiszWczytajAtlas {
    
    /**
     * Metoda zapisująca atlas do pliku za pomocą serializacji binarnej.
     * Dostępne rozszerzenie zapisu: *.ser.
     * @param atlas Atlas, który ma zostać zapisany
     * @param event 
     */
    public static void zapisz(AtlasZwierzat atlas, ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki ser", "*.ser"));
            File file = fc.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());
            if (file != null) {
                String path = file.getPath();
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(atlas);
                ObslugaAlertow.zapisPomyslny("Zapis atlasu do pliku przepiegł pomyślnie ..");
                oos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metoda wczytująca atlas z pliku o rozszerzeniu *.ser.
     * @param event
     * @return Atlas, który udało się wczytać z pliku
     */
    public static AtlasZwierzat wczytaj(ActionEvent event) {
        AtlasZwierzat atlas = new AtlasZwierzat();
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki ser", "*.ser"));
            File file = fc.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
            if (file != null) {
                String path = file.getPath();
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
                atlas = (AtlasZwierzat) ois.readObject();
                ObslugaAlertow.zapisPomyslny("Wczytywanie atlasu przebiegło pomyślnie ..");
                ois.close();
            }
            return atlas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return atlas;
        } catch (IOException e) {
            e.printStackTrace();
            return atlas;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return atlas;
        } 
    }
}
