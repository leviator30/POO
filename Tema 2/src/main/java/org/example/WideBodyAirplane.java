package org.example;

import java.time.LocalTime;

public class WideBodyAirplane extends Airplane {
    /**
     * Constructor ce apeleaza constructorul din clasa parinte
     * @param model
     * @param flightID
     * @param departureLocation
     * @param destination
     * @param desiredTime
     * @param isUrgent
     */
    public WideBodyAirplane(String model, String flightID, String departureLocation,
                            String destination, LocalTime desiredTime, boolean isUrgent) {
        super(model, flightID, departureLocation, destination, desiredTime, isUrgent);
    }

    /**
     * Metoda toString de afisare a detaliilor unui avion d etipul "wide body"
     * @return
     */
    @Override
    public String toString() {
        return "Wide Body - " + super.toString();
    }
}
