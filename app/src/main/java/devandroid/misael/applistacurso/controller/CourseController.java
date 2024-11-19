package devandroid.misael.applistacurso.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import devandroid.misael.applistacurso.db.AppListaDB;
import devandroid.misael.applistacurso.model.Course;
import devandroid.misael.applistacurso.model.Person;

public class CourseController {

    public CourseController() {}

    public Course addCourse(String courseName, Context ctx) {
        int courseId = -1;
        if (validateCourse(courseName)) {
            AppListaDB dbHelper = AppListaDB.getInstance(ctx);
            ContentValues values = new ContentValues();
            values.put("course_name", courseName);
            courseId = dbHelper.saveCourse(values);
        }
        if(courseId == -1) {
            return null;
        } else {
            return new Course(courseName, courseId);
        }
    }

    public ArrayList<Course> getCourses(Context ctx) {
        AppListaDB dbHelper = AppListaDB.getInstance(ctx);
        return dbHelper.getCourses();
    }

    public static boolean validateCourse(String courseName) {
        return !courseName.isEmpty();
    }

    public ArrayList<String> getCourseNames(Context ctx) {
        List<Course> courses = getCourses(ctx);
        ArrayList<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getName());
        }
        return courseNames;

    }

    public void deleteCourse(int id, Context ctx) {
        AppListaDB dbHelper = AppListaDB.getInstance(ctx);
        dbHelper.deleteCourse(id);
    }

}
