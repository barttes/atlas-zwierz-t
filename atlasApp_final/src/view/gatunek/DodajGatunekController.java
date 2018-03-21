/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gatunek;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.AtlasZwierzat;
import model.GatKrzyz;
import model.Gatunek;
import model.Krzyzowka;
import model.Rodzina;
import model.Typ;
import util.ImgToBase64;
import util.ObslugaAlertow;
import util.ObslugaDaty;
import util.SprawdzIndeks;
import view.EdycjaWygladController;
import view.rodzina.DodajRodzineController;
import view.typ.DodajTypController;

/**
 *
 * @author Dell
 */
public class DodajGatunekController implements Initializable {
    private AtlasZwierzat atlas = new AtlasZwierzat();
    private GatKrzyz gatKrzyzTemp;
    private String imgBase64;
    
    @FXML private ComboBox nazwaRodziny;
    @FXML private ComboBox nazwaTypu;
    @FXML private TextField nazwaGatunku;
    @FXML private TextField liczbaKonczyn;
    @FXML private TextField dataOdkrycia;
    @FXML private TextField listaGatunkow;
    @FXML private TextField slawnyPrzedstawiciel;
    @FXML private CheckBox krzyzowkaCB;
    @FXML private ImageView zdjecieGatunku;
    
    /**
     * Inicjalizacja atlasu parametrem przekazanym z okna głównego programu.
     * @param atlas Atlas przekazany z głównego okna.
     */
    public void initData(AtlasZwierzat atlas) {
        this.atlas = atlas;
        uzupelnijComboBoxTyp(atlas);
    }
    
    
    /**
     * Uzupełnienie listy rozwijalnej typami atlasu.
     * @param atlas 
     */
    public void uzupelnijComboBoxTyp(AtlasZwierzat atlas) {
        for (Typ t: atlas.getListaTypow()) {
            nazwaTypu.getItems().add(t.getNazwaTypu());
        } 
    }
    
    /**
     * Uzupełnienie listy rozwijalnej rodzinami atlasu.
     * @param atlas 
     */
    public void uzupelnijComboBoxRodzina(AtlasZwierzat atlas, String typ) {
        int idTypu = SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), typ);
        nazwaRodziny.getItems().clear();
        for (Rodzina r: atlas.getListaTypow().get(idTypu).getListaRodzin()) {
                nazwaRodziny.getItems().add(r.getNazwaRodziny());
        }
    }
    
    /**
     * Metoda uruchamiana po wybraniu typu z listy rozwijalnej. Aktywuje możliwość 
     * wyboru rodziny.
     * @param event
     * @throws IOException 
     */
    public void aktywujWyborRodziny(ActionEvent event) throws IOException {
        nazwaRodziny.setDisable(false);
        uzupelnijComboBoxRodzina(atlas, nazwaTypu.getValue().toString());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Metoda umożliwa dokonywanie edycji w polu tekstowym dotyczącym wpisania listy: krzyżówek gatunków.
     * @param event
     * @throws IOException 
     */
    public void aktywujUzupelnienieListyGatunkow(ActionEvent event) throws IOException {
        if (krzyzowkaCB.isSelected()) {
            listaGatunkow.setDisable(false);
        } else {
            listaGatunkow.setDisable(true);
        }
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
     * Metoda uruchamiana po naciśnięciu przycisku dodaj typ. Uruchamia okno dodawania rodziny.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoDodajRodzine(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/rodzina/DodajRodzineWyglad.fxml"));
        Parent edycjaWygladParent = loader.load();
        DodajRodzineController controller = loader.getController();
        controller.initData(atlas);
        Scene edycjaWygladScene = new Scene(edycjaWygladParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edycjaWygladScene);
        window.show();
    }
    
    /**
     * Metoda odpowiedzialna za dodawanie zdjęcia do gatunku atlasu. Uruchamia okno z wyborem pliku, a następnie
     * metodę, która dokona konwersji pliku zdjęciowego.
     * @param event
     * @throws IOException 
     * @throws Exception
     */
    public void dodajZdjecie(ActionEvent event) throws IOException, Exception {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki jpg", "*.jpg"), new FileChooser.ExtensionFilter("Pliki png", "*.png"));
        File file = fc.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        if (file != null) {
            imgBase64 = ImgToBase64.encode(file);
            BufferedImage bufferedImage = ImageIO.read(file);
            Image zdj = SwingFXUtils.toFXImage(bufferedImage, null);
            zdjecieGatunku.setImage(zdj);
        }
    }
    
    /**
     * Metoda odpowiedzialna za dodawanie gatunku bądź krzyżówki do atlasu. Sprawdza szereg warunków, po czym
     * uruchamia główne okno programu przekazując atlas po dodaniu gatunku.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoZapisz(ActionEvent event) throws IOException {
        if (nazwaTypu.getValue()!=null && nazwaRodziny.getValue()!=null && !nazwaGatunku.getText().equals("")) {
            boolean czyGatunekIstnieje = SprawdzIndeks.czyGatunekIstnieje(atlas.getListaTypow(), nazwaGatunku.getText());
            
            if (!czyGatunekIstnieje) {
                int idTypu = SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), nazwaTypu.getValue().toString());
                int idRodziny = SprawdzIndeks.sprawdzIndeksRodziny(atlas.getListaTypow(), nazwaRodziny.getValue().toString());
                
                if (krzyzowkaCB.isSelected()) {
                    gatKrzyzTemp = new Krzyzowka(nazwaGatunku.getText(), atlas.getListaTypow().get(idTypu), atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny));
                    gatKrzyzTemp.setKrzyz(true);
                    try {
                        List<String> listaTemp = new ArrayList<String>(Arrays.asList(listaGatunkow.getText().split(",")));
                        gatKrzyzTemp.setListaGatunkow(listaTemp);
                    } catch (Exception e) {
                        ObslugaAlertow.niepoprawneDane("Lista gatunków została podana nieprawidłowo ..");
                    }
                    
                } else {
                    gatKrzyzTemp = new Gatunek(nazwaGatunku.getText(), atlas.getListaTypow().get(idTypu), atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny));
                }
                    
                try {
                    gatKrzyzTemp.setLiczbaKonczyn(Integer.parseInt(liczbaKonczyn.getText()));
                    atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).setLiczbaKonczynOgolem(gatKrzyzTemp.getLiczbaKonczyn(), 1);
                } catch (Exception e) {
                    gatKrzyzTemp.setLiczbaKonczyn(0);
                    ObslugaAlertow.niepoprawneDane("Liczba kończyn nie została podana prawidłowo ..");
                }
                
                gatKrzyzTemp.setZdjecie(zdjecieGatunku.getImage());
                gatKrzyzTemp.setImgString(imgBase64);
                gatKrzyzTemp.setSlawnyPrzedstawiciel(slawnyPrzedstawiciel.getText());
                gatKrzyzTemp.setDataOdkrycia(ObslugaDaty.parsuj(dataOdkrycia.getText()));

                atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).dodajGatKrzyz(gatKrzyzTemp);
                
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
                ObslugaAlertow.elementJuzIstnieje("Nazwa gatunku musi być unikalna ..");
            }
        } else {
            ObslugaAlertow.niepelneDane("Typy i rodziny muszą zostać wybrane, a nazwa gatunku uzupełniona ..");
        }
    }
}
