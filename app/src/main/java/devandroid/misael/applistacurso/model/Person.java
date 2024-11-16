package devandroid.misael.applistacurso.model;

import androidx.annotation.NonNull;

public class Person {
    private String firstName;
    private String lastName;
    private String desiredCourse;
    private String phone;

    public Person(){}

    public Person(String firstName, String lastName, String desiredCourse, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.desiredCourse = desiredCourse;
        this.phone = phone;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "First Name ='" + firstName + '\'' +
                ", Last Name ='" + lastName + '\'' +
                ", Desired Course ='" + desiredCourse + '\'' +
                ", Phone Number ='" + phone + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesiredCourse() {
        return desiredCourse;
    }

    public void setDesiredCourse(String desiredCourse) {
        this.desiredCourse = desiredCourse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
