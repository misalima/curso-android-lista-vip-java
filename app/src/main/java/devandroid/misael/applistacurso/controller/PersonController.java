package devandroid.misael.applistacurso.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import devandroid.misael.applistacurso.db.AppListaDB;
import devandroid.misael.applistacurso.model.Person;

public class PersonController {


    public PersonController() {}

    public boolean savePerson(Person person, Context ctx) {
        if (validatePerson(person)) {
            AppListaDB dbHelper = AppListaDB.getInstance(ctx);
            ContentValues values = new ContentValues();
            values.put("first_name", person.getFirstName());
            values.put("last_name", person.getLastName());
            values.put("desired_course", person.getDesiredCourse());
            values.put("phone", person.getPhone());
            dbHelper.savePerson(values);

            return true;
        }
        return false;
    }

    public ArrayList<Person> getPersons(Context ctx) {
        AppListaDB dbHelper = AppListaDB.getInstance(ctx);
        return dbHelper.getPersons();
    }

    public static boolean validatePerson(Person person) {
        return !person.getFirstName().isEmpty() && !person.getLastName().isEmpty() && !person.getDesiredCourse().isEmpty();
    }


}
