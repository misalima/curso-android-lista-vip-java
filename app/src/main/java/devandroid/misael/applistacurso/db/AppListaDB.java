package devandroid.misael.applistacurso.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppListaDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_lista_curso.db";
    private static final int DATABASE_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public AppListaDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        db = getWritableDatabase();
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
}



