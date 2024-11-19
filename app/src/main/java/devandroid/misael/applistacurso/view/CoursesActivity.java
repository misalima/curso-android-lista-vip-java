package devandroid.misael.applistacurso.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import devandroid.misael.applistacurso.R;
import devandroid.misael.applistacurso.controller.CourseController;
import devandroid.misael.applistacurso.databinding.ActivityCoursesBinding;
import devandroid.misael.applistacurso.model.Course;

public class CoursesActivity extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter adapter;
    private ArrayList<Course> coursesList;
    private CourseController courseController = new CourseController();
    private ActivityCoursesBinding binding;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navHome:
                    finish();
                    break;
                case R.id.navList:
                    Intent intentList = new Intent(CoursesActivity.this, PeopleListActivity.class);
                    startActivity(intentList);
                    break;
                case R.id.navCourses:
                    Toast.makeText(CoursesActivity.this, "Courses!", Toast.LENGTH_SHORT).show();
                    break;
            }
            binding.drawerLayout.closeDrawers();
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.coursesList);

        initCourses();

        adapter = new CustomAdapter(this, coursesList, courseController);
        listView.setAdapter(adapter);

        binding.fab.setOnClickListener(view -> {
            EditText inputCourse = binding.newCourseEditText;
            String courseName = inputCourse.getText().toString();
            Course newCourse = courseController.addCourse(courseName, CoursesActivity.this);
            if (newCourse == null) {
                Toast.makeText(CoursesActivity.this, "Algo não deu certo ao adicionar o curso", Toast.LENGTH_SHORT).show();
                return;
            }
            coursesList.add(newCourse);
            adapter.notifyDataSetChanged();
            inputCourse.setText("");
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void initCourses() {
        coursesList = courseController.getCourses(CoursesActivity.this);
    }
}