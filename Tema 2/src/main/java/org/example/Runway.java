package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Runway<T extends Airplane> {
    private String ID;
    private String type;
    private String status;
    private PriorityQueue<T> scheduledAirplanes;
    private LocalTime availableAt;
    private ArrayList<T> managedAirplanes;
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public PriorityQueue<T> getScheduledAirplanes() {
        return scheduledAirplanes;
    }

    public void setScheduledAirplanes(PriorityQueue<T> scheduledAirplanes) {
        this.scheduledAirplanes = scheduledAirplanes;
    }

    public LocalTime getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(LocalTime availableAt) {
        this.availableAt = availableAt;
    }

    /**
     * Constructor cu parametrii pentru clasa generica Runway
     * @param ID
     * @param type
     * @param comparator
     */
    public Runway(String ID, String type, Comparator<T> comparator) {
        this.ID = ID;
        this.type = type;
        this.status = "FREE";
        this.scheduledAirplanes = new PriorityQueue<>(comparator);
        this.availableAt = LocalTime.MIN;
        this.managedAirplanes = new ArrayList<>();
    }
    public void addAirplane(T airplane) {
        scheduledAirplanes.add(airplane);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<T> getManagedAirplanes() {
        return managedAirplanes;
    }

    public void setManagedAirplanes(ArrayList<T> managedAirplanes) {
        this.managedAirplanes = managedAirplanes;
    }

    public void manageAirplane(T airplane) {
        this.managedAirplanes.add((airplane));
    }

    /**
     * Metoda toString de afisare a detaliilor unui runway
     * Sortam lista de avioane in functie de comparatorul asociat
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ID).append(" - ").append(status).append("\n");

        List<T> sortedAirplanes = new ArrayList<>(scheduledAirplanes);
        sortedAirplanes.sort(scheduledAirplanes.comparator());

        for (T airplane : sortedAirplanes) {
            sb.append(airplane.toString()).append("\n");
        }
        return sb.toString();
    }
}
