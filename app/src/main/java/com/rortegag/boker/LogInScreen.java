package rortegag.boker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import rortegag.boker.main.MainActivity;

public class LogInScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editEmailLogIn, editPasswordLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        mAuth = FirebaseAuth.getInstance();

        editEmailLogIn = findViewById(R.id.editEmailLogIn);
        editPasswordLogIn = findViewById(R.id.editPasswordLogIn);

        Button btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(this::logIn);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void logIn(View view) {
        String email = editEmailLogIn.getText().toString().trim();
        String password = editPasswordLogIn.getText().toString().trim();

        if(email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(this, "Email and/or password is empty.", Toast.LENGTH_SHORT).show();
        } else {
            connectLogInFirebase(email, password);
        }
    }

    public void connectLogInFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferences_boker), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.preferences_email), email);
                editor.putBoolean(getString(R.string.preferences_is_login), true);
                editor.apply();
                finish();
                startActivity(new Intent(LogInScreen.this, MainActivity.class));
            } else {
                Toast.makeText(LogInScreen.this, "There was an error logging in, try again.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(LogInScreen.this, "There was an error logging in, try again.", Toast.LENGTH_SHORT).show());
    }
}
