package devandroid.misael.applistacurso.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import devandroid.misael.applistacurso.R;
import devandroid.misael.applistacurso.model.Person;

public class MainActivity extends AppCompatActivity {

    EditText inputFirstName;
    EditText inputLastName;
    EditText inputCourseName;
    EditText inputPhoneNumber;

    Button btnClear;
    Button btnSave;
    Button btnFinish;

    ArrayList<Person> people = new ArrayList<Person>();

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
                if (validateFields()) {
                    String firstName = inputFirstName.getText().toString();
                    String lastName = inputLastName.getText().toString();
                    String courseName = inputCourseName.getText().toString();
                    String phoneNumber = inputPhoneNumber.getText().toString();
                    Person person = new Person(firstName, lastName, courseName, phoneNumber);
                    people.add(person);
                    clearFields(view);
                    Toast.makeText(MainActivity.this, "Person saved! (" + person.getFullName() + ")", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputCourseName = findViewById(R.id.inputCourseName);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        btnFinish = findViewById(R.id.btnFinish);
    }

    private void clearFields(View v) {
        inputFirstName.setText("");
        inputLastName.setText("");
        inputCourseName.setText("");
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
        if (inputCourseName.getText().toString().isEmpty()) {
            inputCourseName.setError("Course name is required");
            return false;
        }
        if (inputPhoneNumber.getText().toString().isEmpty()) {
            inputPhoneNumber.setError("Phone number is required");
            return false;
        }
        return true;
    }
}