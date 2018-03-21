/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Dell
 */
public class ObslugaAlertow {
    public static void niepelneDane(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Niepełne dane!");
        alert.setContentText(s);
        alert.showAndWait();
    }
    
    public static void niepoprawneDane(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Niepoprawne dane!");
        alert.setContentText(s);
        alert.showAndWait();
    }
    
    public static void elementJuzIstnieje(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Niepoprawne dane!");
        alert.setContentText(s);
        alert.showAndWait();
    }
    
    public static void zapisPomyslny(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zapis pomyślny");
        alert.setContentText(s);
        alert.showAndWait();
    }
    
    public static int potwierdzenie(String s) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zapis pomyślny");
        alert.setContentText(s);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return 1;
        } else {
            return 0;
        }
    }
}
