package org.example;

public class RemoveMemberCommand implements Command {
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de eliminare a unui membru.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        // Crearea membrului care trebuie eliminat
        Person deleteMember =  PersonFactory.createPerson(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                parts[5], parts[6], Integer.parseInt(parts[7]));
        deleteMember.setRole("vizitator");
        String output = "";

        // Se cauta membrul in fiecare grup, si daca il gaseste, il elimina.
        for(Group group : database.getGroups()) {
            if(group.getTimetable().equals(parts[10])) {
                output = group.deleteMember(deleteMember);
            }
        }

        // Daca membrul nu este gasit in niciun grup, se arunca eroare.
        if(output.isEmpty()) {
            output = parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: " +
                    "Group does not exist. ## (removed member: " + deleteMember + ")";
        }

        return output;
    }
}
