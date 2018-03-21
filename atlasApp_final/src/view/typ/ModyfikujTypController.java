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
public class ModyfikujTypController implements Initializable {

    private Typ typTemp = new Typ();
    private AtlasZwierzat atlas = new AtlasZwierzat();
    
    @FXML private TextField nazwaTypu;
    @FXML private TextField liczbaZwierzat;
    @FXML private TextArea opisBudowy;
    
    /**
     * Inicjalizacja atlasu parametrem przekazanym z okna głównego programu.
     * @param atlas Atlas przekazany z głównego okna.
     * @param typTemp Typ przekazany z głównego okna programu.
     */
    public void initData(AtlasZwierzat atlas, Typ typTemp)
    {
        this.atlas = atlas;
        this.typTemp = typTemp;
        uzupelnijPolaTyp(typTemp);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Metoda odpowiedzialna za uzupełnianie pól tekstowych informacjami uzyskanymi z obiektu.
     * @param typTemp  Modyfikowany typ.
     */
    public void uzupelnijPolaTyp(Typ typTemp) {
        nazwaTypu.setText(typTemp.getNazwaTypu());
        liczbaZwierzat.setText(Integer.toString(typTemp.getLiczbaZwierzat()));
        opisBudowy.setText(typTemp.getOpisBudowy());
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
        if (!typTemp.getNazwaTypu().equals("")) {
            int id = SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), typTemp.getNazwaTypu());
            
            atlas.getListaTypow().get(id).setNazwaTypu(nazwaTypu.getText());
            atlas.getListaTypow().get(id).setOpisBudowy(opisBudowy.getText());
            try {
                atlas.getListaTypow().get(id).setLiczbaZwierzat(Integer.parseInt(liczbaZwierzat.getText()));
            } catch (Exception e) {
                atlas.getListaTypow().get(id).setLiczbaZwierzat(0);
                ObslugaAlertow.niepoprawneDane("Liczba zwierząt  nie została podana prawidłowo ..");
            }
                        
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
            ObslugaAlertow.niepelneDane("Nie podałeś nazwy typu ..");
        }
    }
        
}
