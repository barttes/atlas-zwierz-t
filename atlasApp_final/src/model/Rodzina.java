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
public class Rodzina implements Serializable{
    private ArrayList<GatKrzyz> listaGatKrzyz = new ArrayList<>();
    private String nazwaRodziny;
    private String cechaRodziny;
    private int sredniaLiczbaKonczyn;
    private Typ typRodziny;
    private int liczbaKonczynOgolem;
    
    public Rodzina() {
    
    }
    
    public Rodzina(String nazwaRodziny) {
        this.nazwaRodziny = nazwaRodziny;
    }
    
    public Rodzina(String nazwaRodziny, Typ typRodziny) {
        this.nazwaRodziny = nazwaRodziny;
        this.typRodziny = typRodziny;
    }

    /**
     * @return the cechaRodziny
     */
    public String getCechaRodziny() {
        return cechaRodziny;
    }

    /**
     * @param cechaRodziny the cechaRodziny to set
     */
    public void setCechaRodziny(String cechaRodziny) {
        this.cechaRodziny = cechaRodziny;
    }

    /**
     * @return the sredniaLiczbaKonczyn
     */
    public int getSredniaLiczbaKonczyn() {
        return sredniaLiczbaKonczyn;
    }

    /**
     * Metoda jest uruchamiana poprzez metodę setLiczbaKonczynOgolem. Jest odpowiedzialna za prawidłowe ustalenie
     * średniej liczby kończyn w rodzinie.
     * @param dodModUsuw Dodawanie - 1; Usuwanie - -1; Modyfikacja - 0;
     * @param liczbaKonczynOgolem Suma kończyn ogółem wśród GatKrzyż w tej rodzinie
     */
    public void setSredniaLiczbaKonczyn(int liczbaKonczynOgolem, int dodModUsuw) {  
        if (dodModUsuw==1) {
            this.sredniaLiczbaKonczyn = (int)(this.getLiczbaKonczynOgolem())/(this.getListaGatKrzyz().size()+1);
        } else if (dodModUsuw==-1) {
            try {
                this.sredniaLiczbaKonczyn = (int)(this.getLiczbaKonczynOgolem())/(this.getListaGatKrzyz().size()-1);
            } catch (ArithmeticException e) {
                this.sredniaLiczbaKonczyn = 0;
            }
        } else {
            this.sredniaLiczbaKonczyn = (int)(this.getLiczbaKonczynOgolem())/(this.getListaGatKrzyz().size());
        } 
    }
    
    /**
     * Metoda dodaje albo GatKrzyz do atlasu.
     * @param gatKrzyz GatKrzyz do dodania
     */
    public void dodajGatKrzyz(GatKrzyz gatKrzyz) {
        this.getListaGatKrzyz().add(gatKrzyz);
    }

    /**
     * Metoda usuwa GatKrzyz z atlasu.
     * @param gatKrzyz GatKrzyz do usunięcia
     */
    public void usunGatKrzyz(GatKrzyz gatKrzyz) {
        this.getListaGatKrzyz().remove(gatKrzyz);
    }

    /**
     * @return the typRodziny
     */
    public Typ getTypRodziny() {
        return typRodziny;
    }

    /**
     * @param typRodziny the typRodziny to set
     */
    public void setTypRodziny(Typ typRodziny) {
        this.typRodziny = typRodziny;
    }

    /**
     * @return the liczbaKonczynOgolem
     */
    public int getLiczbaKonczynOgolem() {
        return liczbaKonczynOgolem;
    }

    /**
     * @param liczbaKonczynOgolem Liczba kończyn modyfikowanego/dodawanego GatKrzyz
     * @param dodModUsuw Czy dodajemy/modyfikujemy/usuwamy gatunek?
     */
    public void setLiczbaKonczynOgolem(int liczbaKonczynOgolem, int dodModUsuw) {
        this.liczbaKonczynOgolem += liczbaKonczynOgolem;
        this.setSredniaLiczbaKonczyn(liczbaKonczynOgolem, dodModUsuw);
    }

    /**
     * @return the listaGatKrzyz
     */
    public ArrayList<GatKrzyz> getListaGatKrzyz() {
        return listaGatKrzyz;
    }

    /**
     * @param listaGatKrzyz the listaGatKrzyz to set
     */
    public void setListaGatKrzyz(ArrayList<GatKrzyz> listaGatKrzyz) {
        this.listaGatKrzyz = listaGatKrzyz;
    }

    /**
     * @return the nazwaRodziny
     */
    public String getNazwaRodziny() {
        return nazwaRodziny;
    }

    /**
     * @param nazwaRodziny the nazwaRodziny to set
     */
    public void setNazwaRodziny(String nazwaRodziny) {
        this.nazwaRodziny = nazwaRodziny;
    }
    
}
