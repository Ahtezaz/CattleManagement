package com.app.cattlemanagement.activities.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.activities.auth.AuthLoginActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class FarmerDashboard extends AppCompatActivity {
    MaterialCardView animal , crdFeedAnimal;
    MaterialCardView liveStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_dashboard);
        InitView();

        animal.setOnClickListener(view -> startActivity(new Intent(this, AnimalsActivity.class)));
        liveStock.setOnClickListener(view -> startActivity(new Intent(this, LiveStockRecordActivity.class)));
        crdFeedAnimal.setOnClickListener(view -> startActivity(new Intent(this, FeedRecord.class)));
    }

    private void InitView() {
        animal = findViewById(R.id.crdAnimal);
        crdFeedAnimal = findViewById(R.id.crdFeedAnimal);
        liveStock = findViewById(R.id.cardViewLiveStock);
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, AuthLoginActivity.class));
        finish();
    }


}