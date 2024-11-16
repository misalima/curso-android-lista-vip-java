package devandroid.misael.applistacurso.controller;

import java.util.ArrayList;

import devandroid.misael.applistacurso.model.Person;

public class PersonController {
    private final ArrayList<Person> people = new ArrayList<Person>();
    public boolean savePerson(Person person) {
        if (validatePerson(person.getFirstName(), person.getLastName(), person.getDesiredCourse())) {
            people.add(person);
            return true;
        }
        return false;
    }

    public static boolean validatePerson(String firstName, String lastName, String courseName) {
        return !firstName.isEmpty() && !lastName.isEmpty() && !courseName.isEmpty();
    }

    public ArrayList<Person> getPeople() {
        return people;
    }
}
