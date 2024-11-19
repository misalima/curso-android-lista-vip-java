package devandroid.misael.applistacurso.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import devandroid.misael.applistacurso.model.Course;
import devandroid.misael.applistacurso.model.Person;

public class AppListaDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_lista_curso.db";
    private static final int DATABASE_VERSION = 1;
    private static AppListaDB instance;

    Cursor cursor;
    SQLiteDatabase db;

    public AppListaDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public static synchronized AppListaDB getInstance(Context context) {
        if (instance == null) {
            instance = new AppListaDB(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String personSQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT, desired_course TEXT, phone TEXT)";
        String courseSQL = "CREATE TABLE courses (id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT)";
        db.execSQL(personSQL);
        db.execSQL(courseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS person");
        db.execSQL("DROP TABLE IF EXISTS course");
        onCreate(db);
    }

    public void savePerson(ContentValues person) {
        db.insert("person", null, person);
    }

    @SuppressLint("Range")
    public ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM person", null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                person.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                person.setDesiredCourse(cursor.getString(cursor.getColumnIndex("desired_course")));
                person.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                persons.add(person);
            } while (cursor.moveToNext());
        }
        return persons;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM courses", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Course course = new Course(cursor.getString(cursor.getColumnIndex("course_name")), cursor.getInt(cursor.getColumnIndex("id")));
                courses.add(course);
            } while (cursor.moveToNext());
        }
        return courses;
    }

    public int saveCourse(ContentValues course) {
        int id = -1;
        id = (int) db.insert("courses", null, course);
        return id;
    }

    public void deleteCourse(int id) {
        String deleteSql = "DELETE FROM courses WHERE id = " + id;
        db.execSQL(deleteSql);
    }
}



