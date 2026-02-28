package org.example;

/**
 * Subclasa Student mosteneste clasa Person.
 * Este folosit pattern-ul Builder in crearea obiectelor de tipul Student.
 */
public class Student extends Person {
    private String school;
    private int studyYear;

    public Student(StudentBuilder builder) {
        super(builder.surname, builder.name, builder.role, builder.age, builder.email);
        this.school = builder.school;
        this.studyYear = builder.studyYear;
    }

    /**
     * Metoda care intoarce datele despre un Student.
     * @return
     */
    public String toString() {
        return super.toString() + ", school=" + school + ", studyYear=" + studyYear;
    }

    public static class StudentBuilder {
        private String surname;
        private String name;
        private String role;
        private int age;
        private String email;
        private String school;
        private int studyYear;

        public StudentBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }
        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public StudentBuilder setRole(String role) {
            this.role = role;
            return this;
        }
        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }
        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }
        public StudentBuilder setSchool(String school) {
            this.school = school;
            return this;
        }
        public StudentBuilder setStudyYear(int studyYear) {
            this.studyYear = studyYear;
            return this;
        }
        public Student build() {
            return new Student(this);
        }
    }
}
