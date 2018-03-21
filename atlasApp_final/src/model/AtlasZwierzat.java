/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dell
 */
public class AtlasZwierzat implements Serializable {

    private ArrayList<Typ> listaTypow = new ArrayList<Typ>();

    /**
     * Metoda dodająca typ do atlasu.
     * @param typ Typ do dodania
     */
    public void dodajTyp(Typ typ) {
        this.getListaTypow().add(typ);
    }

    /**
     * Metoda usuwająca typ z atlasu.
     * @param typ Typ do usunięcia
     */
    public void usunTyp(Typ typ) {
        this.getListaTypow().remove(typ);
    }

    /**
     * @return the listaTypow
     */
    public ArrayList<Typ> getListaTypow() {
        return listaTypow;
    }

    /**
     * @param listaTypow the listaTypow to set
     */
    public void setListaTypow(ArrayList<Typ> listaTypow) {
        this.listaTypow = listaTypow;
    }
}
