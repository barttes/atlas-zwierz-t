/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import model.AtlasZwierzat;
import model.GatKrzyz;
import model.Rodzina;
import model.Typ;
import view.EdycjaWygladController;

/**
 *
 * @author Dell
 */
public class SprawdzIndeks {
    
    /**
     * Metoda sprawdzająca indeks typu w tablicy typów atlasu. Sprawdza typ po jego nazwie.
     * @param listaTypow Lista typów atlasu
     * @param nazwaTypu Nazwa sprawdzanego typu
     * @return Indeks typu lub -1, gdy typ nie został znaleziony
     */
    public static int sprawdzIndeksTypu(ArrayList<Typ> listaTypow, String nazwaTypu) {
        Typ typTemp = new Typ(nazwaTypu);
        int id = 0;
        for (Typ t: listaTypow) {
           if (t.getNazwaTypu().equals(typTemp.getNazwaTypu())) {
               return id;
           }
           id ++;
        }
       return -1;
    }
    
    /**
     * Metoda sprawdzająca indeks rodziny w ramach typu. Sprawdza rodzinę po jej nazwie.
     * @param listaTypow Wszystkie typy atlasu
     * @param rodzinaTemp Obiekt będący rodziną
     * @return Indeks rodziny lub -1 gdy szukana rodzina nie istnieje
     */
    public static int sprawdzIndeksRodziny(ArrayList<Typ> listaTypow, Rodzina rodzinaTemp) {
        int id = 0;
        for (Typ t: listaTypow) {
            id = 0; 
            for ( Rodzina r: t.getListaRodzin()) {
               if (r.getNazwaRodziny().equals(rodzinaTemp.getNazwaRodziny())) {
                    return id;
               }
               id ++;
           }
       }
       return -1;
    }
    
    /**
     * Metoda sprawdzająca indeks rodziny w ramach typu. Sprawdza rodzinę po jej nazwie.
     * @param listaTypow Wszystkie typy atlasu
     * @param nazwaRodziny Nazwa rodziny
     * @return Indeks rodziny lub -1 gdy szukana rodzina nie istnieje
     */
    public static int sprawdzIndeksRodziny(ArrayList<Typ> listaTypow, String nazwaRodziny) {
        int id = 0;
        for (Typ t: listaTypow) {
            id = 0;
            for ( Rodzina r: t.getListaRodzin()) {
               if (r.getNazwaRodziny().equals(nazwaRodziny)) {
                    return id;
               }
               id ++;
           }
        }
        return -1;
    }
    
    /**
     * Metoda sprawdza czy rodzina istnieje w atlasie. Wyszukiwanie odbywa się po nazwie.
     * @param listaTypow Wszystkie typy atlasu
     * @param nazwaRodziny Nazwa rodziny
     * @return Istnieje/nie istnieje
     */
    public static boolean czyRodzinaIstnieje(ArrayList<Typ> listaTypow, String nazwaRodziny) {
        Rodzina rodzinaTemp = new Rodzina(nazwaRodziny);
        for (Typ t: listaTypow) {
           for ( Rodzina r: t.getListaRodzin()) {
               if (r.getNazwaRodziny().equals(rodzinaTemp.getNazwaRodziny())) {
                    return true;
               }
           }
        }
        return false;
    }
    
    /**
     * Metoda odpowiedzialna za zwrócenie indeksu wyszukiwanego gatunku.
     * @param listaTypow Wszystkie typy atlasu
     * @param gatunekTemp  Wyszukiwany obiekt gatunku lub krzyzówki 
     * @return Indeks wyszukiwanego gatunku lub -1, o ile nie istnieje
     */
    public static int sprawdzIndeksGatunku(ArrayList<Typ> listaTypow, GatKrzyz gatunekTemp) {
        int id = 0;
        for (Typ t: listaTypow) {
            for (Rodzina r: t.getListaRodzin()) {
                id=0;
                for (GatKrzyz g: r.getListaGatKrzyz()) {
                    if (g.getNazwaGatunku().equals(gatunekTemp.getNazwaGatunku())) {
                        return id;
                    }
                    id++;
                }
            }
        }
        return -1;
    }
    
    /**
     * Metoda sprawdzająca czy gatunek istnieje w atlasie.
     * @param listaTypow Wszystkie typy atlasu
     * @param nazwaGatunku  Wyszukiwany gatunek identyfikowany nazwą
     * @return Istnieje/nie istnieje
     */
    public static boolean czyGatunekIstnieje(ArrayList<Typ> listaTypow, String nazwaGatunku) {
        for (Typ t: listaTypow) {
            for (Rodzina r: t.getListaRodzin()) {
                for (GatKrzyz g: r.getListaGatKrzyz()) {
                    if (g.getNazwaGatunku().equals(nazwaGatunku)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
