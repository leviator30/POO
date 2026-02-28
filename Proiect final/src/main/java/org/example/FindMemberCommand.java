package org.example;

public class FindMemberCommand implements Command {
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de gasire a unui membru.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        // Se creeaza membrul cautat.
        Person wantedMember = PersonFactory.createPerson(parts[1], parts[2], parts[3],
                Integer.parseInt(parts[4]), parts[5], parts[6], Integer.parseInt(parts[7]));
        wantedMember.setRole("vizitator");

        // Se cauta membrul in baza de date.
        for (Group group : database.getGroups()) {
            if (group.getMuseumCode().equals(Integer.parseInt(parts[9])) && group.getTimetable().equals(parts[10])) {
                for (Person member : group.getMembers()) {
                    // Membrul a fost gasit.
                    if (member.toString().equals(wantedMember.toString())) {
                        return parts[9] + " ## " + parts[10] + " ## member found: " + member;
                    }
                }

                // Membrul nu exista in baza de date.
                wantedMember.setRole("ghid");
                if(group.getGuide().toString().equals(wantedMember.toString()))
                    return parts[9] + " ## " + parts[10] + " ## member not exists: " + group.getGuide().toString();
                wantedMember.setRole("vizitator");

                return parts[9] + " ## " + parts[10] + " ## member not exists: " + wantedMember;
            }
        }

        // Nu exista grup pentru membrul cautat.
        return parts[9] + " ## " + parts[10] +
                " ## GroupNotExistsException: Group does not exist. ## (find member: " + wantedMember + ")";

    }
}
