/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Dell
 */
public class ObslugaDaty {
    private static final String WZORZEC = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATER_DATY = DateTimeFormatter.ofPattern(WZORZEC);
    
    /**
     * Metoda formatująca datę na typ tekstowy.
     * @param data
     * @return Typ tekstowy po sformatowaniu według wzorca: dd.MM.yyyy
     */
    public static String formatuj(LocalDate data) {
        if (data==null) {
            return null;
        } else {
            return FORMATER_DATY.format(data);
        }
    }
    
    /**
     * Metoda parsująca typ tekstowy na typ daty.
     * @param data Data do sparsowania
     * @return Data w typie LocalDate
     */
    public static LocalDate parsuj(String data) {
        try {
            return FORMATER_DATY.parse(data, LocalDate::from);
        } catch (DateTimeParseException e) {
            ObslugaAlertow.niepoprawneDane("Niepoprawny format daty ..");
            return null;
        }
    }
    
    /**
     * Metoda parsująca typ tekstowy na typ daty. Wykorzystywana w przypadku modyfikacji gatunków.
     * Ustawia datę, która była wcześniej w przypadku gdy parsowanie się nie powiedzie.
     * @param data Data do sparsowania
     * @param dataOld Stara data (jeśli parsowanie się nie powiedzie)
     * @return Data w typie LocalDate po sparsowaniu
     */
    public static LocalDate parsuj(String data, LocalDate dataOld) {
        try {
            return FORMATER_DATY.parse(data, LocalDate::from);
        } catch (DateTimeParseException e) {
            ObslugaAlertow.niepoprawneDane("Niepoprawny format daty ..");
            return dataOld;
        }
    }
    
}
