package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.CATTLE_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.COWBULL;
import static com.app.cattlemanagement.utils.Constant.GOAT;
import static com.app.cattlemanagement.utils.Constant.GOAT_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.SHEEP;
import static com.app.cattlemanagement.utils.Constant.SHEEP_COLLECTION;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.remote.FirebaseLiveStockRecord;
import com.app.cattlemanagement.utils.DialogUtils;
import com.google.android.material.snackbar.Snackbar;

public class LiveStockDetailRecord extends AppCompatActivity {
    TextView tvMaleCount, tvFemaleCount, tvPregnantCount, fullyVaccinatedCount, partiallyVaccinated, nonVaccinated, disesaedStatus,
            firstBreedName, firstBreedValue, secondBreedName, fourthBreedName, secondBreedValue, thirdBreedName, thirdBreedValue, totalLiveStock, fourthBreedValue, healthy;
    String getRole;
    FirebaseLiveStockRecord liveStockRecord;
    Dialog dialog;
    String[] breedArray = null;

    private void initViews() {
        tvMaleCount = findViewById(R.id.tvMaleCount);
        fourthBreedName = findViewById(R.id.fourthBreedName);
        fourthBreedValue = findViewById(R.id.fourthBreedValue);
        healthy = findViewById(R.id.healthy);
        tvFemaleCount = findViewById(R.id.tvFemaleCount);
        tvPregnantCount = findViewById(R.id.tvPregnantCount);
        fullyVaccinatedCount = findViewById(R.id.fullyVaccinatedCount);
        partiallyVaccinated = findViewById(R.id.partiallyVaccinated);
        nonVaccinated = findViewById(R.id.nonVaccinated);
        disesaedStatus = findViewById(R.id.disesaedStatus);
        firstBreedName = findViewById(R.id.firstBreedName);
        firstBreedValue = findViewById(R.id.firstBreedValue);
        secondBreedName = findViewById(R.id.secondBreedName);
        secondBreedValue = findViewById(R.id.secondBreedValue);
        thirdBreedName = findViewById(R.id.thirdBreedName);
        thirdBreedValue = findViewById(R.id.thirdBreedValue);
        totalLiveStock = findViewById(R.id.totalLiveStock);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stock_detail_record);
        getRole = getIntent().getStringExtra(ANIMAL);
        liveStockRecord = new FirebaseLiveStockRecord();
        initViews();
        dialog = new Dialog(this);
        DialogUtils.initLoadingDialog(dialog);
        fetchLiveStockRecord();
    }


    private void fetchLiveStockRecord() {
        switch (getRole) {
            case COWBULL:
                breedArray = new String[]{"Chollistani", "Lohani", "Red Sindhi", "Tharparkar"};
                setBreedTextViews();
                liveStockRecord.getLiveStockRecord(dialog, this, CATTLE_COLLECTION, tvMaleCount, tvFemaleCount, tvPregnantCount, fullyVaccinatedCount, partiallyVaccinated, nonVaccinated, disesaedStatus,
                        firstBreedValue, secondBreedValue, thirdBreedValue, totalLiveStock, healthy, fourthBreedValue, breedArray);
                break;
            case GOAT:
                breedArray = new String[]{"Beetal", "Barbari", "Kamori", "Nachi"};
                setBreedTextViews();
                liveStockRecord.getLiveStockRecord(dialog, this, GOAT_COLLECTION, tvMaleCount, tvFemaleCount, tvPregnantCount, fullyVaccinatedCount, partiallyVaccinated, nonVaccinated, disesaedStatus,
                        firstBreedValue, secondBreedValue, thirdBreedValue, totalLiveStock, healthy, fourthBreedValue, breedArray);
                break;
            case SHEEP:
                breedArray = new String[]{"Balkhi", "Baluchi", "Cholistani ", "Damani "};
                setBreedTextViews();
                liveStockRecord.getLiveStockRecord(dialog, this, SHEEP_COLLECTION, tvMaleCount, tvFemaleCount, tvPregnantCount, fullyVaccinatedCount, partiallyVaccinated, nonVaccinated, disesaedStatus,
                        firstBreedValue, secondBreedValue, thirdBreedValue, totalLiveStock, healthy, fourthBreedValue, breedArray);
                break;
            default:
                showSnackBar("Default Case");
        }
    }

    private void setBreedTextViews() {
        firstBreedName.setText(breedArray[0]);
        secondBreedName.setText(breedArray[1]);
        thirdBreedName.setText(breedArray[2]);
        fourthBreedName.setText(breedArray[3]);
    }

    public void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.tvMaleCount), message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.redish1)).show();
    }


}