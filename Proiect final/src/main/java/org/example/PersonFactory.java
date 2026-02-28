package org.example;

/**
 * Folosirea pattern-ului Factory in crearea instantelor de tip Profesor-Student, sublase care mostenesc Person.
 */
public class PersonFactory {
    public static Person createPerson(String surname, String name, String role, int age, String email, String school,
                                      int experience_studyYear) {
        switch (role) {
            case "student":
                return new Student.StudentBuilder()
                        .setSurname(surname)
                        .setName(name)
                        .setRole(role)
                        .setAge(age)
                        .setEmail(email)
                        .setStudyYear(experience_studyYear)
                        .setSchool(school)
                        .build();
            case "profesor":
                return new Professor.ProfessorBuilder()
                        .setSurname(surname)
                        .setName(name)
                        .setRole(role)
                        .setAge(age)
                        .setEmail(email)
                        .setExperience(experience_studyYear)
                        .setSchool(school)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid person type");
        }
    }
}
