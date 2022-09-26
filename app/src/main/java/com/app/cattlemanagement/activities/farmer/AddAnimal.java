package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.CATTLE_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.COWBULL;
import static com.app.cattlemanagement.utils.Constant.GOAT;
import static com.app.cattlemanagement.utils.Constant.GOAT_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.SHEEP;
import static com.app.cattlemanagement.utils.Constant.SHEEP_COLLECTION;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.adapters.AnimalRecyclerAdapter;
import com.app.cattlemanagement.data.entity.Animal;
import com.app.cattlemanagement.data.remote.FirebaseAnimalSource;
import com.app.cattlemanagement.utils.DialogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddAnimal extends AppCompatActivity {
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    AnimalRecyclerAdapter animalRecyclerAdapter;
    public List<Animal> animals = new ArrayList<>();
    private Dialog loadingDialog;
    String intentValue = "";
    TextView empty;
    FirebaseAnimalSource animalSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        initViews();
        animalSource = new FirebaseAnimalSource();
        intentValue = getIntent().getStringExtra(ANIMAL);
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);
        setupRecyclerView();
        actionButton.setOnClickListener(v -> startActivity(new Intent(this, AddAnimalInfo.class).putExtra(ANIMAL, intentValue)));
        loadingDialog.show();
        HandleRemoteSource();
    }

    private void HandleRemoteSource() {
        switch (intentValue) {
            case COWBULL: {
                animalSource.ListOfAnimal(loadingDialog, animals, animalRecyclerAdapter, this, CATTLE_COLLECTION, empty);
                break;
            }
            case GOAT: {
                animalSource.ListOfAnimal(loadingDialog, animals, animalRecyclerAdapter, this, GOAT_COLLECTION, empty);
                break;
            }
            case SHEEP: {
                animalSource.ListOfAnimal(loadingDialog, animals, animalRecyclerAdapter, this, SHEEP_COLLECTION, empty);
                break;
            }
            default:
                showSnackBar("Default");
        }
    }


    private void setupRecyclerView() {
        animalRecyclerAdapter = new AnimalRecyclerAdapter(this, animals, loadingDialog, intentValue);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animalRecyclerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animalSource = null;
    }

    private void initViews() {
        actionButton = findViewById(R.id.btnAddAnimal);
        recyclerView = findViewById(R.id.rvCardCow);
        empty = findViewById(R.id.tvempty);

    }

    public void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.rvCardCow), message, Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(this, R.color.redish1)).show();
    }

}