/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AtlasZwierzat;
import model.GatKrzyz;
import model.Gatunek;
import model.Rodzina;
import model.Typ;

/**
 *
 * @author Dell
 */
public class GetWszystkie {
    /**
     * Metoda odpowiedzialna za zwrócenie wszystkich rodzin występujących w atlasie.
     * Używana głównie do wypełniania tabeli rodzin.
     * @param atlas Aktualny atlas
     * @return Lista rodzin aktualnego atlasu
     */
    public static ArrayList<Rodzina> listaRodzin(AtlasZwierzat atlas) {
        ArrayList<Rodzina> listaRodzin = new ArrayList<>();
        for (Typ t : atlas.getListaTypow()) {
            listaRodzin.addAll(t.getListaRodzin());    
        }
        return listaRodzin;
    }
    
    /**
     * Metoda odpowiedzialna za zwrócenie wszystkich GatKrzyz występujących w atlasie.
     * Używana głównie do wypełniania tabeli gatunków/krzyżówek. W sytuacji gdy jako argument jest podana
     * gatunku jest ona wtedy porównywana wyrażeniem regularnym z nazwą gatunku/krzyżówki z atlasu.
     * @param atlas Aktualny atlas.
     * @param gatunek Nazwa gatunku przekazana z pola wyszukiwania.
     * @return Lista gatunków/krzyżówek aktualnego atlasu.
     */
    public static ArrayList<GatKrzyz> listaGatKrzyz(AtlasZwierzat atlas, String gatunek) {
        ArrayList<GatKrzyz> listaGatKrzyz = new ArrayList<>();
        if (gatunek.equals("")) {
            for (Typ t : atlas.getListaTypow()) {
                for (Rodzina r : t.getListaRodzin()) {
                    listaGatKrzyz.addAll(r.getListaGatKrzyz());
                }    
            }
        } else {
            String regEx = "^.*"+gatunek+".*";
            Pattern p = Pattern.compile(regEx);
            Matcher m;
            for (Typ t : atlas.getListaTypow()) {
                for (Rodzina r : t.getListaRodzin()) {
                    for(GatKrzyz g: r.getListaGatKrzyz()) {
                        m = p.matcher(g.getNazwaGatunku());
                        if (m.matches()) {
                            listaGatKrzyz.add(g);
                        }
                        
                    }
                }    
            }
            
        }
        
        return listaGatKrzyz;
    }
}
