package org.example;

public class FindGuideCommand implements Command {
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de gasire a unui ghid.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        // Se creeaza ghidul cautat.
        Person wantedGuide = PersonFactory.createPerson(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                parts[5], parts[6], Integer.parseInt(parts[7]));

        // Daca ghidul cautat nu este profesor, se arunca eroare.
        if(!wantedGuide.getRole().equals("profesor"))
            return parts[9] + " ## " + parts[10] + " ## GuideTypeException: Guide must be a professor. ## (new guide: "
                    + wantedGuide.toString() + ")";

        // Se cauta ghidul in baza de date.
        wantedGuide.setRole("ghid");
        for(Group group : database.getGroups()) {
            if(group.getMuseumCode().equals(Integer.parseInt(parts[9])) && group.getTimetable().equals(parts[10])) {

                // S-a gasit ghidul cautat.
                if(group.getGuide().toString().equals(wantedGuide.toString()))
                    return parts[9] + " ## " + parts[10] + " ## guide found: " + wantedGuide;

                // Ghidul cautat nu exista deloc, se arunca eroare.
                wantedGuide.setRole("vizitator");
                for(Person member : group.getMembers()) {
                    if(member.toString().equals(wantedGuide.toString())) {
                        return parts[9] + " ## " + parts[10] + " ## guide not exists: " + member;
                    }
                }
                wantedGuide.setRole("ghid");

                return parts[9] + " ## " + parts[10] + " ## guide not exists: " + wantedGuide;

            }
        }

        // Nu exista grup pentru ghidul cautat.
        return parts[9] + " ## " + parts[10] +
                " ## GroupNotExistsException: Group does not exist. ## (find guide: " + wantedGuide + ")";
    }
}
