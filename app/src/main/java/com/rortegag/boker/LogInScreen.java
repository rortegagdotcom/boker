package com.rortegag.boker;

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

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rortegag.boker.main.MainActivity;
import com.rortegag.boker.models.user.User;

public class LogInScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    SharedPreferences sharedPreferences;

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
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://boker-369819-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference();

        sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preferences_boker), Context.MODE_PRIVATE);

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

        if(email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Email and/or password is empty.", Toast.LENGTH_SHORT).show();
        } else {
            connectLogInFirebase(email, password);
        }
    }

    public void connectLogInFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                readUserToRealtimeDatabase(email);
            } else {
                Toast.makeText(LogInScreen.this, "Email or password incorrect.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(LogInScreen.this, "There was an error logging in, try again.", Toast.LENGTH_SHORT).show());
    }

    public void readUserToRealtimeDatabase(String email) {
        Query query = databaseReference.child("users").orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    DataSnapshot userBoker = snapshot.getChildren().iterator().next();
                    User user = userBoker.getValue(User.class);
                    if (user != null) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.preferences_uid), userBoker.getKey());
                        editor.putString(getString(R.string.preferences_userName), user.getUserName());
                        editor.putString(getString(R.string.preferences_email), user.getEmail());
                        editor.apply();
                        finish();
                        startActivity(new Intent(LogInScreen.this, MainActivity.class));
                    } else {
                        Toast.makeText(LogInScreen.this, "Error reading user data.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LogInScreen.this, "Error reading user data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
