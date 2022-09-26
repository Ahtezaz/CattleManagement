package com.app.cattlemanagement.activities.auth;

import static com.app.cattlemanagement.utils.Constant.BUYER;
import static com.app.cattlemanagement.utils.Constant.FARM_OWNER;
import static com.app.cattlemanagement.utils.Constant.GET_ROLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cattlemanagement.R;

public class GatewayActivity extends AppCompatActivity {
    TextView register;
    Button farmer, buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gateway);
        initViews();
        /**
         * go to sign up  screen
         */
        register.setOnClickListener(v -> {
            startActivity(new Intent(this, AuthRegistration.class));
        });
        /**
         * go to sign in  screen
         */
        farmer.setOnClickListener(v -> {
            startActivity(new Intent(this, AuthLoginActivity.class)
                    .putExtra(GET_ROLE, FARM_OWNER));
        });
        /**
         * go to sign in  screen
         */
        buyer.setOnClickListener(v -> {
            startActivity(new Intent(this, AuthLoginActivity.class).putExtra(GET_ROLE, BUYER));
        });

    }

    private void initViews() {
        register = findViewById(R.id.tv_register);
        farmer = findViewById(R.id.btn_farmer);
        buyer = findViewById(R.id.btn_buyer);
    }
}