package devandroid.misael.applistacurso.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;

import devandroid.misael.applistacurso.R;
import devandroid.misael.applistacurso.db.AppListaDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switchActivity();
    }

    private void switchActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            try (AppListaDB db = new AppListaDB(SplashActivity.this)) {
                Log.i("DB", "Database initialized;");
            }
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, TIME_OUT);

    }
}