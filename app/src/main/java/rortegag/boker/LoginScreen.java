package rortegag.boker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import rortegag.boker.main.MainActivity;

public class LoginScreen extends AppCompatActivity {

    private EditText txtCorreoUsuario, txtContrasenna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio_sesion);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        txtCorreoUsuario = (EditText)findViewById(R.id.editCorreoUsuario);
        txtContrasenna = (EditText)findViewById(R.id.editContrasenna);

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

    public void iniciarSesion(View view) {
        String correo = txtCorreoUsuario.getText().toString();
        String pass = txtContrasenna.getText().toString();

        if(!correo.equals("ejemplo@ejemplo.com"))
        {
            Toast.makeText(this, "Correo o usuario incorrectos", Toast.LENGTH_SHORT).show();
        } else if (!pass.equals("1234")) {
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences preferencias = getSharedPreferences(getString(R.string.preferencias_boker), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString(getString(R.string.preferencias_email), correo);
            editor.putBoolean(getString(R.string.preferencias_isLogin), true);
            editor.commit();

            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
