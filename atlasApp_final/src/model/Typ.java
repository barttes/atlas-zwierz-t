/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Typ implements Serializable{

    private String nazwaTypu;
    private int liczbaZwierzat;
    private String opisBudowy;
    private ArrayList<Rodzina> listaRodzin = new ArrayList<>();
    
    public Typ() {
    }
    
    public Typ(String nazwaTypu) {
        this.nazwaTypu = nazwaTypu;
    }

    /**
     * @return the liczbaZwierzat
     */
    public int getLiczbaZwierzat() {
        return liczbaZwierzat;
    }

    /**
     * @param liczbaZwierzat the liczbaZwierzat to set
     */
    public void setLiczbaZwierzat(int liczbaZwierzat) {
        this.liczbaZwierzat = liczbaZwierzat;
    }

    /**
     * @return the opisBudowy
     */
    public String getOpisBudowy() {
        return opisBudowy;
    }

    /**
     * @param opisBudowy the opisBudowy to set
     */
    public void setOpisBudowy(String opisBudowy) {
        this.opisBudowy = opisBudowy;
    }

    /**
     * Dodawanie nowej rodziny do atlasu.
     * @param rodzina Rodzina do dodania
     */
    public void dodajRodzine(Rodzina rodzina) {
        this.getListaRodzin().add(rodzina);
    }

    /**
     * Usuwanie rodziny z atlasu.
     * @param rodzina Rodzina do usunięcia
     */
    public void usunRodzine(Rodzina rodzina) {
        this.getListaRodzin().remove(rodzina);
    }

    /**
     * @return the nazwaTypu
     */
    public String getNazwaTypu() {
        return nazwaTypu;
    }

    /**
     * @param nazwaTypu the nazwaTypu to set
     */
    public void setNazwaTypu(String nazwaTypu) {
        this.nazwaTypu = nazwaTypu;
    }

    /**
     * @return the listaRodzin
     */
    public ArrayList<Rodzina> getListaRodzin() {
        return listaRodzin;
    }

    /**
     * @param listaRodzin the listaRodzin to set
     */
    public void setListaRodzin(ArrayList<Rodzina> listaRodzin) {
        this.listaRodzin = listaRodzin;
    }
   
}
