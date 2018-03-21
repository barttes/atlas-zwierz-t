/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.typ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AtlasZwierzat;
import model.Typ;
import util.ObslugaAlertow;
import util.SprawdzIndeks;
import view.EdycjaWygladController;


/**
 *
 * @author Dell
 */
public class DodajTypController implements Initializable {

    private Typ typTemp = new Typ();
    private AtlasZwierzat atlas = new AtlasZwierzat();
    
    @FXML private TextField nazwaTypu;
    @FXML private TextField liczbaZwierzat;
    @FXML private TextArea opisBudowy;
    
    /**
     * Inicjalizacja atlasu parametrem przekazanym z okna głównego programu.
     * @param atlas Atlas przekazany z głównego okna.
     */
    public void initData(AtlasZwierzat atlas) {
        this.atlas = atlas;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Metoda uruchamiana po naciśnięciu przycisku anuluj. Uruchamia główne okno programu.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoAnuluj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/EdycjaWyglad.fxml"));
        Parent edycjaWygladParent = loader.load();
        EdycjaWygladController controller = loader.getController();
        controller.initData(atlas);
        Scene edycjaWygladScene = new Scene(edycjaWygladParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edycjaWygladScene);
        window.show();
    }
    
    /**
     * Metoda odpowiedzialna za dodawanie typu do atlasu. Sprawdza szereg warunków, po czym
     * uruchamia główne okno programu przekazując atlas po dodaniu typu.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoZapisz(ActionEvent event) throws IOException {
        if (!nazwaTypu.getText().equals("")) {
            typTemp.setNazwaTypu(nazwaTypu.getText());
            typTemp.setOpisBudowy(opisBudowy.getText());
            try {
                typTemp.setLiczbaZwierzat(Integer.parseInt(liczbaZwierzat.getText()));
            } catch (Exception e) {
                typTemp.setLiczbaZwierzat(0);
                ObslugaAlertow.niepoprawneDane("Liczba zwierząt  nie została podana prawidłowo ..");
            }
            if (SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), typTemp.getNazwaTypu())==-1) {
                atlas.dodajTyp(typTemp);
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/EdycjaWyglad.fxml"));
                Parent edycjaWygladParent = loader.load();
                EdycjaWygladController controller = loader.getController();
                controller.initData(atlas);
                Scene edycjaWygladScene = new Scene(edycjaWygladParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(edycjaWygladScene);
                window.show();
            } else {
                ObslugaAlertow.elementJuzIstnieje("Nazwa typu musi być unikalna ..");
            }
            
        } else {
            ObslugaAlertow.niepelneDane("Nazwa typu nie została podana ..");
        }
    }
        
}
