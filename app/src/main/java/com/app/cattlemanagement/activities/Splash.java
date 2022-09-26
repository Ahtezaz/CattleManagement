package com.app.cattlemanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.activities.auth.GatewayActivity;
import com.app.cattlemanagement.activities.farmer.FarmerDashboard;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /**
         * if auth has a token it will directly goes to dashboard
         * otherwise goes to login page
         */

        if (auth.getUid() != null) {
            Log.d(TAG, "onCreate: " + auth.getUid());
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, FarmerDashboard.class));
                finish();
            }, 100);
        } else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, GatewayActivity.class));
                finish();
            }, 100);
        }
    }


}