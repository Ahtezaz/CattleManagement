package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.Barley_Feed;
import static com.app.cattlemanagement.utils.Constant.COWBULL;
import static com.app.cattlemanagement.utils.Constant.Cattle_Feed;
import static com.app.cattlemanagement.utils.Constant.Chaffhaye;
import static com.app.cattlemanagement.utils.Constant.Corn_Feed;
import static com.app.cattlemanagement.utils.Constant.DECREMENT;
import static com.app.cattlemanagement.utils.Constant.FARMER_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.GOAT;
import static com.app.cattlemanagement.utils.Constant.Goat_Feed;
import static com.app.cattlemanagement.utils.Constant.Grain;
import static com.app.cattlemanagement.utils.Constant.Grass_Feed;
import static com.app.cattlemanagement.utils.Constant.Hay;
import static com.app.cattlemanagement.utils.Constant.INCREMENT;
import static com.app.cattlemanagement.utils.Constant.SHEEP;
import static com.app.cattlemanagement.utils.Constant.Sheep_Feed;
import static com.app.cattlemanagement.utils.Constant.Silage;
import static com.app.cattlemanagement.utils.Constant.Straw;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.remote.FirebaseFeedStock;
import com.app.cattlemanagement.utils.DialogUtils;
import com.app.cattlemanagement.utils.FeedStockDialogue;
import com.app.cattlemanagement.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class FeedStockDetailsActivity extends AppCompatActivity {
    String[] feedArray = null;
    String getRole = "";
    TextView feedType1, feedType2, feedType3, totalQuantity1, totalQuantity2, totalQuantity3, consumed1, consumed2, consumed3, remaining1, remaining2, remaining3;
    FirebaseFeedStock feedStock;
    Dialog dialog;
    MaterialButton btnStock1, btnStock2, btnStock3, btnDelete1, btnDelete2, btnDelete3;
    Dialog feedDialogue;
    AppCompatButton btnDone;
    TextInputLayout tvAmountLayout;
    TextInputEditText tvAmount;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String path = "";
    String getFeedCollectionRole = "";
    String feedId;
    String operation = "";
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_stockk_details);
        getRole = getIntent().getStringExtra(ANIMAL);
        Log.d(TAG, "onCreate: " + getRole);
        feedStock = new FirebaseFeedStock();

        dialog = new Dialog(this);
        feedDialogue = new Dialog(this);
        DialogUtils.initLoadingDialog(dialog);
        FeedStockDialogue.initLoadingDialog(feedDialogue);
        initViews();
        handleFeedNames();
        btnStock1.setOnClickListener(v -> {
            feedDialogue.show();
            getCollectionId1(getFeedCollectionRole);
            path = FARMER_COLLECTION + "/" + auth.getUid() + "/" + getFeedCollectionRole + "/" + feedId;
            Log.d("TAG", "onCreate: " + path);
            operation = INCREMENT;
        });
        btnDelete1.setOnClickListener(v -> {
            feedDialogue.show();
            getCollectionId1(getFeedCollectionRole);
            path = FARMER_COLLECTION + "/" + auth.getUid() + "/" + getFeedCollectionRole + "/" + feedId;
            Log.d("TAG", "onCreate: " + path);
            operation = DECREMENT;
        });
        btnStock2.setOnClickListener(v -> {
            feedDialogue.show();
            getCollectionId2(getFeedCollectionRole);
            path = FARMER_COLLECTION + "/" + auth.getUid() + "/" + getFeedCollectionRole + "/" + feedId;
            Log.d("TAG", "onCreate: " + path);
            operation = INCREMENT;
        });
        btnDelete2.setOnClickListener(v -> {
            feedDialogue.show();
            getCollectionId2(getFeedCollectionRole);
            path = FARMER_COLLECTION + "/" + auth.getUid() + "/" + getFeedCollectionRole + "/" + feedId;
            Log.d("TAG", "onCreate: " + path);
            operation = DECREMENT;
        });
        btnStock3.setOnClickListener(v -> {
            feedDialogue.show();
            getCollectionId3(getFeedCollectionRole);
            path = FARMER_COLLECTION + "/" + auth.getUid() + "/" + getFeedCollectionRole + "/" + feedId;
            Log.d("TAG", "onCreate: " + path);
            operation = INCREMENT;
        });
        btnDelete3.setOnClickListener(v -> {
            feedDialogue.show();
            getCollectionId3(getFeedCollectionRole);
            path = FARMER_COLLECTION + "/" + auth.getUid() + "/" + getFeedCollectionRole + "/" + feedId;
            Log.d("TAG", "onCreate: " + path);
            operation = DECREMENT;
        });
        btnDone.setOnClickListener(v -> {

            if (!Utils.validateWeight(Objects.requireNonNull(tvAmount.getText()).toString())) {
                tvAmountLayout.setError("Required");
                tvAmountLayout.requestFocus();
                showSnackBar("Enter Amount");
            }
            btnDone.setBackgroundColor(ContextCompat.getColor(this, R.color.greenChange));
            dialog.show();
            Log.d(TAG, "onCreate: " + tvAmount.getText());
            if (tvAmount.getText().toString().length() > 1) {
                if (operation.equals(INCREMENT)) {
                    int amount = Integer.parseInt(tvAmount.getText().toString());
                    feedStock.incrementValue(path, feedDialogue, amount, tvAmount, dialog, this);
                }
                if (operation.equals(DECREMENT)) {
                    int amount = Integer.parseInt(tvAmount.getText().toString());
                    feedStock.decrement(path, feedDialogue, amount, tvAmount, dialog, this);
                }
            } else {
                feedDialogue.dismiss();
                dialog.dismiss();
                showSnackBar("Minimum Length Required");
            }

            tvAmount.setText("");
        });
    }

    private void getCollectionId2(String getFeedCollectionRole) {
        switch (getFeedCollectionRole) {
            case Cattle_Feed:
                feedId = Corn_Feed;
                break;
            case Goat_Feed:
                feedId = Straw;
                break;
            case Sheep_Feed:
                feedId = Silage;
                break;
            default:
                showSnackBar("Default");
        }
    }

    private void getCollectionId3(String getFeedCollectionRole) {
        switch (getFeedCollectionRole) {
            case Cattle_Feed:
                feedId = Barley_Feed;
                break;
            case Goat_Feed:
                feedId = Chaffhaye;
                break;
            case Sheep_Feed:
                feedId = Hay;
                break;
            default:
                showSnackBar("Default");
        }
    }

    private void getCollectionId1(String getFeedCollectionRole) {
        switch (getFeedCollectionRole) {
            case Cattle_Feed:
                feedId = Grass_Feed;
                break;
            case Goat_Feed:
                feedId = Hay;
                break;
            case Sheep_Feed:
                feedId = Grain;
                break;
            default:
                showSnackBar("Default");
        }
    }

    private void initViews() {
        feedType1 = findViewById(R.id.feedType1);
        btnStock1 = findViewById(R.id.btnStock1);
        btnDone = feedDialogue.findViewById(R.id.btnDone);
        tvAmount = feedDialogue.findViewById(R.id.tvAmount);
        tvAmountLayout = feedDialogue.findViewById(R.id.tvAmountLayout);
        btnStock2 = findViewById(R.id.btnStock2);
        btnStock3 = findViewById(R.id.btnStock3);
        btnDelete1 = findViewById(R.id.btnDelete1);
        btnDelete2 = findViewById(R.id.btnDelete2);
        btnDelete3 = findViewById(R.id.btnDelete3);
        feedType2 = findViewById(R.id.feedType2);
        feedType3 = findViewById(R.id.feedType3);
        totalQuantity1 = findViewById(R.id.totalQuantity1);
        totalQuantity2 = findViewById(R.id.totalQuantity2);
        totalQuantity3 = findViewById(R.id.totalQuantity3);
        consumed1 = findViewById(R.id.consumed1);
        consumed2 = findViewById(R.id.consumed2);
        consumed3 = findViewById(R.id.consumed3);
        remaining1 = findViewById(R.id.remaining1);
        remaining2 = findViewById(R.id.remaining2);
        remaining3 = findViewById(R.id.remaining3);
    }

    private void handleFeedNames() {
        switch (getRole) {
            case COWBULL:
                getFeedCollectionRole = Cattle_Feed;
                feedArray = new String[]{"Grass Feed", "Corn Feed", "Barley Feed"};
                setupFeedType();
                feedStock.getFeedStock(FARMER_COLLECTION, Cattle_Feed, Grass_Feed, Corn_Feed, Barley_Feed, totalQuantity1, totalQuantity2, totalQuantity3, consumed1, consumed2, consumed3, remaining1, remaining2, remaining3, this, dialog);
                break;
            case GOAT:
                getFeedCollectionRole = Goat_Feed;
                feedArray = new String[]{"Hay", "Straw", "Chaffhaye"};
                setupFeedType();
                feedStock.getFeedStock(FARMER_COLLECTION, Goat_Feed, Hay, Straw, Chaffhaye, totalQuantity1, totalQuantity2, totalQuantity3, consumed1, consumed2, consumed3, remaining1, remaining2, remaining3, this, dialog);
                break;
            case SHEEP:
                getFeedCollectionRole = Sheep_Feed;
                feedArray = new String[]{"Grain", "Silage", "Hay"};
                setupFeedType();
                feedStock.getFeedStock(FARMER_COLLECTION, Sheep_Feed, Grain, Silage, Hay, totalQuantity1, totalQuantity2, totalQuantity3, consumed1, consumed2, consumed3, remaining1, remaining2, remaining3, this, dialog);
                break;
            default:
                showSnackBar("Default");

        }
    }

    private void setupFeedType() {
        feedType1.setText(feedArray[0]);
        feedType2.setText(feedArray[1]);
        feedType3.setText(feedArray[2]);
    }

    public void showSnackBar(String message) {
        Snackbar.make(feedType1, message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.redish1)).show();
    }
}