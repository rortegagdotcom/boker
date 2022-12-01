package com.rortegag.boker;

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

import java.util.Objects;

public class SignUpScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

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
        database = FirebaseDatabase.getInstance("https://boker-369819-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference("message");

        databaseReference.setValue("Hello, World!");

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
                String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                registerUserToRealtimeDatabase(uid, user, email, password);
            } else {
                Toast.makeText(SignUpScreen.this, "There was an error creating the user, try again.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(SignUpScreen.this, "There was an error creating the user, try again.", Toast.LENGTH_SHORT).show());
    }

    public void registerUserToRealtimeDatabase(String uid, String user, String email, String password) {
        /*
        mFirestore.collection("user").document(uid).set(map).addOnSuccessListener(unused -> {
            finish();
            startActivity(new Intent(SignUpScreen.this, MainActivity.class));
            Toast.makeText(SignUpScreen.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(SignUpScreen.this, "Error saving user data in the Boker database. Please contact the administrator.", Toast.LENGTH_SHORT).show());
         */
    }
}
