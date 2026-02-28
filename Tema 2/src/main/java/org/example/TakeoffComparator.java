package org.example;

import java.util.Comparator;

public class TakeoffComparator<T extends Airplane> implements Comparator<T> {
    /**
     * Comparato pentru pistele de tipul decolare, sortam doar in functie de timpul dorit
     * @param airplane1 the first object to be compared.
     * @param airplane2 the second object to be compared.
     * @return
     */
    public int compare(T airplane1, T airplane2) {
        return airplane1.getDesiredTime().compareTo(airplane2.getDesiredTime());
    }
}
