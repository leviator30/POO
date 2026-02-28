package org.example;

import java.util.List;

/**
 * Este folosit pattern-ul Builder in crearea obiectelor de tipul Group.
 */
public class Group {
    private List<Person> members;
    private Professor guide;
    private Integer museumCode;
    private String timetable;

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public Professor getGuide() {
        return guide;
    }

    public void setGuide(Professor guide) {
        this.guide = guide;
    }

    public Integer getMuseumCode() {
        return museumCode;
    }

    public void setMuseumCode(Integer museumCode) {
        this.museumCode = museumCode;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    private Group(GroupBuilder builder) {
        this.members = builder.members;
        this.guide = null;
        this.museumCode = builder.museumCode;
        this.timetable = builder.timetable;
    }

    /**
     * Metoda pentru adaugarea unui membru in lista de membri.
     * @param person
     * @return
     */
    public String addMember(Person person) {
        // Se adauga membri in limita de 10 persoane per grup.
        if(members.size() < 10) {
            members.add(person);
            return museumCode + " ## " + timetable + " ## new member: " + person;
        }
        return museumCode + " ## " + timetable + " ## GroupThresholdException: " +
                "Group cannot have more than 10 members. ## (new member: " + person.toString() + ")";
    }

    /**
     * Metoda pentru eliminarea unui membru din lista de membri.
     * @param person
     * @return
     */
    public String deleteMember(Person person) {
        // Se cauta membrul in lista si este eliminat.
        for(Person iter : members) {
            if(iter.toString().equals(person.toString())) {
                members.remove(iter);
                return museumCode + " ## " + timetable + " ## removed member: " + person;
            }
        }

        // Daca membrul nu este gasit, se intoarce eroare.
        return museumCode + " ## " + timetable + " ## PersonNotExistsException: " +
                "Person was not found in the group. ## (" + person.toString() + ")";
    }

    /**
     * Metoda pentru asocierea unui ghid la grupul respectiv.
     * @param guide
     * @return
     */
    public String addGuide(Professor guide) {
        // Daca grupul are deja un ghid, se intoarce eroare.
        if (this.guide != null) {
            return museumCode + " ## " + timetable + " ## GuideExistsException: Guide already exists. ## (new guide: "
                    + this.guide.toString() + ")";
        }

        // Se asociaza un ghid grupului.
        this.guide = guide;
        return museumCode + " ## " + timetable + " ## new guide: " + guide.toString();
    }

    /**
     * Metoda pentru eliminarea ghidului grupului.
     * @return
     */
    public String removeGuide() {
        return museumCode + " ## " + timetable + " ## removed guide: " + this.guide.toString();
    }

    public static class GroupBuilder {
        private List<Person> members;
        private Integer museumCode;
        private String timetable;
        public GroupBuilder setMembers(List<Person> members) {
            this.members = members;
            return this;
        }
        public GroupBuilder setMuseumCode(Integer museumCode) {
            this.museumCode = museumCode;
            return this;
        }
        public GroupBuilder setTimetable(String timetable) {
            this.timetable = timetable;
            return this;
        }
        public Group build() {
            return new Group(this);
        }
    }
}
