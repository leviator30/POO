package org.example;

/**
 * Clasa Person mosteneste subclasele Professor si Student.
 */
public class Person {
    private String surname;
    private String name;
    private String role;
    private int age;
    private String email;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(String surname, String name, String role, int age, String email) {
        this.surname = surname;
        this.name = name;
        this.role = role;
        this.age = age;
        this.email = email;
    }

    /**
     * Metoda care intoarce datele despre un Person.
     * @return
     */
    public String toString() {
        StringBuilder outputLine = new StringBuilder();
        outputLine.append("surname=").append(surname);
        outputLine.append(", name=").append(name);
        outputLine.append(", role=").append(role);
        outputLine.append(", age=").append(age);
        outputLine.append(", email=").append(email == null || email.isEmpty() ? "null" : email);

        return outputLine.toString();
    }
}
