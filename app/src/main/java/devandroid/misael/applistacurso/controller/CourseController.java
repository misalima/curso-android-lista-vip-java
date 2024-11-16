package devandroid.misael.applistacurso.controller;

import java.util.ArrayList;
import java.util.List;

import devandroid.misael.applistacurso.model.Course;
import devandroid.misael.applistacurso.model.Person;

public class CourseController {
    private List<Course> coursesList = new ArrayList<Course>();

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public boolean saveCourse(Course course) {
        if (validateCourse(course.getName())) {
            coursesList.add(course);
            return true;
        }
        return false;
    }

    public static boolean validateCourse(String courseName) {
        return !courseName.isEmpty();
    }

    public ArrayList<String> getCourseNames() {
        ArrayList<String> courseNames = new ArrayList<String>();
        for (Course course : coursesList) {
            courseNames.add(course.getName());
        }
        return courseNames;
    }

}
