package org.example;

import java.util.Comparator;

public class LandingComparator<T extends Airplane> implements Comparator<T> {
    /**
     * Comparator pentru pistele de aterizare, sortam in functie de urgente, apoi in functie de timpul dorit
     * @param airplane1 the first object to be compared.
     * @param airplane2 the second object to be compared.
     * @return
     */
    public int compare(T airplane1, T airplane2) {
        if(airplane1.isUrgent() && !airplane2.isUrgent())
            return -1;
        else if(!airplane1.isUrgent() && airplane2.isUrgent())
            return 1;

        return airplane1.getDesiredTime().compareTo(airplane2.getDesiredTime());
    }
}
