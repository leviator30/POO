package org.example;

public class AddMuseumCommand implements Command{
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de adaugare de muzeu.
     * @param database
     * @param parts
     * @return
     */
    public String execute(Database database, String[] parts) {
        try {
            // Crearea unui nou muzeu
            Location newLocation = new Location(parts[4], Integer.parseInt(parts[16]));
            Museum museum = new Museum.MuseumBuilder()
                    .setCode(Long.parseLong(parts[1]))
                    .setSupervisorCode(0)
                    .setName(parts[2])
                    .setLocation(newLocation)
                    .build();

            database.getMuseums().add(museum);

            StringBuilder output = new StringBuilder();
            output.append(museum.getCode()).append(": ").append(museum.getName());

            return output.toString();

        // In cazul exceptiilor date, se arunca eroare
        } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
            return "Exception: Data is broken. ## (" + String.join("|", parts) + ")";

        }
    }
}
