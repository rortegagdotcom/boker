package com.rortegag.boker;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rortegag.boker.main.MainActivity;
import com.rortegag.boker.models.user.User;

import java.util.Objects;

public class SignUpScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private SharedPreferences sharedPreferences;

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
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://boker-369819-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference();

        sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preferences_boker), Context.MODE_PRIVATE);

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
        String userName = editUserSignUp.getText().toString().trim();
        String password = editPasswordSignUp.getText().toString().trim();
        String repeatPassword = editRepeatPasswordSignUp.getText().toString().trim();

        if(email.isEmpty() && userName.isEmpty() && password.isEmpty() && repeatPassword.isEmpty()) {
            Toast.makeText(this, "Some of the fields are empty.", Toast.LENGTH_SHORT).show();
        } else if(email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")) {
            Toast.makeText(this, "The email address is not correct.", Toast.LENGTH_SHORT).show();
        } else if(!password.contentEquals(repeatPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        } else if(!chkTerms.isChecked()) {
            Toast.makeText(this, "Please agree to Boker's terms and conditions.", Toast.LENGTH_SHORT).show();
        } else {
            connectSignUpFirebase(email, userName, password);
        }
    }

    public void connectSignUpFirebase(String email, String userName, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                writeUserToRealtimeDatabase(uid, userName, email);
                finish();
                startActivity(new Intent(SignUpScreen.this, MainActivity.class));
            } else {
                Toast.makeText(SignUpScreen.this, "This user exists on Boker", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(SignUpScreen.this, "There was an error creating the user, try again.", Toast.LENGTH_SHORT).show());
    }

    public void writeUserToRealtimeDatabase(String uid, String userName, String email) {
        User user = new User(userName, email);
        databaseReference.child("users").child(uid).setValue(user);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.preferences_userName), userName);
        editor.putString(getString(R.string.preferences_email), email);
        editor.apply();
    }
}
