package org.example;

public class AddMemberCommand implements Command{
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de adaugare de membru.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        // Crearea unui nou membru
        Person newMember = PersonFactory.createPerson(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                parts[5], parts[6], Integer.parseInt(parts[7]));
        newMember.setRole("vizitator");

        String output = "";

        // Se cauta grupul in care trebuie adaugat membrul
        for (Group group : database.getGroups()) {
            if (group.getTimetable().equals(parts[10])) {
                output = group.addMember(newMember);
                break;
            }
        }

        // Daca grupul nu exista, se arunca eroare
        if (output.isEmpty()) {
            output = parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: " +
                    "Group does not exist. ## (new member: " + newMember + ")";
        }

        return output;
    }
}
