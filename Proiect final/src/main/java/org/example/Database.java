package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    // Implementarea Singleton a clasei Database
    public static final Database instance = new Database();
    private Database() {};
    public static Database getInstance() {
        return instance;
    }

    // Listele de muzee si grupuri
    private ArrayList<Museum> museums = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    // Instanta de gestionare a comenzilor
    private CommandManager commandManager = new CommandManager();

    public ArrayList<Museum> getMuseums() {
        return museums;
    }

    public void setMuseums(ArrayList<Museum> museums) {
        this.museums = museums;
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    /**
     * Functia de executare a comenzilor din fisiere.
     * @param line
     * @param outputFile
     */
    public void doCommand(String line, String outputFile) {
        // Impartim comanda pe bucati, extrage datele necesare prelucrarii comenzii.
        String[] parts = line.split("\\|");
        String output = commandManager.executeCommand(this, parts);

        // Scrierea in fisierele de output.
        if(!output.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
                writer.write(output);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cautarea unui muzeu, dupa codul muzeului.
     * @param museumCode
     * @return
     */
    public String getMuseumName(long museumCode) {
        for(Museum museum : museums) {
            if(museum.getCode() == museumCode) {
                return museum.getName();
            }
        }
        return null;
    }
}
