package devandroid.misael.applistacurso.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import devandroid.misael.applistacurso.R;
import devandroid.misael.applistacurso.controller.PersonController;
import devandroid.misael.applistacurso.databinding.ActivityPeopleListBinding;
import devandroid.misael.applistacurso.model.Person;

public class PeopleListActivity extends AppCompatActivity {

    private ActivityPeopleListBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPeopleListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PersonController personController = new PersonController();
        ArrayList<Person> people = personController.getPersons(this);



        NavigationView navigationView = findViewById(R.id.navView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navHome:
                    finish();
                    break;
                case R.id.navList:
                    Intent intentList = new Intent(PeopleListActivity.this, PeopleListActivity.class);
                    startActivity(intentList);
                    break;
                case R.id.navCourses:
                    Toast.makeText(PeopleListActivity.this, "Courses!", Toast.LENGTH_SHORT).show();
                    break;
            }
            binding.drawerLayout.closeDrawers();
            return true;
        });

        populatePersonList(people);

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void populatePersonList(ArrayList<Person> people) {
        TableLayout tableLayout = binding.tableLayout;
        for (Person person : people) {
            TableRow row = new TableRow(PeopleListActivity.this);
            row.setPadding(8, 8, 8, 8);

            TextView firstName = new TextView(PeopleListActivity.this);
            firstName.setText(person.getFirstName());
            firstName.setPadding(0,12, 0, 12);
            firstName.setTextAppearance(R.style.TableCellStyle);

            TextView lastName = new TextView(PeopleListActivity.this);
            lastName.setText(person.getLastName());
            lastName.setPadding(0,12, 0, 12);
            lastName.setTextAppearance(R.style.TableCellStyle);

            TextView phone = new TextView(PeopleListActivity.this);
            phone.setText(person.getPhone());
            phone.setPadding(0,12, 0, 12);
            phone.setTextAppearance(R.style.TableCellStyle);

            TextView course = new TextView(PeopleListActivity.this);
            course.setText(person.getDesiredCourse());
            course.setPadding(0,12, 0, 12);
            course.setTextAppearance(R.style.TableCellStyle);

            row.addView(firstName);
            row.addView(lastName);
            row.addView(phone);
            row.addView(course);
            tableLayout.addView(row);
        }
    }
}
