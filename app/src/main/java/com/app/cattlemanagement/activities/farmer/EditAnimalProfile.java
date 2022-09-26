package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.ANIMAL_DESC;
import static com.app.cattlemanagement.utils.Constant.BREED;
import static com.app.cattlemanagement.utils.Constant.DOB;
import static com.app.cattlemanagement.utils.Constant.DOB_FARM;
import static com.app.cattlemanagement.utils.Constant.FATHER_TAG;
import static com.app.cattlemanagement.utils.Constant.FEMALE;
import static com.app.cattlemanagement.utils.Constant.FRAGMENT_TAG;
import static com.app.cattlemanagement.utils.Constant.GENDER;
import static com.app.cattlemanagement.utils.Constant.IS_PREGNANT;
import static com.app.cattlemanagement.utils.Constant.MALE;
import static com.app.cattlemanagement.utils.Constant.MOTHER_TAG;
import static com.app.cattlemanagement.utils.Constant.NAME;
import static com.app.cattlemanagement.utils.Constant.NO;
import static com.app.cattlemanagement.utils.Constant.OBTAINED_SOURCE;
import static com.app.cattlemanagement.utils.Constant.PREGNANT_MONTH;
import static com.app.cattlemanagement.utils.Constant.TAG;
import static com.app.cattlemanagement.utils.Constant.VACCINE_DETAIL;
import static com.app.cattlemanagement.utils.Constant.WEIGHT;
import static com.app.cattlemanagement.utils.Constant.YES;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.entity.Animal;
import com.app.cattlemanagement.utils.DialogUtils;
import com.app.cattlemanagement.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditAnimalProfile extends AppCompatActivity {
    MaterialDatePicker datePicker, getDatePicker;
    AutoCompleteTextView gender, buy, breed, pregnant;
    TextInputEditText dateLayout, dateEntryOnFarm, tvName, tvMonthPregnancy, tvTag, tvWeight, tvFatherTag, tvMotherTag, tvDescription, tvVaccine;
    TextView tvImageUploadStatus;
    String stvName, stvTag, stvWeight, stvFatherTag = "", stvMonthPregnancy, stvMotherTag = "", stvDescription, stvVaccine, stvImageUploadStatus;
    TextInputLayout genderType, buyType, breedType, lName, ltag, ldob, ldFarm, lweight, ldesc, lvaacine, lmonthPregnancies, pregnantLayout;
    AppCompatButton btnUpdate;
    CircleImageView btnUploadImage, ImSource;
    String dateOfBirth = "";
    String dateEntryFarm = "";
    Boolean isCheckedMonth = false;
    String properFormat = "";
    Boolean isPregnantChecker = false;
    String[] menuData = new String[4];
    String[] genderArray = {"Male", "Female"};
    String[] pregnantArray = {"Yes", "No"};
    String[] buyStatus = {"Born on farm", "Purchased", "other"};
    String[] cattleBreed = {"Chollistani", "Lohani", "Red Sindhi", "Tharparkar"};
    FragmentManager fragmentManager;
    FirebaseStorage storage;
    FirebaseFirestore db;
    Dialog dgLoading;
    FirebaseAuth auth;
    HashMap<String, String> animalInfo;
    Animal animal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_animal_profile);
        animal = getIntent().getParcelableExtra(ANIMAL);

        showSnackBar(animal.getRefDoc(), "color");

        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        dgLoading = new Dialog(this);
        DialogUtils.initLoadingDialog(dgLoading);
        initViews();
        datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        getDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        handleDropdownMenu(gender, genderType, 0, genderArray);
        handleDropdownMenu(buy, buyType, 1, buyStatus);
        handleDropdownMenu(breed, breedType, 2, cattleBreed);
        handleDropdownMenu(pregnant, pregnantLayout, 3, pregnantArray);
        dateLayout.setOnClickListener(view -> showDatePickerDialogue(datePicker));
        dateEntryOnFarm.setOnClickListener(view -> showDatePickerDialogue(getDatePicker));
        datePickerClickListener();

        setDefaultValues();
        btnUpdate.setOnClickListener(view -> {
            removeIndicatedError();
            if (animalInfoValidation()) {
                dgLoading.show();
                addAnimalData();
            }

        });
    }

    private void setDefaultValues() {
        try {
            Glide.with(this).load(animal.getImageUri()).into(ImSource);
        } catch (Exception exception) {
            showSnackBar("Error Loading Image");
        }
        tvTag.setText(animal.getTagNo());
        tvName.setText(animal.getName());
        dateLayout.setText(animal.getDateOfBirth());
        dateEntryOnFarm.setText(animal.getDateOfFarm());
        tvFatherTag.setText(animal.getFatherTagNo());
        tvMotherTag.setText(animal.getMotherTagNo());
        tvDescription.setText(animal.getDesc());
        tvVaccine.setText(animal.getVaccineDesc());
    }

    private void addAnimalData() {
        new Handler().post(() -> {
            try {
                db.document(Objects.requireNonNull(animal.getRefDoc())).set(animalInfo, SetOptions.merge());
                dgLoading.dismiss();
                showSnackBar("Profile Updating Successful", "green");
            } catch (Exception exception) {
                dgLoading.dismiss();
                showSnackBar(exception.getMessage());
            }
        });

    }

    private void datePickerClickListener() {
        datePicker.addOnPositiveButtonClickListener(picker -> {
            properFormat = datePicker.getHeaderText().replace(" ", "-");
            dateLayout.setText(properFormat);
            dateOfBirth = properFormat;
        });
        getDatePicker.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = getDatePicker.getHeaderText().replace(" ", "-");
            dateEntryOnFarm.setText(newProperFormat);
            dateEntryFarm = newProperFormat;
        });
    }


    private boolean animalInfoValidation() {
        castStrings();
        if (!Utils.validateName(stvName)) {
            messageLayout(lName, "Name Is Required");
            showSnackBar("Name Is Required");
            return false;
        }
        if (stvTag.length() < 3) {
            showSnackBar("Tag Is Required");
            messageLayout(ltag, "Minimum length 3");
            return false;
        }
        if (menuData[0] == null) {
            messageLayout(genderType, "Gender Is Required");
            showSnackBar("Gender Is Required");
            return false;
        }
        if (isPregnantChecker) {
            if (menuData[3] == null) {
                messageLayout(pregnantLayout, "Is Pregnant Required");
                showSnackBar("Give Pregnant Info");
                return false;
            }
        }
        if (isCheckedMonth) {
            if (stvMonthPregnancy.isEmpty()) {
                messageLayout(lmonthPregnancies, "Pregnancy Month Required");
                showSnackBar("Pregnancy Month Required");
                return false;
            }
        }
        if (menuData[1] == null) {
            messageLayout(buyType, "Obtained Source Is Required");
            showSnackBar("Obtained Source Is Required");
            return false;
        }
        if (menuData[2] == null) {
            messageLayout(breedType, "Breed Is Required");
            showSnackBar("Breed Is Required");
            return false;
        }

        if (!Utils.validateDate(Objects.requireNonNull(dateLayout.getText()).toString())) {
            messageLayout(ldob, "DD-MMM-YYYY Format Required");
            showSnackBar("DOB Is Required In Proper Format");
            return false;
        }

        if (!Utils.validateDate(dateEntryOnFarm.getText().toString())) {
            messageLayout(ldFarm, "DD-MMM-YYYY Format Required");
            showSnackBar("Farm Entry Date Is Required");
            return false;
        }
        if (!Utils.validateWeight(stvWeight)) {
            messageLayout(lweight, "Number Is Required");
            showSnackBar("Weight Is Required In Proper Format");
            return false;
        }
        if (stvDescription.isEmpty()) {
            messageLayout(ldesc, "Write Some Notes");
            showSnackBar("Description Notes Required");
            return false;
        }
        if (stvVaccine.isEmpty()) {
            messageLayout(ldesc, "Write Vaccination Info");
            showSnackBar("Write Vaccination Info");
            return false;
        }
        putHashMapValues();
        return true;
    }

    private void putHashMapValues() {
        dateOfBirth = Objects.requireNonNull(dateLayout.getText()).toString();
        dateEntryFarm = Objects.requireNonNull(dateEntryOnFarm.getText()).toString();
        animalInfo = new HashMap<>();
        animalInfo.put(NAME, stvName);
        animalInfo.put(TAG, stvTag);
        animalInfo.put(GENDER, menuData[0]);
        animalInfo.put(WEIGHT, stvWeight);
        if (isPregnantChecker) {
            animalInfo.put(IS_PREGNANT, YES);
            animalInfo.put(PREGNANT_MONTH, tvMonthPregnancy.getText().toString());
        }
        animalInfo.put(IS_PREGNANT, NO);
        animalInfo.put(OBTAINED_SOURCE, menuData[1]);
        animalInfo.put(BREED, menuData[2]);
        animalInfo.put(DOB, dateOfBirth);
        animalInfo.put(DOB_FARM, dateEntryFarm);

        animalInfo.put(MOTHER_TAG, stvMotherTag);
        animalInfo.put(FATHER_TAG, stvFatherTag);
        animalInfo.put(ANIMAL_DESC, stvDescription);
        animalInfo.put(VACCINE_DETAIL, stvVaccine);

    }

    private void messageLayout(TextInputLayout layoutName, String message) {
        layoutName.requestFocus();
        layoutName.setError(message);

    }

    private void castStrings() {
        stvName = Objects.requireNonNull(tvName.getText()).toString();
        stvMonthPregnancy = Objects.requireNonNull(tvMonthPregnancy.getText()).toString();
        stvTag = Objects.requireNonNull(tvTag.getText()).toString();
        stvWeight = Objects.requireNonNull(tvWeight.getText()).toString();
        stvFatherTag = Objects.requireNonNull(tvFatherTag.getText()).toString();
        stvMotherTag = Objects.requireNonNull(tvMotherTag.getText()).toString();
        stvDescription = Objects.requireNonNull(tvDescription.getText()).toString();
        stvVaccine = Objects.requireNonNull(tvVaccine.getText()).toString();
        stvImageUploadStatus = tvImageUploadStatus.getText().toString();
    }

    private void showDatePickerDialogue(MaterialDatePicker dateMaterialPicker) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
            try {
                dateMaterialPicker.show(fragmentManager, FRAGMENT_TAG);

            } catch (Exception exception) {
                showSnackBar(exception.getMessage());
            }
        } else {
            fragmentManager = null;
        }

    }


    private void handleDropdownMenu(AutoCompleteTextView menuItem, TextInputLayout menuHolder, int index, String[] data) {
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, R.layout.marketing_list, data);
        menuItem.setAdapter(roleAdapter);
        ((AutoCompleteTextView) Objects.requireNonNull(menuHolder.getEditText()))
                .setOnItemClickListener((parent, view, position, id) -> {
                    menuData[index] = roleAdapter.getItem(position);
                    switch (menuData[index]) {
                        case FEMALE: {
                            pregnantLayout.setVisibility(View.VISIBLE);
                            isPregnantChecker = true;
                            break;
                        }
                        case MALE: {
                            pregnantLayout.setVisibility(View.GONE);
                            isPregnantChecker = false;
                            break;
                        }
                        case YES: {
                            lmonthPregnancies.setVisibility(View.VISIBLE);
                            isCheckedMonth = true;
                            break;
                        }
                        case NO: {
                            lmonthPregnancies.setVisibility(View.GONE);
                            isCheckedMonth = false;
                            break;
                        }
                        default:
                            System.out.println("Default");
                    }
                });
    }

    public void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.select_obtained), message, Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(this, R.color.redish1)).show();
    }

    public void showSnackBar(String message, String Color) {
        Snackbar.make(findViewById(R.id.select_obtained), message, Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(this, R.color.yellow)).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        genderArray = null;
        buyStatus = null;
        menuData = null;
    }

    private void removeIndicatedError() {
        lName.setError(null);
        ltag.setError(null);
        genderType.setError(null);
        breedType.setError(null);
        buyType.setError(null);
        ldob.setError(null);
        ldFarm.setError(null);
        lweight.setError(null);
        ldesc.setError(null);
        lvaacine.setError(null);
        if (isCheckedMonth) {
            pregnantLayout.setError(null);
        }
        if (isPregnantChecker) {
            lmonthPregnancies.setError(null);
        }
    }

    private void initViews() {
        gender = findViewById(R.id.select_gender);
        pregnant = findViewById(R.id.select_pregnant);
        pregnantLayout = findViewById(R.id.pregnant_layout);
        lmonthPregnancies = findViewById(R.id.monthPregnancylayout);
        tvName = findViewById(R.id.tvName);
        tvTag = findViewById(R.id.tvTag);
        tvMonthPregnancy = findViewById(R.id.tvMonthPregnancy);
        tvWeight = findViewById(R.id.tvWeight);
        tvFatherTag = findViewById(R.id.fatherTagNo);
        tvMotherTag = findViewById(R.id.tvMotherTagNo);
        tvDescription = findViewById(R.id.tvDescription);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        ImSource = findViewById(R.id.ImAnimalSource);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvDescription = findViewById(R.id.tvDescription);
        tvImageUploadStatus = findViewById(R.id.tvImageStatus);
        tvVaccine = findViewById(R.id.tvVaccineNotes);
        genderType = findViewById(R.id.menu);
        buy = findViewById(R.id.select_obtained);
        buyType = findViewById(R.id.menuBuy);
        lName = findViewById(R.id.lName);
        ltag = findViewById(R.id.lTag);
        lweight = findViewById(R.id.lweight);
        ldob = findViewById(R.id.ldob);
        ldesc = findViewById(R.id.desc_layout);
        ldFarm = findViewById(R.id.lDFarm);
        lvaacine = findViewById(R.id.ldesc);
        breedType = findViewById(R.id.menuBreed);
        breed = findViewById(R.id.select_breed);
        dateLayout = findViewById(R.id.datePicker);
        dateEntryOnFarm = findViewById(R.id.dateOfEntryOnFarm);

    }
}