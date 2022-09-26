package com.app.cattlemanagement.activities.farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.utils.Constant;
import com.google.android.material.card.MaterialCardView;

public class LiveStockRecordActivity extends AppCompatActivity {
    MaterialCardView cardCattle, cardGoat, cardSheep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stock_record);
        initViews();
        cardCattle.setOnClickListener(click -> {
            startActivity(new Intent(this, LiveStockDetailRecord.class).putExtra(Constant.ANIMAL, Constant.COWBULL));
        });
        cardGoat.setOnClickListener(click -> {
            startActivity(new Intent(this, LiveStockDetailRecord.class).putExtra(Constant.ANIMAL, Constant.GOAT));
        });
        cardSheep.setOnClickListener(click -> {
            startActivity(new Intent(this, LiveStockDetailRecord.class).putExtra(Constant.ANIMAL, Constant.SHEEP));
        });
    }
    private void initViews() {
        cardCattle = findViewById(R.id.cardCattle);
        cardGoat = findViewById(R.id.cardGoat);
        cardSheep = findViewById(R.id.cardSheep);
    }
}