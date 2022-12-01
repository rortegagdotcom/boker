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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import rortegag.boker.main.MainActivity;

public class SignUpScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Firebase

    private EditText editEmailSignUp, editUserSignUp, editPasswordSignUp, editRepeatPasswordSignUp;
    private CheckBox chkTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_signup);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        mAuth = FirebaseAuth.getInstance();

        editEmailSignUp = findViewById(R.id.editEmailSignUp);
        editUserSignUp = findViewById(R.id.editUserSignUp);
        editPasswordSignUp = findViewById(R.id.editPasswordSignUp);
        editRepeatPasswordSignUp = findViewById(R.id.editRepeatPasswordSignUp);

        chkTerms = findViewById(R.id.chkTerms);

        Button btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this::signUp);
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

    public void signUp(View view) {
        String email = editEmailSignUp.getText().toString().trim();
        String user = editUserSignUp.getText().toString().trim();
        String password = editPasswordSignUp.getText().toString().trim();
        String repeatPassword = editRepeatPasswordSignUp.getText().toString().trim();

        if(email.isEmpty() && user.isEmpty() && password.isEmpty() && repeatPassword.isEmpty()) {
            Toast.makeText(this, "Some of the fields are empty.", Toast.LENGTH_SHORT).show();
        } else if(email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")) {
            Toast.makeText(this, "The email address is not correct.", Toast.LENGTH_SHORT).show();
        } else if(!password.contentEquals(repeatPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        } else if(!chkTerms.isChecked()) {
            Toast.makeText(this, "Please agree to Boker's terms and conditions.", Toast.LENGTH_SHORT).show();
        } else {
            connectSignUpFirebase(email, user, password);
        }
    }

    public void connectSignUpFirebase(String email, String user, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferences_boker), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.preferences_user), user);
                editor.putString(getString(R.string.preferences_email), email);
                editor.putBoolean(getString(R.string.preferences_is_login), true);
                editor.apply();
                finish();
                startActivity(new Intent(SignUpScreen.this, MainActivity.class));
            } else {
                Toast.makeText(SignUpScreen.this, "There was an error creating the user, try again.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(SignUpScreen.this, "There was an error creating the user, try again.", Toast.LENGTH_SHORT).show());
    }
}
