package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("Too few arguments!");
        }

        // Fisierele de input din care extrag datele
        String[] inputFiles = new String[4];
        System.arraycopy(args, 1, inputFiles, 0, args.length - 1);

        // Database-ul nostru
        Database database = Database.getInstance();

        // Executam comenzile din fisiere
        for(String inputFile : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile + ".in"))) {
                String line;
                while((line = reader.readLine()) != null) {
                    database.doCommand(line, inputFile + ".out");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        database.getMuseums().clear();
        database.getGroups().clear();
    }
}