package rortegag.boker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import rortegag.boker.main.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferences_boker), Context.MODE_PRIVATE);
            boolean isLogin = sharedPreferences.getBoolean(getString(R.string.preferences_is_login), false);
            if(isLogin) {
                new Handler().postDelayed(() -> {
                    finish();
                    Bundle bundle = new Bundle();
                    bundle.putString("user", getString(R.string.preferences_user));
                    bundle.putString("email", getString(R.string.preferences_email));
                    startActivity(new Intent(SplashScreen.this, MainActivity.class), bundle);
                }, 4000);
            } else {
                new Handler().postDelayed(() -> {
                    finish();
                    startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
                }, 4000);
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_SHORT).show();
        }

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
}
