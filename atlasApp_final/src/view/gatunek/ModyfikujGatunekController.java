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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.AtlasZwierzat;
import model.GatKrzyz;
import util.ImgToBase64;
import util.ObslugaAlertow;
import util.ObslugaDaty;
import util.SprawdzIndeks;
import view.EdycjaWygladController;

/**
 *
 * @author Dell
 */
public class ModyfikujGatunekController implements Initializable {
    private AtlasZwierzat atlas = new AtlasZwierzat();
    private GatKrzyz gatunekTemp;
    private String imgBase64;
    
    @FXML private TextField nazwaRodziny;
    @FXML private TextField nazwaTypu;
    @FXML private TextField nazwaGatunku;
    @FXML private TextField liczbaKonczyn;
    @FXML private TextField dataOdkrycia;
    @FXML private TextField slawnyPrzedstawiciel;
    @FXML private ImageView zdjecieGatunku;
    
    /**
     * Inicjalizacja atlasu parametrem przekazanym z okna głównego programu.
     * @param atlas Atlas przekazany z głównego okna.
     * @param gatunekTemp Gatunek przekazany z głównego okna.
     */
    public void initData(AtlasZwierzat atlas, GatKrzyz gatunekTemp){
        this.gatunekTemp = gatunekTemp;
        pokazDetaleGatunku(gatunekTemp);
        this.atlas = atlas;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Metoda uzupełniająca pola tekstowe wartościami uzyskanymi z obiektu.
     * @param gatunekTemp Gatunek przekazany do metody
     */
    public void pokazDetaleGatunku(GatKrzyz gatunekTemp) {
        nazwaRodziny.setText(gatunekTemp.getRodzinaGatunku().getNazwaRodziny());
        nazwaTypu.setText(gatunekTemp.getTypGatunku().getNazwaTypu());
        nazwaGatunku.setText(gatunekTemp.getNazwaGatunku());
        liczbaKonczyn.setText(Integer.toString(gatunekTemp.getLiczbaKonczyn()));
        slawnyPrzedstawiciel.setText(gatunekTemp.getSlawnyPrzedstawiciel());
        dataOdkrycia.setText(ObslugaDaty.formatuj(gatunekTemp.getDataOdkrycia()));
        zdjecieGatunku.setImage(gatunekTemp.getZdjecie());
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
     * Metoda odpowiedzialna za dodawanie gatunku bądź krzyżówki do atlasu. Sprawdza szereg warunków, po czym
     * uruchamia główne okno programu przekazując atlas po dodaniu gatunku.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoZapisz(ActionEvent event) throws IOException {
        if (!nazwaGatunku.getText().equals("")) {
            
            int idTypu = SprawdzIndeks.sprawdzIndeksTypu(atlas.getListaTypow(), nazwaTypu.getText());
            int idRodziny = SprawdzIndeks.sprawdzIndeksRodziny(atlas.getListaTypow(), nazwaRodziny.getText());
            int idGatunku = SprawdzIndeks.sprawdzIndeksGatunku(atlas.getListaTypow(), gatunekTemp);

            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku).setNazwaGatunku(nazwaGatunku.getText());
            int liczbaKonczynOld = gatunekTemp.getLiczbaKonczyn();
            try {
                atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku).setLiczbaKonczyn(Integer.parseInt(liczbaKonczyn.getText()));
                atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).setLiczbaKonczynOgolem(-liczbaKonczynOld, 0);
                atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).setLiczbaKonczynOgolem(Integer.parseInt(liczbaKonczyn.getText()), 0);
            } catch (Exception e) {
                atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku).setLiczbaKonczyn(liczbaKonczynOld);
                ObslugaAlertow.niepoprawneDane("Liczba kończyn nie została podana prawidłowo ..");
            }
            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku) .setSlawnyPrzedstawiciel(slawnyPrzedstawiciel.getText());
            LocalDate dataOld = gatunekTemp.getDataOdkrycia();
            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku).setDataOdkrycia(ObslugaDaty.parsuj(dataOdkrycia.getText(), dataOld));
            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku).setZdjecie(zdjecieGatunku.getImage());
            atlas.getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).getListaGatKrzyz().get(idGatunku).setImgString(imgBase64);

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
            ObslugaAlertow.niepelneDane("Nazwa gatunku musi zostać podana ..");
        }
    }
}
