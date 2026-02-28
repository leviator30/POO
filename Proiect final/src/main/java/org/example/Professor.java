package org.example;

/**
 * Subclasa Profesor mosteneste clasa Person.
 * Este folosit pattern-ul Builder in crearea obiectelor de tipul Profesor.
 */
public class Professor extends Person {
    private int experience;
    private String school;

    public Professor(ProfessorBuilder builder) {
        super(builder.surname, builder.name, builder.role, builder.age, builder.email);
        this.experience = builder.experience;
        this.school = builder.school;
    }

    /**
     * Metoda care intoarce datele despre un Profesor.
     * @return
     */
    public String toString() {
        return super.toString() + ", school=" + school + ", experience=" + experience;
    }
    public static class ProfessorBuilder {
        private String surname;
        private String name;
        private String role;
        private int age;
        private String email;
        private int experience;
        private String school;

        public ProfessorBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }
        public ProfessorBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public ProfessorBuilder setRole(String role) {
            this.role = role;
            return this;
        }
        public ProfessorBuilder setAge(int age) {
            this.age = age;
            return this;
        }
        public ProfessorBuilder setEmail(String email) {
            this.email = email;
            return this;
        }
        public ProfessorBuilder setExperience(int experience) {
            this.experience = experience;
            return this;
        }
        public ProfessorBuilder setSchool(String school) {
            this.school = school;
            return this;
        }
        public Professor build() {
            return new Professor(this);
        }
    }
}
