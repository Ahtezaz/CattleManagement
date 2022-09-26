package com.app.cattlemanagement.data.remote;

import static com.app.cattlemanagement.utils.Constant.BREED;
import static com.app.cattlemanagement.utils.Constant.DISEASED;
import static com.app.cattlemanagement.utils.Constant.FARMER_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.FEMALE;
import static com.app.cattlemanagement.utils.Constant.GENDER;
import static com.app.cattlemanagement.utils.Constant.HEALTHY;
import static com.app.cattlemanagement.utils.Constant.HEALTH_STATUS;
import static com.app.cattlemanagement.utils.Constant.IS_PREGNANT;
import static com.app.cattlemanagement.utils.Constant.MALE;
import static com.app.cattlemanagement.utils.Constant.REMAINING_VACCCINE;
import static com.app.cattlemanagement.utils.Constant.VACCINE_COMPLETED;
import static com.app.cattlemanagement.utils.Constant.YES;
import static com.app.cattlemanagement.utils.Constant.ZERO;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.app.cattlemanagement.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class FirebaseLiveStockRecord {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Context context;
    String TAG = "TAG";
    int counterAnimals = 0;

    public void getLiveStockRecord(Dialog dialog, Context liveContext,
                                   String cattleCollection, TextView tvMaleCount, TextView tvFemaleCount,
                                   TextView tvPregnantCount, TextView fullyVaccinatedCount,
                                   TextView partiallyVaccinated, TextView nonVaccinated,
                                   TextView diseasedStatus,
                                   TextView firstBreedValue,
                                   TextView secondBreedValue,
                                   TextView thirdBreedValue, TextView totalLiveStock,
                                   TextView healthy, TextView fourthBreedValue, String[] breed) {
        context = liveContext;
        dialog.show();
        counterAnimals = 0;
        countLiveRecord(dialog, GENDER, MALE, tvMaleCount, cattleCollection);
        /**
         * Male Count
         */

        /**
         * Female Count
         */
        countLiveRecord(dialog, GENDER, FEMALE, tvFemaleCount, cattleCollection);

        /**
         * Pregnant Count
         */
        countLiveRecord(dialog, IS_PREGNANT, YES, tvPregnantCount, cattleCollection);

        /**
         * Fully Vaccinated
         */
        countLiveRecord(dialog, REMAINING_VACCCINE, ZERO, fullyVaccinatedCount, cattleCollection);
        /**
         * partially Vaccinated
         */
        countLiveRecordForNot(dialog, REMAINING_VACCCINE, ZERO, partiallyVaccinated, cattleCollection);
        /**
         * Un-Vaccinated
         */
        countLiveRecord(dialog, VACCINE_COMPLETED, ZERO, nonVaccinated, cattleCollection);
        /**
         * Diseased Count
         */
        countLiveRecord(dialog, HEALTH_STATUS, DISEASED, diseasedStatus, cattleCollection);
        /**
         * Healthy Count
         */
        countLiveRecord(dialog, HEALTH_STATUS, HEALTHY, healthy, cattleCollection);
        /**
         * Breed Count
         */
        countLiveRecord(dialog, BREED, breed[0], firstBreedValue, cattleCollection);
        countLiveRecord(dialog, BREED, breed[1], secondBreedValue, cattleCollection);
        countLiveRecord(dialog, BREED, breed[2], thirdBreedValue, cattleCollection);
        countLiveRecord(dialog, BREED, breed[3], fourthBreedValue, cattleCollection);
        new Handler().postDelayed(() -> {
            dialog.dismiss();
            totalLiveStock.setText(String.valueOf(counterAnimals));
        }, 3000);
    }

    private void countLiveRecord(Dialog dialog, String key, String valueKey, TextView tvToCount, String cattleCollection) {
        new Handler().post(() -> {
            db.collection(FARMER_COLLECTION).document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).
                    collection(cattleCollection).whereEqualTo(key, valueKey).addSnapshotListener(((value, error) -> {
                        if (error != null) {
                            showSnackBar(error.getMessage(), tvToCount);
                            dialog.dismiss();
                        }
                        assert value != null;
                        if (value.isEmpty()) {
                            tvToCount.setText("0");
                            if (valueKey.equals(MALE) || valueKey.equals(FEMALE)) {
                                counterIncrement(0);
                            }

                            Log.d(TAG, "getLiveStockRecord: Empty");

                        } else {
                            tvToCount.setText(String.valueOf(value.size()));
                            if (valueKey.equals(MALE) || valueKey.equals(FEMALE)) {
                                counterIncrement(value.size());
                            }

                            Log.d(TAG, "getLiveStockRecord: " + value.size());
                        }
                    }));

        });
    }

    private synchronized void counterIncrement(int value) {
        counterAnimals += value;
    }

    private void countLiveRecordForNot(Dialog dialog, String key, String valueKey, TextView tvToCount, String cattleCollection) {
        new Handler().post(() -> {
            db.collection(FARMER_COLLECTION).document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).
                    collection(cattleCollection).whereNotEqualTo(key, valueKey).addSnapshotListener(((value, error) -> {
                        if (error != null) {
                            showSnackBar(error.getMessage(), tvToCount);
                            dialog.dismiss();
                        }
                        assert value != null;
                        if (value.isEmpty()) {
                            tvToCount.setText("0");
                            Log.d(TAG, "getLiveStockRecord: Empty");

                        } else {
                            tvToCount.setText(String.valueOf(value.size()));

                            Log.d(TAG, "getLiveStockRecord: " + value.size());
                        }
                    }));

        });
    }

    public void showSnackBar(String message, TextView id) {
        Snackbar.make(id, message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(context, R.color.redish1)).show();
    }
}