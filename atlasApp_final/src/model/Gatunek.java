/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class Gatunek extends GatKrzyz{
    
    public Gatunek() {
        super();
    }
    
    public Gatunek(String nazwaGatunku) {
        super(nazwaGatunku);
    }
    
    public Gatunek(String nazwaGatunku, Typ typGatunku, Rodzina rodzinaGatunku) {
        super(nazwaGatunku, typGatunku, rodzinaGatunku);
    }
}
