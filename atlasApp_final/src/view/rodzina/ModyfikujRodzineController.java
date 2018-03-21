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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AtlasZwierzat;
import model.Rodzina;
import model.Typ;
import util.ObslugaAlertow;
import util.SprawdzIndeks;
import view.EdycjaWygladController;

/**
 *
 * @author Dell
 */
public class ModyfikujRodzineController implements Initializable {
    private Rodzina rodzinaTemp = new Rodzina();
    private AtlasZwierzat atlas = new AtlasZwierzat();
    
    @FXML private TextField nazwaRodziny;
    @FXML private TextField nazwaTypu;
    @FXML private TextArea cechaRodziny;
    
    /**
     * Inicjalizacja atlasu parametrem przekazanym z okna głównego programu.
     * @param atlas Atlas przekazany z głównego okna.
     * @param rodzinaTemp Rodzina przekaza z głównego okna.
     */
    public void initData(AtlasZwierzat atlas, Rodzina rodzinaTemp) {
        this.rodzinaTemp = rodzinaTemp;
        pokazDetaleRodzin(rodzinaTemp);
        this.atlas = atlas;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Metoda odpowiedzialna za uzupełnianie pól tekstowych informacjami uzyskanymi z obiektu.
     * @param rodzinaTemp Modyfikowana rodzina.
     */
    public void pokazDetaleRodzin(Rodzina rodzinaTemp) {
        nazwaRodziny.setText(rodzinaTemp.getNazwaRodziny());
        nazwaTypu.setText(rodzinaTemp.getTypRodziny().getNazwaTypu());
        cechaRodziny.setText(rodzinaTemp.getCechaRodziny());
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
     * Metoda odpowiedzialna za dodawanie rodziny do atlasu. Sprawdza szereg warunków, po czym
     * uruchamia główne okno programu przekazując atlas po dodaniu rodziny.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoZapisz(ActionEvent event) throws IOException {
        if (!nazwaRodziny.getText().equals("")) {

            int idTypu = SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), nazwaTypu.getText());
            int idRodziny = SprawdzIndeks.sprawdzIndeksRodziny(atlas.getListaTypow(), rodzinaTemp);

            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).setCechaRodziny(cechaRodziny.getText());
            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).setNazwaRodziny(nazwaRodziny.getText());

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
            ObslugaAlertow.niepelneDane("Nazwa rodziny musi zostać podana ..");
        }    
    }
}
