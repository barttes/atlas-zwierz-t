/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Krzyzowka extends GatKrzyz{

    public Krzyzowka () {
        super();
    }
    
    public Krzyzowka(String nazwaKrzyzowki) {
        super(nazwaKrzyzowki);
        super.setKrzyz(true);
    }
    
    public Krzyzowka(String nazwaGatunku, Typ typGatunku, Rodzina rodzinaGatunku) {
        super(nazwaGatunku, typGatunku, rodzinaGatunku);
        super.setKrzyz(true);
    }   
}
