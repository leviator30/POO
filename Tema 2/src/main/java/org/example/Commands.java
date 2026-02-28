package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Commands {
    private static Commands instance;
    private ArrayList<Runway> runways = new ArrayList<>();

    // Lista de outputuri
    ArrayList<String> outputs = new ArrayList<>();

    // Lista de exceptii
    ArrayList<String> exceptions = new ArrayList<>();

    private Commands() {
    }

    /**
     * Instanta de Singleton
     * @return
     */
    public static Commands getInstance() {
        if(instance == null) {
            instance = new Commands();
        }
        return instance;
    }

    /**
     * Metoda ce executa comenzile primite
     * @param line
     * @param testName
     */
    public void doCommand(String line, String testName) {
        // Bucatile din comanda
        String[] parts = line.split(" - ");

        // Comanda de executat
        String command = parts[1];

        // Folderul in care vom pune rezultatele
        String folder = "src/main/resources/" + testName;

        // Timestamp-ul curent
        LocalTime currentTime = LocalTime.parse(parts[0]);

        switch (command) {
            // Comanda "add_runway_in_use"
            case "add_runway_in_use":
                // Tipul de runway pe care il avem (landing/takeoff)
                boolean isLanding = parts[3].equalsIgnoreCase("landing");

                // Adaugam runway de tipul "wide body"
                if(parts[4].equals("wide body")) {
                    Runway<WideBodyAirplane> runwayWide = new Runway<>(
                            parts[2],
                            parts[3],
                            isLanding ? new LandingComparator<>() : new TakeoffComparator<>()
                    );
                    runways.add(runwayWide);

                // Adaugam runway de tipul "narrow body"
                } else if(parts[4].equals("narrow body")) {
                    Runway<NarrowBodyAirplane> runwayNarrow = new Runway<>(
                            parts[2],
                            parts[3],
                            isLanding ? new LandingComparator<>() : new TakeoffComparator<>()
                    );
                    runways.add(runwayNarrow);
                }

                break;

            // Comanda "allocate_plane"
            case "allocate_plane":
                boolean isUrgent = parts.length > 9 && parts[9].equalsIgnoreCase("urgent");

                if(parts[2].equals("wide body")) {
                    // Avion de tipul "wide body"
                    WideBodyAirplane wideAirplane = new WideBodyAirplane(parts[3], parts[4], parts[5], parts[6], LocalTime.parse(parts[7]), isUrgent);

                    // Cautam id-ul runway-ului asociat si vedem daca acesta poate fi adaugat(landing/takeoff)
                    // In caz contrar, trimitem o exceptie
                    for(Runway runway : runways) {
                        if(runway.getID().equals(parts[8])) {
                            if(wideAirplane.associationRunwayAirplane().equals(runway.getType())) {
                                runway.addAirplane(wideAirplane);
                                break;
                            } else {
                                StringBuilder output = new StringBuilder();
                                output.append(parts[0]).append(" | ").append("The chosen runway for allocating the plane is incorrect");

                                exceptions.add(output.toString());
                                break;
                            }
                        }
                    }
                } else {
                    // Avion de tipul "narrow body"
                    NarrowBodyAirplane narrowAirplane = new NarrowBodyAirplane(parts[3], parts[4], parts[5], parts[6],
                            LocalTime.parse(parts[7]), isUrgent);

                    // Cautam id-ul runway-ului asociat si vedem daca acesta poate fi adaugat(landing/takeoff)
                    // In caz contrar, trimitem o exceptie
                    for(Runway runway : runways) {
                        if(runway.getID().equals(parts[8])) {
                            if(narrowAirplane.associationRunwayAirplane().equals(runway.getType())) {
                                runway.addAirplane(narrowAirplane);
                                break;
                            } else {
                                StringBuilder output = new StringBuilder();
                                output.append(parts[0]).append(" | ").append("The chosen runway for allocating the plane is incorrect");

                                exceptions.add(output.toString());
                                break;
                            }
                        }
                    }
                }

                break;

            // Comanda "permission_for_maneuver"
            case "permission_for_maneuver":
                String ID = parts[2];

                for(Runway runway : runways) {
                    if(runway.getID().equals(ID)) {
                        // Cautam runway-ul dat, si din priority queue-ul acestuia scoatem primul element
                        Airplane airplane = (Airplane) runway.getScheduledAirplanes().poll();

                        if(airplane != null) {
                            // Vedem daca pista este libera pentru executarea comenzii, si daca timpul necesare
                            // de la ultima executare a trecut (10/5 min)
                            if(runway.getAvailableAt().isBefore(currentTime)) {
                                if(runway.getType().equals("landing")) {
                                    airplane.setStatus(Airplane.Status.LANDED);
                                    airplane.setActualTime(currentTime);
                                    runway.setAvailableAt(currentTime.plusMinutes(10));
                                    runway.manageAirplane(airplane);

                                } else if(runway.getType().equals("takeoff")) {
                                    airplane.setStatus(Airplane.Status.DEPARTED);
                                    airplane.setActualTime(currentTime);
                                    runway.setAvailableAt(currentTime.plusMinutes(5));
                                    runway.manageAirplane(airplane);
                                }
                                // Executam comanda, schimbam status-ul avionului si al pistei
                                runway.setStatus("OCCUPIED");

                            // Comanda nu se executa, trimitem o exceptie si punem avionul extras inapoi in coada
                            // de prioritati
                            } else {
                                runway.getScheduledAirplanes().add(airplane);
                                exceptions.add(parts[0] + " | The chosen runway for maneuver is currently occupied");
                            }
                        }
                        break;
                    }
                }

                break;

            case "runway_info":
                String time = parts[0];
                String[] timeParts = time.split(":");
                String outputFileRunwayInfo = folder + "/runway_info_" + parts[2] + "_" + timeParts[0] + "-" +
                        timeParts[1] + "-" + timeParts[2] + ".out";

                // Daca timpul de la comanda de manevra a trecut, schimbam status-ul pistei
                for(Runway runway : runways) {
                    if(runway.getAvailableAt().isBefore(currentTime))
                        runway.setStatus("FREE");

                    // Scriem in fisier detaliile runway-ului
                    if(runway.getID().equals(parts[2])) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileRunwayInfo))) {
                            writer.write(runway.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;

            case "flight_info":
                boolean found = false;
                for (Runway runway : runways) {
                    if (found) break;

                    // Cautam avionul, si extragem informatiile despre acesta
                    for (Object airplaneObj : runway.getScheduledAirplanes()) {
                        if (found) break;

                        Airplane airplane = (Airplane) airplaneObj;

                        if (airplane.getID().equals(parts[2])) {
                            StringBuilder output = new StringBuilder();
                            output.append(parts[0]).append(" | ").append(airplane.toString());

                            outputs.add(output.toString());
                            found = true;
                        }
                    }

                    for (Object airplaneObj : runway.getManagedAirplanes()) {
                        if (found) break;

                        Airplane airplane = (Airplane) airplaneObj;

                        // Scriem in fisier detaliile airplane-ului
                        if (airplane.getID().equals(parts[2])) {
                            StringBuilder output = new StringBuilder();
                            output.append(parts[0]).append(" | ").append(airplane.toString());

                            outputs.add(output.toString());
                            found = true;
                        }
                    }
                }

                break;

            // Comanda "exit"
            case "exit":
                // Scriem in fisier lista de output-uri
                if(!outputs.isEmpty()) {
                    String outputFile = folder + "/flight_info.out";

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                        for (String output : outputs) {
                            writer.write(output);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Scriem in fisier lista de exceptii
                if(!exceptions.isEmpty()) {
                    String exceptionsFile = folder + "/board_exceptions.out";

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(exceptionsFile))) {
                        for (String exception : exceptions) {
                            writer.write(exception);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Eliberam listele folosite
                outputs.clear();
                runways.clear();
                exceptions.clear();
                break;

            default:
                break;
        }
    }
}
