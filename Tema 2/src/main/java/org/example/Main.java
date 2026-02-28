package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("Too few arguments!");
        }

        // Fisierul de input
        String folder = "src/main/resources/" + args[0];
        String inputFile = folder + "/input.in";

        // O instanta de tipul Singleton ce reprezinta panoul de comanda pentru comenzi
        Commands commandsPanel = Commands.getInstance();

        // Citim comanda
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                commandsPanel.doCommand(line, args[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}