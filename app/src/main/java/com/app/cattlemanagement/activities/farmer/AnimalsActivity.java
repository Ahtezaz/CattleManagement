package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.COWBULL;
import static com.app.cattlemanagement.utils.Constant.GOAT;
import static com.app.cattlemanagement.utils.Constant.SHEEP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cattlemanagement.R;
import com.google.android.material.card.MaterialCardView;

public class AnimalsActivity extends AppCompatActivity {
    MaterialCardView crdBull;
    MaterialCardView crdGoat;
    MaterialCardView crdSheep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        initViews();
        crdBull.setOnClickListener(view -> startActivity(new Intent(this, AddAnimal.class).putExtra(ANIMAL , COWBULL)));
        crdGoat.setOnClickListener(view -> startActivity(new Intent(this, AddAnimal.class).putExtra(ANIMAL, GOAT)));
        crdSheep.setOnClickListener(view -> startActivity(new Intent(this, AddAnimal.class).putExtra(ANIMAL, SHEEP)));
    }

    private void initViews() {
        crdBull = findViewById(R.id.crdCowBull);
        crdGoat = findViewById(R.id.crdGoat);
        crdSheep = findViewById(R.id.crdSheep);
    }

    public void back(View view) {
        finish();
    }

}