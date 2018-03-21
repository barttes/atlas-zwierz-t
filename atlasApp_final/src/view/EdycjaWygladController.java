/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.gatunek.ModyfikujGatunekController;
import view.gatunek.DodajGatunekController;
import view.gatunek.ModyfikujKrzyzowkeController;
import view.rodzina.DodajRodzineController;
import view.rodzina.ModyfikujRodzineController;
import view.typ.ModyfikujTypController;
import view.typ.DodajTypController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.AtlasZwierzat;
import model.GatKrzyz;
import model.Rodzina;
import model.Typ;
import util.GetWszystkie;
import util.ObslugaAlertow;
import util.ObslugaDaty;
import util.SprawdzIndeks;
import util.ZapiszWczytajAtlas;

/**
 *
 * @author Dell
 */
public class EdycjaWygladController implements Initializable {
    
    private AtlasZwierzat atlas = new AtlasZwierzat();
    
    //zawartość zakładki dot. całego atlasu
    @FXML private Button zapiszAtlas;
    @FXML private Button wczytajAtlas;
    @FXML private TreeView drzewoAtlas;
    
    //zawartość zakładki dot. edycji typów
    @FXML private TableView<Typ> tabelaTypy;
    @FXML private TableColumn<Typ, String> kolumnaTypy;
    @FXML private Label nazwaTypu;
    @FXML private Label szacowanaLiczba;
    @FXML private TextArea opisBudowy;
    @FXML private Button modyfikujTyp;
    @FXML private Button usunTyp;
    @FXML private Button dodajTyp;
    
    //zawartość zakładki dot. edycji rodzin
    @FXML private TableView<Rodzina> tabelaRodziny;
    @FXML private TableColumn<Rodzina, String> kolumnaRodziny;
    @FXML private Label nazwaTypuOdRodziny;
    @FXML private Label nazwaRodziny;
    @FXML private Label sredniaLiczbaKonczyn;
    @FXML private TextArea cechaRodziny;
    @FXML private Button modyfikujOdRodziny;
    @FXML private Button usunOdRodziny;
    @FXML private Button dodajOdRodziny;
    
    //zawartość zakładki dot. edycji gatunków
    @FXML private TableView<GatKrzyz> tabelaGatunki;
    @FXML private TableColumn<GatKrzyz, String> kolumnaGatunki;
    @FXML private Label nazwaTypuOdGatunku;
    @FXML private Label nazwaRodzinyOdGatunku;
    @FXML private Label nazwaGatunku;
    @FXML private Label liczbaKonczyn;
    @FXML private Label dataOdkrycia;
    @FXML private Label slawnyPrzedstawiciel;
    @FXML private Button modyfikujOdGatunku;
    @FXML private Button usunOdGatunku;
    @FXML private ImageView zdjecieGatunku;
    @FXML private CheckBox krzyzowkaCB;
    @FXML private Label listaGatunkow;
    @FXML private Button dodajOdGatunku;
    @FXML private TextField wyszukajGatunek;
    
