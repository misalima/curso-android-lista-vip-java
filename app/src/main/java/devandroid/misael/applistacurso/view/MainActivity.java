package devandroid.misael.applistacurso.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import devandroid.misael.applistacurso.R;
import devandroid.misael.applistacurso.controller.CourseController;
import devandroid.misael.applistacurso.controller.PersonController;
import devandroid.misael.applistacurso.model.Course;
import devandroid.misael.applistacurso.model.Person;

public class MainActivity extends AppCompatActivity {

    EditText inputFirstName;
    EditText inputLastName;
    EditText inputPhoneNumber;

    Button btnClear;
    Button btnSave;
    Button btnFinish;

    Spinner spinnerCourses;

    PersonController personController;
    CourseController courseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        courseController = new CourseController();
        personController = new PersonController();
        courseController.setCoursesList(new ArrayList<Course>(
                List.of(new Course("Java"), new Course("Kotlin"), new Course("Swift"), new Course("Python"))
        ));
        initViews();
        btnClear.setOnClickListener(this::clearFields);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Bye!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
                Person person = new Person(inputFirstName.getText().toString(), inputLastName.getText().toString(), inputPhoneNumber.getText().toString());
                person.setDesiredCourse(spinnerCourses.getSelectedItem().toString());
                boolean isSaved = personController.savePerson(person);

                if(isSaved) {
                    clearFields(view);
                    Toast.makeText(MainActivity.this, "Person saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        btnFinish = findViewById(R.id.btnFinish);
        spinnerCourses = findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseController.getCourseNames());

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerCourses.setAdapter(adapter); // set the adapter to provide layout of rows and content
    }

    private void clearFields(View v) {
        inputFirstName.setText("");
        inputLastName.setText("");
        inputPhoneNumber.setText("");
    }

    private boolean validateFields() {
        if (inputFirstName.getText().toString().isEmpty()) {
            inputFirstName.setError("First name is required");
            return false;
        }
        if (inputLastName.getText().toString().isEmpty()) {
            inputLastName.setError("Last name is required");
            return false;
        }
        if (inputPhoneNumber.getText().toString().isEmpty()) {
            inputPhoneNumber.setError("Phone number is required");
            return false;
        }
        return true;
    }
}