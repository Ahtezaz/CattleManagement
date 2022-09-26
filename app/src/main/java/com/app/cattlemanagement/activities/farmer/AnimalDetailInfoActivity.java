package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.CATTLE_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.FEMALE;
import static com.app.cattlemanagement.utils.Constant.GOAT_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.SHEEP;
import static com.app.cattlemanagement.utils.Constant.YES;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.entity.Animal;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AnimalDetailInfoActivity extends AppCompatActivity {
    Animal animal;
    TextView tvTitle, tvTag, tvDescAnimal, tvHealthDesc, tvGender,
            tvWeight, tvBreed, tvDob, tvOnFarm, tvPregnant, tvObtainedSource, tvPregnancyStart, tvDeliveryDate, tvRemainingDays,
            tvVaccineCompeleteCount, tvVaccine1, tvVaccine2, tvVaccine3,
            tvVaccineDate1, tvVaccineDate2, tvVaccineDate3, health_check,
            tvRemainingCount, tvRVaccineName1, tvRVaccineName2, tvRVaccineName3,
            tvRVaccineNameDate1, tvRVaccineNameDate2, tvRVaccineNameDate3, tvNoOfChild, tvAgainExpected;
    ImageView ImSrc;
    CardView isPregnant;
    CardView maternityCard;
    Date todayDate, pregnancyDate;
    String couldExpectAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        animal = getIntent().getParcelableExtra(ANIMAL);
        Log.d("UNIQUE", animal.toString());
        initViews();
        setContent();
    }

    private void initViews() {
        ImSrc = findViewById(R.id.ImSrc);
        tvTitle = findViewById(R.id.tvTitle);
        health_check = findViewById(R.id.health_check);
        tvTag = findViewById(R.id.tvTag);
        tvDescAnimal = findViewById(R.id.tvDescAnimal);

        tvGender = findViewById(R.id.tvGenderType);
        tvWeight = findViewById(R.id.tvWeight);
        tvBreed = findViewById(R.id.tvBreed);
        tvDob = findViewById(R.id.tvDob);
        tvOnFarm = findViewById(R.id.tvOnFarm);
        tvPregnant = findViewById(R.id.tvPregnant);
        tvObtainedSource = findViewById(R.id.tvObtainedSource);
        maternityCard = findViewById(R.id.maternityCard);
        tvDeliveryDate = findViewById(R.id.tvDeliveryDate);
        tvPregnancyStart = findViewById(R.id.tvPregnancyStart);
        tvRemainingDays = findViewById(R.id.tvRemainingDays);
        tvAgainExpected = findViewById(R.id.tvAgainExpected);
        tvNoOfChild = findViewById(R.id.tvNoOfChild);
        tvVaccineCompeleteCount = findViewById(R.id.tvVaccineCompeleteCount);
        tvRemainingCount = findViewById(R.id.tvRemainingCount);
        tvVaccine1 = findViewById(R.id.tvVaccine1);
        tvVaccine2 = findViewById(R.id.tvVaccine2);
        tvVaccine3 = findViewById(R.id.tvVaccine3);
        tvVaccineDate1 = findViewById(R.id.tvVaccineDate1);
        tvVaccineDate2 = findViewById(R.id.tvVaccineDate2);
        tvVaccineDate3 = findViewById(R.id.tvVaccineDate3);
        tvRVaccineName1 = findViewById(R.id.tvRVaccineName1);
        tvRVaccineName2 = findViewById(R.id.tvRVaccineName2);
        tvRVaccineName3 = findViewById(R.id.tvRVaccineName3);
        tvRVaccineNameDate1 = findViewById(R.id.tvRVaccineNameDate1);
        tvRVaccineNameDate2 = findViewById(R.id.tvRVaccineNameDate2);
        tvRVaccineNameDate3 = findViewById(R.id.tvRVaccineNameDate3);
        tvHealthDesc = findViewById(R.id.tvHealthDesc);

        isPregnant = findViewById(R.id.cardIsPregnant);

    }

    private void setContent() {
        Glide.with(this).load(animal.getImageUri()).into(ImSrc);
        tvTitle.setText(animal.getName());
        tvTag.setText(animal.getTagNo());
        tvDescAnimal.setText(animal.getDesc());
        tvHealthDesc.setText(animal.getVaccineDesc());
        Log.d("TAG", "setContent: ");
        tvGender.setText(animal.getGender());
        if (Objects.equals(animal.getGender(), FEMALE) && Objects.equals(animal.getIsPregnant(), YES)) {
            Log.d("TAG", "setContent: IN IF BLOCK ");
            handleMaternityCycle();
        } else {
            maternityCard.setVisibility(View.GONE);
            Log.d("TAG", "setContent: IN ELSE BLOCK ");
        }
        health_check.setText(animal.getHealthStatus());
        tvBreed.setText(animal.getBreed());
        tvWeight.setText(animal.getWeight());
        tvDob.setText(animal.getDateOfBirth());
        tvOnFarm.setText(animal.getDateOfFarm());
        tvObtainedSource.setText(animal.getObtainedSource());
        if (animal.getGender().equals("Female")) {
            tvPregnant.setText(animal.getIsPregnant());
        } else {
            isPregnant.setVisibility(View.GONE);
        }
        vaccineCompleteSetter();
        vaccineRemainingSetter();

    }

    private void vaccineRemainingSetter() {
        tvRemainingCount.setText(animal.getRemainingVaccine());
        if (empty(animal.getRfirstVaccineName())) {
            tvRVaccineName1.setVisibility(View.GONE);
            tvRVaccineNameDate1.setVisibility(View.GONE);
        } else {
            tvRVaccineName1.setText(animal.getRfirstVaccineName());
            tvRVaccineNameDate1.setText(animal.getRfirstVaccineDate());
        }
        if (empty(animal.getRsecondVaccineName())) {
            tvRVaccineName2.setVisibility(View.GONE);
            tvRVaccineNameDate2.setVisibility(View.GONE);
        } else {
            tvRVaccineName2.setText(animal.getRsecondVaccineName());
            tvRVaccineNameDate2.setText(animal.getRsecondVaccineDate());
        }
        if (empty(animal.getRthirdVaccineName())) {
            tvRVaccineName3.setVisibility(View.GONE);
            tvRVaccineNameDate3.setVisibility(View.GONE);
        } else {
            tvRVaccineName3.setText(animal.getRthirdVaccineName());
            tvRVaccineNameDate3.setText(animal.getRthirdVaccineDate());
        }
    }

    private void vaccineCompleteSetter() {
        tvVaccineCompeleteCount.setText(animal.getVaccineComplete());
        if (empty(animal.getFirstVaccineName())) {
            tvVaccine1.setVisibility(View.GONE);
            tvVaccineDate1.setVisibility(View.GONE);
        } else {
            tvVaccine1.setText(animal.getFirstVaccineName());
            tvVaccineDate1.setText(animal.getFirstVaccineDate());
        }
        if (empty(animal.getSecondVaccineName())) {
            tvVaccine2.setVisibility(View.GONE);
            tvVaccineDate2.setVisibility(View.GONE);
        } else {
            tvVaccine2.setText(animal.getSecondVaccineName());
            tvVaccineDate2.setText(animal.getSecondVaccineDate());
        }
        if (empty(animal.getThirdVaccineName())) {
            tvVaccine3.setVisibility(View.GONE);
            tvVaccineDate3.setVisibility(View.GONE);
        } else {
            tvVaccine3.setText(animal.getThirdVaccineName());
            tvVaccineDate3.setText(animal.getThirdVaccineDate());
        }
    }

    public boolean empty(String check) {
        return check.isEmpty();
    }

    private void handleMaternityCycle() {
        if (animal.getRefDoc().contains(CATTLE_COLLECTION)) {

            tvPregnancyStart.setText(animal.getPregnantMonth());
            calculateDeliveryDate(CATTLE_COLLECTION, 280);
            calculateDaysRemaining();
            tvNoOfChild.setText(animal.getChildNo());
            againExpectedDate(370);
        } else if (animal.getRefDoc().contains(SHEEP)) {

            tvPregnancyStart.setText(animal.getPregnantMonth());
            calculateDeliveryDate(CATTLE_COLLECTION, 152);
            calculateDaysRemaining();
            tvNoOfChild.setText(animal.getChildNo());
            againExpectedDate(55);
        } else if (animal.getRefDoc().contains(GOAT_COLLECTION)) {
            tvPregnancyStart.setText(animal.getPregnantMonth());
            calculateDeliveryDate(CATTLE_COLLECTION, 150);
            calculateDaysRemaining();
            tvNoOfChild.setText(animal.getChildNo());
            againExpectedDate(60);
        }
    }

    private void againExpectedDate(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar expectedCalender = Calendar.getInstance();
        expectedCalender.setTime(new Date(animal.getPregnantMonth())); // Using today's date
        expectedCalender.add(Calendar.DATE, i); // Adding 5 days
        couldExpectAgain = sdf.format(expectedCalender.getTime());
        tvAgainExpected.setText(couldExpectAgain);
    }

    private void calculateDaysRemaining() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar todayCalender = Calendar.getInstance();

        todayCalender.setTime(new Date()); // Using today's date
        String output = dateFormat.format(todayCalender.getTime());
        todayDate = todayCalender.getTime();
        long diff = pregnancyDate.getTime() - todayDate.getTime();
        long days = diff / 1000 / 60 / 60 / 24;
        String daysInString = Long.toString(days);
        tvRemainingDays.setText(daysInString);
    }

    private void calculateDeliveryDate(String type, int days) {
        switch (type) {
            case CATTLE_COLLECTION:
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar expectedCalender = Calendar.getInstance();
                expectedCalender.setTime(new Date(animal.getPregnantMonth())); // Using today's date
                expectedCalender.add(Calendar.DATE, days); // Adding 5 days
                String output = sdf.format(expectedCalender.getTime());
                pregnancyDate = expectedCalender.getTime();
                tvDeliveryDate.setText(output);
        }
    }


}