    /**
     * Metoda wczytująca dane atlasu do głównej sceny aplikacji w komponent "TreeView". Wywoływana po:
     * zmianie danych i dodawaniu nowych danych. Wczytuje również dane do tabel typów, rodzin i gatunków.
     * @param atlas Aktualny atlas
     */
    public void initData(AtlasZwierzat atlas) {
        this.setAtlas(atlas);
        TreeItem<String> rootItem = new TreeItem<String>("Atlas");
        rootItem.setExpanded(true);
        for (Typ t: atlas.getListaTypow()) {
            TreeItem<String> itemTyp = new TreeItem<String> (t.getNazwaTypu());
            itemTyp.setExpanded(true);
            rootItem.getChildren().add(itemTyp);
            for (Rodzina r: t.getListaRodzin()) {
                TreeItem<String> itemRodzina = new TreeItem<String> (r.getNazwaRodziny());
                itemRodzina.setExpanded(true);
                itemTyp.getChildren().add(itemRodzina);
                for (GatKrzyz g: r.getListaGatKrzyz()) {
                    TreeItem<String> itemGatKrzyz = new TreeItem<String> (g.getNazwaGatunku());
                    itemRodzina.getChildren().add(itemGatKrzyz);
                }
            }
        } 
        drzewoAtlas.setRoot(rootItem);
        tabelaTypy.setItems(FXCollections.observableArrayList(atlas.getListaTypow()));
        tabelaRodziny.setItems(FXCollections.observableArrayList(GetWszystkie.listaRodzin(this.getAtlas())));
        tabelaGatunki.setItems(FXCollections.observableArrayList(GetWszystkie.listaGatKrzyz(this.getAtlas(), wyszukajGatunek.getText())));
        if (atlas.getListaTypow().isEmpty()) {
            zapiszAtlas.setDisable(true);
        } else {
            zapiszAtlas.setDisable(false);
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData(this.getAtlas());
        zapiszAtlas.setDisable(true);
        
        kolumnaTypy.setCellValueFactory(new PropertyValueFactory<>("nazwaTypu"));        
        tabelaTypy.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> pokazDetaleTypow(newValue));
        kolumnaRodziny.setCellValueFactory(new PropertyValueFactory<>("nazwaRodziny"));
        tabelaRodziny.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> pokazDetaleRodzin(newValue));
        kolumnaGatunki.setCellValueFactory(new PropertyValueFactory<>("nazwaGatunku"));
        tabelaGatunki.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> pokazDetaleGatunkow(newValue));
    }
    
    /**
     * Metoda wywoływana po naciśnięsiu na któryś z wierszy tabeli w karcie typów.
     * Odpowiedzialna za pokazanie szczegółowych informacji o sfokusowanym typie.
     * @param typ Typ którego szczegóły mają być zaprezentowane
     */
    public void pokazDetaleTypow(Typ typ) {
        if (typ!=null) {
            modyfikujTyp.setDisable(false);
            usunTyp.setDisable(false);
            nazwaTypu.setText(typ.getNazwaTypu());
            szacowanaLiczba.setText(Integer.toString(typ.getLiczbaZwierzat()));
            opisBudowy.setText(typ.getOpisBudowy());
        }
    }
    
    /**
     * Metoda wywoływana po naciśnięsiu na któryś z wierszy tabeli w karcie rodzin.
     * Odpowiedzialna za pokazanie szczegółowych informacji o sfokusowanej rodzinie.
     * @param rodzina Rodzina której szczegóły mają być zaprezentowane
     */
    public void pokazDetaleRodzin(Rodzina rodzina) {
        if (rodzina!=null) {
            modyfikujOdRodziny.setDisable(false);
            usunOdRodziny.setDisable(false);
            nazwaTypuOdRodziny.setText(rodzina.getTypRodziny().getNazwaTypu());
            nazwaRodziny.setText(rodzina.getNazwaRodziny());
            sredniaLiczbaKonczyn.setText(Integer.toString(rodzina.getSredniaLiczbaKonczyn()));
            cechaRodziny.setText(rodzina.getCechaRodziny());
        }
    }
    
    /**
     * Metoda wywoływana po naciśnięsiu na któryś z wierszy tabeli w karcie gatunków.
     * Odpowiedzialna za pokazanie szczegółowych informacji o sfokusowanym gatunku/krzyżówce.
     * @param gatunek Gatunek którego szczegóły mają być zaprezentowane
     */
    public void pokazDetaleGatunkow(GatKrzyz gatunek) {
        if (gatunek!=null) {
            modyfikujOdGatunku.setDisable(false);
            usunOdGatunku.setDisable(false);
            nazwaTypuOdGatunku.setText(gatunek.getTypGatunku().getNazwaTypu());
            nazwaRodzinyOdGatunku.setText(gatunek.getRodzinaGatunku().getNazwaRodziny());
            nazwaGatunku.setText(gatunek.getNazwaGatunku());
            liczbaKonczyn.setText(Integer.toString(gatunek.getLiczbaKonczyn()));
            dataOdkrycia.setText(ObslugaDaty.formatuj(gatunek.getDataOdkrycia()));
            slawnyPrzedstawiciel.setText(gatunek.getSlawnyPrzedstawiciel());
            if (gatunek.isKrzyz()) {
                krzyzowkaCB.setSelected(true);
                listaGatunkow.setText(gatunek.listaGatunkowDoWyswietlenia(gatunek.getListaGatunkow()));
            } else {
                krzyzowkaCB.setSelected(false);
                listaGatunkow.setText("");
            }
            if (gatunek.getZdjecie()!=null) {
                zdjecieGatunku.setImage(gatunek.getZdjecie());
            } else {
                zdjecieGatunku.setImage(null);
            }
        }
    }
    
    /**
     * Metoda wywoływana po wpisaniu ciągu znaków w pole tekstowe służące do wszyukiwania gatunków. Odpowiada za
     * odświeżanie tabeli gatunków/krzyzówek.
     * @param event 
     * @throws IOException 
     */
    public void wyszukiwanieGatunku(ActionEvent event) throws IOException {
        tabelaGatunki.setItems(FXCollections.observableArrayList(GetWszystkie.listaGatKrzyz(this.getAtlas(), wyszukajGatunek.getText())));
    }

    /**
     * Metoda jest odpowiedzialna za załadowanie nowej sceny do okna aplikacji.
     * Załadowana scena różni się w zależności który przycisk spowodował wywołanie tej metody.
     * Otwiera sceny dodawania: typu, rodziny, gatunku/krzyżówki.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoDodaj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Scene edycjaWygladScene;
        if (event.getSource().equals(dodajTyp)) {
            loader.setLocation(getClass().getResource("typ/DodajTypWyglad.fxml"));
            Parent edycjaWygladParent = loader.load();
            edycjaWygladScene = new Scene(edycjaWygladParent); 
            DodajTypController controller = loader.getController();
            controller.initData(this.getAtlas());
        } else if (event.getSource().equals(dodajOdRodziny)) {
            loader.setLocation(getClass().getResource("rodzina/DodajRodzineWyglad.fxml"));
            Parent edycjaWygladParent = loader.load();
            edycjaWygladScene = new Scene(edycjaWygladParent);
            DodajRodzineController controller = loader.getController();
            controller.initData(this.getAtlas());     
        } else {
            loader.setLocation(getClass().getResource("gatunek/DodajGatunekWyglad.fxml"));
            Parent edycjaWygladParent = loader.load();
            edycjaWygladScene = new Scene(edycjaWygladParent);
            DodajGatunekController controller = loader.getController();
            controller.initData(this.getAtlas());   
        }
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edycjaWygladScene);
        window.show();
    }
    
    /**
     * Metoda jest odpowiedzialna za załadowanie nowej sceny do okna aplikacji.
     * Załadowana scena różni się w zależności który przycisk spowodował wywołanie tej metody.
     * Otwiera sceny modyfikacji: typu, rodziny, gatunku, krzyżówki.
     * @param event
     * @throws IOException 
     */
    public void zmienOknoModyfikuj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Scene edycjaWygladScene;
        if (event.getSource().equals(modyfikujTyp)) {
            loader.setLocation(getClass().getResource("typ/ModyfikujTypWyglad.fxml"));
            Parent edycjaWygladParent = loader.load();
            edycjaWygladScene = new Scene(edycjaWygladParent);
            ModyfikujTypController controller = loader.getController();
            controller.initData(this.getAtlas(), tabelaTypy.getSelectionModel().getSelectedItem());
        } else if (event.getSource().equals(modyfikujOdRodziny)) {
            loader.setLocation(getClass().getResource("rodzina/ModyfikujRodzineWyglad.fxml"));
            Parent edycjaWygladParent = loader.load();
            edycjaWygladScene = new Scene(edycjaWygladParent);
            ModyfikujRodzineController controller = loader.getController();
            controller.initData(this.getAtlas(), tabelaRodziny.getSelectionModel().getSelectedItem());  
        } else {
            if (!krzyzowkaCB.isSelected()) {
                loader.setLocation(getClass().getResource("gatunek/ModyfikujGatunekWyglad.fxml"));
                Parent edycjaWygladParent = loader.load();
                edycjaWygladScene = new Scene(edycjaWygladParent);
                ModyfikujGatunekController controller = loader.getController();
                controller.initData(this.getAtlas(), tabelaGatunki.getSelectionModel().getSelectedItem()); 
            } else {
                loader.setLocation(getClass().getResource("gatunek/ModyfikujKrzyzowkeWyglad.fxml"));
                Parent edycjaWygladParent = loader.load();
                edycjaWygladScene = new Scene(edycjaWygladParent);
                ModyfikujKrzyzowkeController controller = loader.getController();
                controller.initData(this.getAtlas(), tabelaGatunki.getSelectionModel().getSelectedItem());
            }
        }
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edycjaWygladScene);
        window.show();
    }
    
    /**
     * Metoda usuwa typ z atlasu. Obsługa przycisku "usuń" w zakładce typów.
     * @param event
     * @throws IOException 
     */
    public void usunTyp(ActionEvent event) throws IOException {
        getAtlas().usunTyp(tabelaTypy.getSelectionModel().getSelectedItem());
        
        initData(this.getAtlas());
    }
    
    /**
     * Metoda usuwa rodzinę z atlasu. Obsługa przycisku "usuń" w zakładce rodzin.
     * @param event
     * @throws IOException 
     */
    public void usunRodzine(ActionEvent event) throws IOException {
        getAtlas().getListaTypow().get(SprawdzIndeks.sprawdzIndeksTypu(getAtlas().getListaTypow(), tabelaRodziny.getSelectionModel().getSelectedItem().getTypRodziny().getNazwaTypu())).getListaRodzin().remove(tabelaRodziny.getSelectionModel().getSelectedItem());
        initData(this.getAtlas());
    }
    
    /**
     * Metoda usuwająca gatunek z atlasu.
     * @param event
     * @throws IOException 
     */
    public void usunGatunek(ActionEvent event) throws IOException {
        int idTypu = SprawdzIndeks.sprawdzIndeksTypu(getAtlas().getListaTypow(), tabelaGatunki.getSelectionModel().getSelectedItem().getTypGatunku().getNazwaTypu());
        int idRodziny = SprawdzIndeks.sprawdzIndeksRodziny(getAtlas().getListaTypow(), tabelaGatunki.getSelectionModel().getSelectedItem().getRodzinaGatunku().getNazwaRodziny());
        getAtlas().getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).setLiczbaKonczynOgolem(-tabelaGatunki.getSelectionModel().getSelectedItem().getLiczbaKonczyn(), -1);
        getAtlas().getListaTypow().get(idTypu).getListaRodzin().get(idRodziny).usunGatKrzyz(tabelaGatunki.getSelectionModel().getSelectedItem());
        initData(this.getAtlas());
    }
    
    /**
     * Metoda obsługuje naciśnięcie przycisku "zapisz atlas".
     * @param event 
     */
    public void zapiszAtlas(ActionEvent event){
        ZapiszWczytajAtlas.zapisz(this.getAtlas(), event);
    }
    
    /**
     * Metoda obsługuje naciśnięcie przycisku "wczytaj atlas". Metoda najpierw sprawdza czy atlas jest pusty i sygnalizuje użytkownikowi że może utracić dotychczas wprowadzone dane.
     * @param event 
     */
    public void wczytajAtlas(ActionEvent event) {
        if (getAtlas().getListaTypow().isEmpty()) {
            this.setAtlas(ZapiszWczytajAtlas.wczytaj(event));
            initData(this.getAtlas());
        } else {
            if (ObslugaAlertow.potwierdzenie("Wczytanie nowego atlasu spowoduje usunięcie wprowadzonych danych ..") == 1) {
                this.setAtlas(ZapiszWczytajAtlas.wczytaj(event));
                initData(this.getAtlas());
            }
        }
    }

    /**
     * @return the atlas
     */
    public AtlasZwierzat getAtlas() {
        return atlas;
    }

    /**
     * @param atlas the atlas to set
     */
    public void setAtlas(AtlasZwierzat atlas) {
        this.atlas = atlas;
    }
}
