package org.example;

import java.util.ArrayList;

public class AddGuideCommand implements Command {
    /**
     * Implementarea functiei execute pentru clasa care implementeaza comanda de adaugare de ghid.
     * @param database
     * @param parts
     * @return
     */
    @Override
    public String execute(Database database, String[] parts) {
        // Crearea unui nou ghid
        Person newGuide = PersonFactory.createPerson(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                parts[5], parts[6], Integer.parseInt(parts[7]));

        // Daca ghidul este student, se arunca eroare
        if (newGuide.getRole().equals("student")) {
            newGuide.setRole("vizitator");
            return parts[9] + " ## " + parts[10] + " ## GuideTypeException: Guide must be a professor. ## (new guide: "
                    + newGuide.toString() + ")";
        }

        // Se seteaza roul de ghid si se verifica existenta unui grup in care sa fie adaugat
        newGuide.setRole("ghid");
        for (Group group : database.getGroups()) {
            if (group.getMuseumCode().equals(Integer.parseInt(parts[9])) && group.getTimetable().equals(parts[10])) {
                return group.addGuide((Professor) newGuide);
            }
        }

        // Se adauga un grup nou, asociat noului ghid
        Group newGroup = new Group.GroupBuilder()
                .setMembers(new ArrayList<>())
                .setMuseumCode(Integer.parseInt(parts[9]))
                .setTimetable(parts[10])
                .build();

        database.getGroups().add(newGroup);
        return newGroup.addGuide((Professor) newGuide);
    }
}
