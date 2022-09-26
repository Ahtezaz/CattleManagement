package com.app.cattlemanagement.data.remote;

import static com.app.cattlemanagement.utils.Constant.CONSUMED;
import static com.app.cattlemanagement.utils.Constant.REMAINING;
import static com.app.cattlemanagement.utils.Constant.TOTAL_QUANTITY;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.entity.Feed;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Objects;

public class FirebaseFeedStock {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Context context;
    String TAG = "TAG";


    public void getFeedStock(String collection, String feed_collection, String feed_id1, String feed_id2, String feed_id3, TextView totalQuantity1, TextView totalQuantity2, TextView totalQuantity3,
                             TextView consumed1, TextView consumed2, TextView consumed3, TextView remaining1,
                             TextView remaining2, TextView remaining3, Context feedStockDetailsActivity, Dialog dialog) {
        dialog.show();
        /**
         * get Feed
         */
        getFeedData(collection, feed_collection, feed_id1, consumed1, remaining1, totalQuantity1);
        getFeedData(collection, feed_collection, feed_id2, consumed2, remaining2, totalQuantity2);
        getFeedData(collection, feed_collection, feed_id3, consumed3, remaining3, totalQuantity3);

        new Handler().postDelayed(dialog::dismiss, 3000);
    }

    private void getFeedData(String collection, String feed_collection, String feed_id1, TextView consumed1, TextView remaining1, TextView totalQuantity1) {
        new Handler().post(() -> {
            db.collection(collection).document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection(feed_collection).document(feed_id1).addSnapshotListener(((value, error) -> {
                if (error != null) {
                    showSnackBar(error.getMessage(), totalQuantity1);
                    return;
                }
                assert value != null;
                 Feed cattleFeed = value.toObject(Feed.class);
                Log.d(TAG, "getFeedData: " + cattleFeed);
                assert cattleFeed != null;
                String cattleFeedInKg = cattleFeed.getConsumed() + " Kg";
                consumed1.setText(cattleFeedInKg);
                String cattleFeedInKg1 = cattleFeed.getTotalQuantity() + " Kg";
                totalQuantity1.setText(cattleFeedInKg1);
                String cattleFeedInKg2 = cattleFeed.getRemaining() + " Kg";
                remaining1.setText(cattleFeedInKg2);

            }));
        });
    }

    public void showSnackBar(String message, TextView id) {
        Snackbar.make(id, message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(context, R.color.redish1)).show();
    }

    public void incrementValue(String path, Dialog feedDialogue, int tvAmount, TextView textView, Dialog dialog, Context c) {
        db.document(path).get().addOnSuccessListener(documentSnapshot -> {
            Feed cattleFeed = documentSnapshot.toObject(Feed.class);
            HashMap<String, String> map = new HashMap<>();
            assert cattleFeed != null;
            int totalQuantity = Integer.parseInt(cattleFeed.getTotalQuantity()) + tvAmount;
            int remaining = totalQuantity - Integer.parseInt(cattleFeed.getConsumed());
            map.put(TOTAL_QUANTITY, String.valueOf(totalQuantity));
            map.put(REMAINING, String.valueOf(remaining));
            Log.d(TAG, "incrementValue: " + totalQuantity);
            Log.d(TAG, "incrementValue: " + remaining);
            db.document(path).set(map, SetOptions.merge());
            dialog.dismiss();
            feedDialogue.dismiss();
        });

    }

    public void decrement(String path, Dialog feedDialogue, int tvAmount, TextInputEditText amount, Dialog dialog, Context c) {
        db.document(path).get().addOnSuccessListener(documentSnapshot -> {
            Feed cattleFeed = documentSnapshot.toObject(Feed.class);
            HashMap<String, String> map = new HashMap<>();
            assert cattleFeed != null;
            int consumed = Integer.parseInt(cattleFeed.getConsumed()) + tvAmount;
            int remaining = Integer.parseInt(cattleFeed.getRemaining()) - consumed;
            map.put(CONSUMED, String.valueOf(consumed));
            map.put(REMAINING, String.valueOf(remaining));
            if (tvAmount > remaining) {
                Toast.makeText(c, "Consumed Amount Must Be Less Than Remaining Amount", Toast.LENGTH_SHORT).show();
            } else {
                db.document(path).set(map, SetOptions.merge());
            }
            dialog.dismiss();
            feedDialogue.dismiss();
        });
    }
}
