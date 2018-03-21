/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import util.ImgToBase64;

/**
 *
 * @author Dell
 */
public abstract class GatKrzyz implements Serializable{
    private String nazwaGatunku;
    private LocalDate dataOdkrycia;
    private int liczbaKonczyn;
    private String slawnyPrzedstawiciel;
    private boolean krzyz;
    private transient Image zdjecie;
    private String imgString;
    private Rodzina rodzinaGatunku;
    private Typ typGatunku;
    private List<String> listaGatunkow = new ArrayList<>();
    
    
    public GatKrzyz() {
    }
    
    public GatKrzyz(String nazwaGatunku) {
        this.nazwaGatunku = nazwaGatunku;
        this.krzyz = false;
    }
    
    public GatKrzyz(String nazwaGatunku, Typ typGatunku, Rodzina rodzinaGatunku) {
        this.nazwaGatunku = nazwaGatunku;
        this.krzyz = false;
        this.typGatunku = typGatunku;
        this.rodzinaGatunku = rodzinaGatunku;
    }

    /**
     * @return the dataOdkrycia
     */
    public LocalDate getDataOdkrycia() {
        return dataOdkrycia;
    }

    /**
     * @param dataOdkrycia the dataOdkrycia to set
     */
    public void setDataOdkrycia(LocalDate dataOdkrycia) {
        this.dataOdkrycia = dataOdkrycia;
    }

    /**
     * @return the liczbaKonczyn
     */
    public int getLiczbaKonczyn() {
        return liczbaKonczyn;
    }

    /**
     * @param liczbaKonczyn the liczbaKonczyn to set
     */
    public void setLiczbaKonczyn(int liczbaKonczyn) {
        this.liczbaKonczyn = liczbaKonczyn;
    }

    /**
     * @return the slawnyPrzedstawiciel
     */
    public String getSlawnyPrzedstawiciel() {
        return slawnyPrzedstawiciel;
    }

    /**
     * @param slawnyPrzedstawiciel the slawnyPrzedstawiciel to set
     */
    public void setSlawnyPrzedstawiciel(String slawnyPrzedstawiciel) {
        this.slawnyPrzedstawiciel = slawnyPrzedstawiciel;
    }

    /**
     * @return the krzyz
     */
    public boolean isKrzyz() {
        return krzyz;
    }

    /**
     * @param krzyz the krzyz to set
     */
    public void setKrzyz(boolean krzyz) {
        this.krzyz = krzyz;
    }

    /**
     * Metoda odpowiedzialna za pobieranie zdjęcia z obiektu oraz przypisywanie zdjęcia w formacie Base64 do 
     * zdjęcia w formacie Image.
     * @return the zdjecie
     */
    public Image getZdjecie() {
        if (imgString != null) {
            try {
                this.zdjecie = ImgToBase64.decoder(imgString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return zdjecie;
    }

    /**
     * @param zdjecie the zdjecie to set
     */
    public void setZdjecie(Image zdjecie) {
        this.zdjecie = zdjecie;
    }

    /**
     * @return the rodzinaGatunku
     */
    public Rodzina getRodzinaGatunku() {
        return rodzinaGatunku;
    }

    /**
     * @param rodzinaGatunku the rodzinaGatunku to set
     */
    public void setRodzinaGatunku(Rodzina rodzinaGatunku) {
        this.rodzinaGatunku = rodzinaGatunku;
    }

    /**
     * @return the typGatunku
     */
    public Typ getTypGatunku() {
        return typGatunku;
    }

    /**
     * @param typGatunku the typGatunku to set
     */
    public void setTypGatunku(Typ typGatunku) {
        this.typGatunku = typGatunku;
    }

    /**
     * @param nazwaGatunku the nazwaGatunku to set
     */
    public void setNazwaGatunku(SimpleStringProperty nazwaGatunku) {
        this.setNazwaGatunku(nazwaGatunku);
    }

    /**
     * @return the listaGatunkow
     */
    public List<String> getListaGatunkow() {
        return listaGatunkow;
    }

    /**
     * @param aListaGatunkow the listaGatunkow to set
     */
    public void setListaGatunkow(List<String> aListaGatunkow) {
        listaGatunkow = aListaGatunkow;
    }
    
    /**
     * Metoda wyświetlająca listę gatunków wchodzących w skład krzyżówki.
     * @param listaGatunkow
     * @return Zwraca listę gatunków oddzielonych przecinkami
     */
    public String listaGatunkowDoWyswietlenia(List<String> listaGatunkow) {
        String stringTemp = "";
        for (String s : listaGatunkow) {
            stringTemp += s.toString()+",";
        }
        return stringTemp.substring(0, stringTemp.length()-1);
    }

    /**
     * @return the nazwaGatunku
     */
    public String getNazwaGatunku() {
        return nazwaGatunku;
    }

    /**
     * @param nazwaGatunku the nazwaGatunku to set
     */
    public void setNazwaGatunku(String nazwaGatunku) {
        this.nazwaGatunku = nazwaGatunku;
    }

    /**
     * @return the imgString
     */
    public String getImgString() {
        return imgString;
    }

    /**
     * @param imgString the imgString to set
     */
    public void setImgString(String imgString) {
        this.imgString = imgString;
    }
}
