package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Airplane {
    private String model;
    private String ID;
    private String departureLocation;
    private String destination;
    private LocalTime desiredTime;
    private LocalTime actualTime;
    private Status status;
    private boolean isUrgent;

    public enum Status {
        WAITING_FOR_TAKEOFF,
        DEPARTED,
        WAITING_FOR_LANDING,
        LANDED
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDesiredTime() {
        return desiredTime;
    }

    public void setDesiredTime(LocalTime desiredTime) {
        this.desiredTime = desiredTime;
    }

    public LocalTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalTime actualTime) {
        this.actualTime = actualTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    /**
     * Constructor cu parametrii pentru tipul Airplane
     * @param model
     * @param ID
     * @param departureLocation
     * @param destination
     * @param desiredTime
     * @param isUrgent
     */
    public Airplane(String model, String ID, String departureLocation, String destination,
                    LocalTime desiredTime, boolean isUrgent) {
        this.model = model;
        this.ID = ID;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.desiredTime = desiredTime;

        if (destination.equalsIgnoreCase("Bucharest")) {
            this.status = Status.WAITING_FOR_LANDING;
        } else if (departureLocation.equalsIgnoreCase("Bucharest")) {
            this.status = Status.WAITING_FOR_TAKEOFF;
        } else {
            throw new IllegalArgumentException("Unknown status: airplane must either depart from or arrive at Bucharest.");
        }

        this.actualTime = null;
        this.isUrgent = isUrgent;
    }

    /**
     * Metosa toString de afisare a detaliilor unui avion
     * @return
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append(model).append(" - ")
                .append(ID).append(" - ")
                .append(departureLocation).append(" - ")
                .append(destination).append(" - ")
                .append(status).append(" - ")
                .append(desiredTime.format(formatter));

        if (actualTime != null) {
            sb.append(" - ").append(actualTime.format(formatter));
        }

        return sb.toString();
    }

    public String associationRunwayAirplane() {
        if (this.status == Status.WAITING_FOR_LANDING) {
            return "landing";
        } else if (this.status == Status.WAITING_FOR_TAKEOFF) {
            return "takeoff";
        }
        return null;
    }
}
