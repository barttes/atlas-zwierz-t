/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.rodzina;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AtlasZwierzat;
import model.Rodzina;
import model.Typ;
import util.ObslugaAlertow;
import util.SprawdzIndeks;
import view.EdycjaWygladController;
import view.typ.DodajTypController;

/**
 *
 * @author Dell
 */
public class DodajRodzineController implements Initializable {
    private Rodzina rodzinaTemp = new Rodzina();
    private AtlasZwierzat atlas = new AtlasZwierzat();
    
    @FXML private TextField nazwaRodziny;
    @FXML private ComboBox nazwaTypu;
    @FXML private TextArea cechaRodziny;
    
    /**
     * Inicjalizacja atlasu parametrem przekazanym z okna głównego programu.
     * @param atlas Atlas przekazany z głównego okna.
     */
    public void initData(AtlasZwierzat atlas)
    {
        this.atlas = atlas;
        uzupelnijComboBoxy(atlas);
    }
    
    /**
     * Uzupełnienie listy rozwijalnej typami atlasu.
     * @param atlas 
     */
    public void uzupelnijComboBoxy(AtlasZwierzat atlas) {
        for (Typ t: atlas.getListaTypow()) {
            nazwaTypu.getItems().add(t.getNazwaTypu());
        } 
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
     * Metoda uruchamiana po naciśnięciu przycisku dodaj typ. Uruchamia okno dodawania typu.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoDodajTyp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/typ/DodajTypWyglad.fxml"));
        Parent edycjaWygladParent = loader.load();
        DodajTypController controller = loader.getController();
        controller.initData(atlas);
        Scene edycjaWygladScene = new Scene(edycjaWygladParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edycjaWygladScene);
        window.show();
    }
    
    /**
     * Metoda odpowiedzialna za dodawanie rodziny do atlasu. Sprawdza szereg warunków, po czym
     * uruchamia główne okno programu przekazując atlas po dodaniu rodziny.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoZapisz(ActionEvent event) throws IOException {
        if (nazwaTypu.getValue()!=null && !nazwaRodziny.getText().equals("")) {
            int idTypu = SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), nazwaTypu.getValue().toString());
            boolean czyRodzinaIstnieje = SprawdzIndeks.czyRodzinaIstnieje(atlas.getListaTypow(), nazwaRodziny.getText());
            
            rodzinaTemp.setCechaRodziny(cechaRodziny.getText());
            rodzinaTemp.setNazwaRodziny(nazwaRodziny.getText());
            
            if (!czyRodzinaIstnieje) {
                rodzinaTemp.setTypRodziny(atlas.getListaTypow().get(idTypu));
                atlas.getListaTypow().get(idTypu).dodajRodzine(rodzinaTemp);
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
                ObslugaAlertow.elementJuzIstnieje("Nazwa rodziny musi być unikalna ..");
            }
        } else {
            ObslugaAlertow.niepelneDane("Typ musi zostać wybrany i nazwa rodziny uzupełniona ..");
        }
    }
}
