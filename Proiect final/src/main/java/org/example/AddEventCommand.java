package org.example;

public class AddEventCommand implements Command {
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de adaugare de evenimente.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        String output = "";

        // Adaugarea outputurilor intr-un string
        for(Group group : database.getGroups()) {
            if(group.getMuseumCode().equals(Integer.parseInt(parts[1]))) {
                if(database.getMuseumName(Long.parseLong(parts[1])) != null) {
                    String outputLine = "To: " + group.getGuide().getEmail() + " ## Message: " +
                            database.getMuseumName(Long.parseLong(parts[1])) + " (" + parts[1] + ") " + parts[2];
                    output += outputLine + "|";
                }
            }
        }

        // Ordonarea output-urilor
        String finalOutput = "";
        String[] outputParts = output.split("\\|");
        for(int i=outputParts.length - 1; i > 0; i--) {
            finalOutput += outputParts[i] + "\n";
        }
        finalOutput += outputParts[0];

        return finalOutput;
    }
}
