package org.example;

import java.io.BufferedWriter;

public class RemoveGuideCommand implements Command {
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de eliminare a unui ghid.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        // Se cauta ghidul in lista de grupuri si este eliminat.
        for (Group group : database.getGroups()) {
            if (group.getTimetable().equals(parts[10])) {
                String output = group.removeGuide();
                group.setGuide(null);
                return output;
            }
        }

        // Nu exista grupul asociat, se arunca eroare.
        return parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: Group does not exist.";
    }
}
