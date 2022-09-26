package com.app.cattlemanagement.activities.farmer;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.ANIMAL_DESC;
import static com.app.cattlemanagement.utils.Constant.BREED;
import static com.app.cattlemanagement.utils.Constant.CATTLE_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.CHILD_NO;
import static com.app.cattlemanagement.utils.Constant.COWBULL;
import static com.app.cattlemanagement.utils.Constant.DOB;
import static com.app.cattlemanagement.utils.Constant.DOB_FARM;
import static com.app.cattlemanagement.utils.Constant.FARMER_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.FATHER_TAG;
import static com.app.cattlemanagement.utils.Constant.FEMALE;
import static com.app.cattlemanagement.utils.Constant.FIRST_VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.FIRST_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.FOURTH_VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.FOURTH_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.FRAGMENT_TAG;
import static com.app.cattlemanagement.utils.Constant.GENDER;
import static com.app.cattlemanagement.utils.Constant.GOAT;
import static com.app.cattlemanagement.utils.Constant.GOAT_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.HEALTH_STATUS;
import static com.app.cattlemanagement.utils.Constant.IMAGE;
import static com.app.cattlemanagement.utils.Constant.IMAGE_URI;
import static com.app.cattlemanagement.utils.Constant.IS_FULLY_VACCINATED;
import static com.app.cattlemanagement.utils.Constant.IS_PREGNANT;
import static com.app.cattlemanagement.utils.Constant.MALE;
import static com.app.cattlemanagement.utils.Constant.MOTHER_TAG;
import static com.app.cattlemanagement.utils.Constant.NAME;
import static com.app.cattlemanagement.utils.Constant.NO;
import static com.app.cattlemanagement.utils.Constant.OBTAINED_SOURCE;
import static com.app.cattlemanagement.utils.Constant.PREGNANT_MONTH;
import static com.app.cattlemanagement.utils.Constant.REMAINING_VACCCINE;
import static com.app.cattlemanagement.utils.Constant.R_FIRST_VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.R_FIRST_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.R_SECOND_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.R_SECOND__VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.R_THIRD_VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.R_THIRD_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.SECOND_VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.SECOND_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.SHEEP;
import static com.app.cattlemanagement.utils.Constant.SHEEP_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.TAG;
import static com.app.cattlemanagement.utils.Constant.THIRD_VACCINE_DATE;
import static com.app.cattlemanagement.utils.Constant.THIRD_VACCINE_NAME;
import static com.app.cattlemanagement.utils.Constant.VACCINE_COMPLETED;
import static com.app.cattlemanagement.utils.Constant.VACCINE_DETAIL;
import static com.app.cattlemanagement.utils.Constant.WEIGHT;
import static com.app.cattlemanagement.utils.Constant.YES;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.utils.DialogUtils;
import com.app.cattlemanagement.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddAnimalInfo extends AppCompatActivity {
    MaterialDatePicker datePicker, getDatePicker, pregnancyDatePicker, completeVaccineDatePicker2, completeVaccineDatePicker1, completeVaccineDatePicker3, completeVaccineDatePicker4, remainingVaccineDatePicker1, remainingVaccineDatePicker2, remainingVaccineDatePicker3;
    AutoCompleteTextView gender, buy, breed, pregnant, tvVaccinate, health_status;
    TextInputEditText tvRemainingVaccineNumber1, tvRemainingVaccineNumberDate1, tvRemainingVaccineNumber2, tvRemainingVaccineNumberDate2, tvRemainingVaccineNumber3,
            tvRemainingVaccineNumberDate3, tvVaccineName1, tvVaccineName2, tvVaccineName3, tvRemainingVaccineNumber,
            tvVaccineName4, tvVaccineNameDate1, tvVaccineNameDate2, tvVaccineNameDate3, tvVaccineNameDate4, dateLayout, dateEntryOnFarm, tvName, tvVaccineCompelete, tvMonthPregnancy, tvTag, tvWeight, tvFatherTag, tvMotherTag, tvDescription, tvVaccine, tvChildNumberLayout;
    TextView tvImageUploadStatus;
    String stvName, stvTag, stvWeight, stvFatherTag = "", stvMonthPregnancy, stvMotherTag = "", stvDescription, stvVaccine, stvImageUploadStatus, stvChildNumberLayout;
    TextInputLayout healthLayout, tvRemainingVaccineNumber3DateLayout, tvRemainingVaccineNumber3Layout, tvRemainingVaccineNumber1Layout, tvRemainingVaccineNumber1DateLayout, tvRemainingVaccineNumber2Layout, tvRemainingVaccineNumber2DateLayout, genderType, buyType, tvVaccineName1Layout, tvRemainingVaccineNumberLayout, tvVaccineName2Layout, tvVaccineName3Layout, tvVaccineName4Layout,
            tvVaccineName1DateLayout, tvVaccineName2DateLayout, tvVaccineName3DateLayout, tvVaccineName4DateLayout, tvVaccineCompletedLayout, breedType, lName, ltag, ldob, ldFarm, lweight, ldesc, lvaacine, lmonthPregnancies, pregnantLayout, tvChildLayout, tvVaccineLayout;
    AppCompatButton btnAdd;
    CircleImageView btnUploadImage, ImSource;
    String dateOfBirth = "";
    String dateEntryFarm = "", vaccineDate1 = "", vaccineDate2 = "", vaccineDate3 = "", vaccineDate4 = "", remainingVaccineDate1 = "", remainingVaccineDate2 = "", remainingVaccineDate3 = "";
    Boolean isCheckedMonth = false;
    String properFormat = "", pregnancyDate = "";
    Boolean isPregnantChecker = false;
    Boolean isImageUploaded = false;
    String[] menuData = new String[6];
    String[] genderArray = {"Male", "Female"};
    String[] healthArray = {"Healthy", "Diseased"};
    String[] vaccinatedArray = {"Vaccinated-Yes", "Vaccinated-No"};
    String[] pregnantArray = {"Yes", "No"};
    Uri imageUri;
    String[] buyStatus = {"Born on farm", "Purchased", "other"};
    String[] cattleBreed = null;
    FragmentManager fragmentManager;
    ActivityResultLauncher<String> launcher;
    FirebaseStorage storage;
    FirebaseFirestore db;
    Dialog dgLoading;
    FirebaseAuth auth;
    HashMap<String, String> animalInfo;
    String fileName;
    String animalRefCollection = "";
    int completedVaccine;
    int remainingVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal_info);
        animalRefCollection = getIntent().getStringExtra(ANIMAL);
        getBreedByAnimalType(animalRefCollection);
        /**
         * Initialize storage and database
         */

        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        /**
         * init dialogue loading
         */
        dgLoading = new Dialog(this);
        DialogUtils.initLoadingDialog(dgLoading);
        /**
         * Initialize all the views
         */
        initViews();
        datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        getDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        pregnancyDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        completeVaccineDatePicker1 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        completeVaccineDatePicker2 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        completeVaccineDatePicker3 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        completeVaccineDatePicker4 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        remainingVaccineDatePicker1 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        remainingVaccineDatePicker2 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        remainingVaccineDatePicker3 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        remainingVaccineDatePicker3 = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build();
        /**
         * handle dropDownMenu Listener
         */
        handleDropdownMenu(gender, genderType, 0, genderArray);
        handleDropdownMenu(buy, buyType, 1, buyStatus);
        handleDropdownMenu(breed, breedType, 2, cattleBreed);
        handleDropdownMenu(pregnant, pregnantLayout, 3, pregnantArray);
        handleDropdownMenu(tvVaccinate, tvVaccineLayout, 4, vaccinatedArray);
        handleDropdownMenu(health_status, healthLayout, 5, healthArray);
        dateLayout.setOnClickListener(view -> showDatePickerDialogue(datePicker));
        dateEntryOnFarm.setOnClickListener(view -> showDatePickerDialogue(getDatePicker));
        tvVaccineNameDate1.setOnClickListener(view -> showDatePickerDialogue(completeVaccineDatePicker1));
        tvVaccineNameDate2.setOnClickListener(view -> showDatePickerDialogue(completeVaccineDatePicker2));
        tvVaccineNameDate3.setOnClickListener(view -> showDatePickerDialogue(completeVaccineDatePicker3));
        tvVaccineNameDate4.setOnClickListener(view -> showDatePickerDialogue(completeVaccineDatePicker4));
        tvRemainingVaccineNumberDate1.setOnClickListener(view -> showDatePickerDialogue(remainingVaccineDatePicker1));
        tvRemainingVaccineNumberDate2.setOnClickListener(view -> showDatePickerDialogue(remainingVaccineDatePicker2));
        tvRemainingVaccineNumberDate3.setOnClickListener(view -> showDatePickerDialogue(remainingVaccineDatePicker3));
        tvMonthPregnancy.setOnClickListener(view -> showDatePickerDialogue(pregnancyDatePicker));
        /**
         * register click listener for date picker
         */
        datePickerClickListener();

        /**
         * Vaccine Complete Text Change Listener
         */
        completeVaccineTextChangeListener();
        remainingVaccineTextChangeListener();
        /**
         * register for activity result launcher to upload image
         */
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    ImSource.setImageURI(result);
                    tvImageUploadStatus.setText("Competed");
                    tvImageUploadStatus.setTextColor(getResources().getColor(R.color.yellow));
                    isImageUploaded = true;
                    imageUri = result;
                    fileName = new File(result.getPath()).getName();
                }
            }
        });

        btnUploadImage.setOnClickListener(view -> {
            launcher.launch(IMAGE);
        });
        /**
         *
         * button add animal on click listener
         */
        btnAdd.setOnClickListener(view -> {
            removeIndicatedError();
            if (animalInfoValidation()) {
                dgLoading.show();
                switch (animalRefCollection) {
                    case COWBULL: {

                        addAnimalData(CATTLE_COLLECTION);
                        break;
                    }
                    case GOAT: {
                        addAnimalData(GOAT_COLLECTION);
                        break;
                    }
                    case SHEEP: {
                        addAnimalData(SHEEP_COLLECTION);
                        break;
                    }
                    default:
                        showSnackBar("Default");
                }
            }

        });
    }

    private void remainingVaccineTextChangeListener() {
        tvRemainingVaccineNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                showSnackBar("Must Be Integer < 4", "");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = Objects.requireNonNull(tvRemainingVaccineNumber.getText()).toString();
                if (Utils.validateIsNumber(value)) {
                    if (Integer.parseInt(value) < 4) {
                        remainingVaccine = Integer.parseInt(value);
                        switch (Integer.parseInt(value)) {
                            case 1:
                                visibleVaccineNameDate(tvRemainingVaccineNumber1Layout, tvRemainingVaccineNumber1DateLayout);
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber2Layout, tvRemainingVaccineNumber2DateLayout);
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber3Layout, tvRemainingVaccineNumber3DateLayout);
                                break;
                            case 2:
                                visibleVaccineNameDate(tvRemainingVaccineNumber1Layout, tvRemainingVaccineNumber1DateLayout);
                                visibleVaccineNameDate(tvRemainingVaccineNumber2Layout, tvRemainingVaccineNumber2DateLayout);
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber3Layout, tvRemainingVaccineNumber3DateLayout);
                                break;
                            case 3:
                                visibleVaccineNameDate(tvRemainingVaccineNumber1Layout, tvRemainingVaccineNumber1DateLayout);
                                visibleVaccineNameDate(tvRemainingVaccineNumber2Layout, tvRemainingVaccineNumber2DateLayout);
                                visibleVaccineNameDate(tvRemainingVaccineNumber3Layout, tvRemainingVaccineNumber3DateLayout);
                                break;
                            default:
                                showSnackBar("default");
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber1Layout, tvRemainingVaccineNumber1DateLayout);
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber3Layout, tvRemainingVaccineNumber3DateLayout);
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber2Layout, tvRemainingVaccineNumber2DateLayout);
                                visibleVaccineNameDateGone(tvRemainingVaccineNumber3Layout, tvRemainingVaccineNumber3DateLayout);
                                break;
                        }
                    } else {
                        showSnackBar("Must Be Integer < 4");
                    }
                }
            }
        });

    }

    private void completeVaccineTextChangeListener() {
        tvVaccineCompelete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                showSnackBar("Must Be Integer < 5", "");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = tvVaccineCompelete.getText().toString();
                if (Utils.validateIsNumber(value)) {
                    if (Integer.parseInt(value) < 5) {
                        completedVaccine = Integer.parseInt(value);
                        switch (Integer.parseInt(value)) {
                            case 1:
                                visibleVaccineNameDate(tvVaccineName1Layout, tvVaccineName1DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName2Layout, tvVaccineName2DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName3Layout, tvVaccineName3DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName4Layout, tvVaccineName4DateLayout);
                                break;
                            case 2:
                                visibleVaccineNameDate(tvVaccineName1Layout, tvVaccineName1DateLayout);
                                visibleVaccineNameDate(tvVaccineName2Layout, tvVaccineName2DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName3Layout, tvVaccineName3DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName4Layout, tvVaccineName4DateLayout);
                                break;
                            case 3:
                                visibleVaccineNameDate(tvVaccineName1Layout, tvVaccineName1DateLayout);
                                visibleVaccineNameDate(tvVaccineName2Layout, tvVaccineName2DateLayout);
                                visibleVaccineNameDate(tvVaccineName3Layout, tvVaccineName3DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName4Layout, tvVaccineName4DateLayout);
                                break;
                            case 4:
                                visibleVaccineNameDate(tvVaccineName1Layout, tvVaccineName1DateLayout);
                                visibleVaccineNameDate(tvVaccineName2Layout, tvVaccineName2DateLayout);
                                visibleVaccineNameDate(tvVaccineName3Layout, tvVaccineName3DateLayout);
                                visibleVaccineNameDate(tvVaccineName4Layout, tvVaccineName4DateLayout);
                                break;
                            default:
                                visibleVaccineNameDateGone(tvVaccineName1Layout, tvVaccineName1DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName2Layout, tvVaccineName2DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName3Layout, tvVaccineName3DateLayout);
                                visibleVaccineNameDateGone(tvVaccineName4Layout, tvVaccineName4DateLayout);
                        }
                    } else {
                        showSnackBar("Must Be Integer < 5");
                    }
                }
            }
        });

    }

    private void visibleVaccineNameDate(TextInputLayout firstLayout, TextInputLayout secondLayout) {
        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.VISIBLE);
    }

    private void visibleVaccineNameDateGone(TextInputLayout firstLayout, TextInputLayout secondLayout) {
        firstLayout.setVisibility(View.GONE);
        secondLayout.setVisibility(View.GONE);
    }

    private void getBreedByAnimalType(String animalRefCollection) {
        switch (animalRefCollection) {
            case COWBULL:
                cattleBreed = new String[]{"Chollistani", "Lohani", "Red Sindhi", "Tharparkar"};
                break;
            case GOAT:
                cattleBreed = new String[]{"Beetal", "Barbari", "Kamori", "Nachi"};
                break;
            case SHEEP:
                cattleBreed = new String[]{"Balkhi", "Baluchi", "Cholistani ", "Damani "};

        }
    }

    private void addAnimalData(String collection) {
        new Handler().post(() -> {
            final StorageReference reference = storage.getReference().child(fileName);
            reference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> reference.getDownloadUrl().addOnSuccessListener(uri -> {
                        animalInfo.put(IMAGE_URI, uri.toString());
                        showSnackBar(uri.toString());
                        db.collection(FARMER_COLLECTION).document(auth.getCurrentUser().getUid()).collection(collection).document().set(animalInfo);
                        dgLoading.dismiss();
                        Snackbar.make(findViewById(R.id.select_obtained), "Task Successful", Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.yellow)).show();
                    }
            ).addOnFailureListener(view -> {
                dgLoading.dismiss();
                showSnackBar("Image Upload To Server Failed");
            })).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dgLoading.dismiss();
                    showSnackBar(e.getMessage());
                }
            });
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
        completeVaccineDatePicker1.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = completeVaccineDatePicker1.getHeaderText().replace(" ", "-");
            tvVaccineNameDate1.setText(newProperFormat);
            vaccineDate1 = newProperFormat;
        });
        completeVaccineDatePicker2.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = completeVaccineDatePicker2.getHeaderText().replace(" ", "-");
            tvVaccineNameDate2.setText(newProperFormat);
            vaccineDate2 = newProperFormat;
        });
        completeVaccineDatePicker3.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = completeVaccineDatePicker3.getHeaderText().replace(" ", "-");
            tvVaccineNameDate3.setText(newProperFormat);
            vaccineDate3 = newProperFormat;
        });
        completeVaccineDatePicker4.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = completeVaccineDatePicker4.getHeaderText().replace(" ", "-");
            tvVaccineNameDate4.setText(newProperFormat);
            vaccineDate4 = newProperFormat;
        });
        remainingVaccineDatePicker1.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = remainingVaccineDatePicker1.getHeaderText().replace(" ", "-");
            tvRemainingVaccineNumberDate1.setText(newProperFormat);
            remainingVaccineDate1 = newProperFormat;
        });
        remainingVaccineDatePicker2.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = remainingVaccineDatePicker2.getHeaderText().replace(" ", "-");
            tvRemainingVaccineNumberDate2.setText(newProperFormat);
            remainingVaccineDate2 = newProperFormat;
        });
        remainingVaccineDatePicker3.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = remainingVaccineDatePicker3.getHeaderText().replace(" ", "-");
            tvRemainingVaccineNumberDate3.setText(newProperFormat);
            remainingVaccineDate3 = newProperFormat;
        });
        pregnancyDatePicker.addOnPositiveButtonClickListener(picker -> {
            String newProperFormat = pregnancyDatePicker.getHeaderText().replace(" ", "-");
            tvMonthPregnancy.setText(newProperFormat);
            pregnancyDate = newProperFormat;
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
        if (!Utils.validateIsNumber(stvChildNumberLayout)) {
            messageLayout(tvChildLayout, "Valid Number Is Required");
            showSnackBar("Child No Should Be Single Integer.");
            return false;
        }

        if (menuData[4] == null) {
            messageLayout(tvVaccineLayout, "Vaccination Status Required");
            showSnackBar("Vaccination Required");
            return false;
        }


        if (Objects.requireNonNull(tvVaccineCompelete.getText()).toString().isEmpty()) {
            messageLayout(tvVaccineCompletedLayout, "Vaccination Number Required");
            return false;
        }


        if (completedVaccine != 0) {
            Log.d(TAG, "animalInfoValidation: ");
            if (!vaccineCompletedValidation()) {
                return false;
            }
        }
        if (tvRemainingVaccineNumber.getText().toString().isEmpty()) {
            messageLayout(tvRemainingVaccineNumberLayout, "Remaining Vaccine Number Is Required");
            return false;
        }
        if (remainingVaccine != 0) {
            Log.d(TAG, "animalInfoValidation: Remaining");

            if (!remainingVaccineValidation()) {
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

        if (!Utils.validateDate(dateLayout.getText().toString())) {
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

        if (!Utils.validateIsNumber(tvVaccineCompelete.getText().toString())) {
            messageLayout(tvVaccineLayout, "Valid Number Is Required");
            return false;
        }
        if (!Utils.validateIsNumber(tvRemainingVaccineNumber.getText().toString())) {
            messageLayout(tvRemainingVaccineNumberLayout, "Valid Number Is Required");
            return false;
        }


        if (stvDescription.isEmpty()) {
            messageLayout(ldesc, "Write Some Notes");
            showSnackBar("Description Notes Required");
            return false;
        }
        if (stvVaccine.isEmpty()) {
            messageLayout(lvaacine, "Write Vaccination Info");
            showSnackBar("Write Vaccination Info");
            return false;
        }
        if (!isImageUploaded) {
            showSnackBar("Upload Animal Image");
            tvImageUploadStatus.requestFocus();
            return false;
        }
        if (menuData[5] == null) {
            healthLayout.requestFocus();
            messageLayout(healthLayout, "Health Status Required");
        }
        putHashMapValues();
        return true;
    }

    private boolean remainingVaccineValidation() {
        switch (remainingVaccine) {
            case 1:
                if (tvRemainingVaccineNumber1.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumberDate1.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                return true;
            case 2:
                if (tvRemainingVaccineNumber1.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumberDate1.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumber2.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber2Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumberDate2.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber2DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                return true;
            case 3:
                if (tvRemainingVaccineNumber1.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumberDate1.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumber2.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber2Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumberDate2.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber2DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumber3.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber3Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvRemainingVaccineNumberDate3.getText().toString().length() < 6) {
                    messageLayout(tvRemainingVaccineNumber3DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                return true;

            default:
                return true;
        }
    }

    private void putHashMapValues() {
        animalInfo = new HashMap<>();
        animalInfo.put(NAME, stvName);
        animalInfo.put(TAG, stvTag);
        animalInfo.put(GENDER, menuData[0]);
        animalInfo.put(HEALTH_STATUS, menuData[5]);
        animalInfo.put(PREGNANT_MONTH, Objects.requireNonNull(tvMonthPregnancy.getText()).toString().trim());
        animalInfo.put(IS_PREGNANT, menuData[3]);
        animalInfo.put(CHILD_NO, tvChildNumberLayout.getText().toString().trim());
        animalInfo.put(IS_FULLY_VACCINATED, menuData[4]);
        animalInfo.put(VACCINE_COMPLETED, tvVaccineCompelete.getText().toString().trim());
        animalInfo.put(FIRST_VACCINE_NAME, tvVaccineName1.getText().toString().trim());
        animalInfo.put(FIRST_VACCINE_DATE, tvVaccineNameDate1.getText().toString().trim());

        animalInfo.put(SECOND_VACCINE_NAME, Objects.requireNonNull(tvVaccineName2.getText()).toString().trim());
        animalInfo.put(SECOND_VACCINE_DATE, Objects.requireNonNull(tvVaccineNameDate2.getText()).toString().trim());

        animalInfo.put(THIRD_VACCINE_NAME, Objects.requireNonNull(tvVaccineName3.getText()).toString().trim());
        animalInfo.put(THIRD_VACCINE_DATE, Objects.requireNonNull(tvVaccineNameDate3.getText()).toString().trim());


        animalInfo.put(FOURTH_VACCINE_NAME, Objects.requireNonNull(tvVaccineName4.getText()).toString().trim());
        animalInfo.put(FOURTH_VACCINE_DATE, Objects.requireNonNull(tvVaccineNameDate4.getText()).toString().trim());


        animalInfo.put(REMAINING_VACCCINE, Objects.requireNonNull(tvRemainingVaccineNumber.getText()).toString().trim());

        animalInfo.put(R_FIRST_VACCINE_NAME, Objects.requireNonNull(tvRemainingVaccineNumber1.getText()).toString().trim());
        animalInfo.put(R_FIRST_VACCINE_DATE, Objects.requireNonNull(tvRemainingVaccineNumberDate1.getText()).toString().trim());

        animalInfo.put(R_SECOND_VACCINE_NAME, Objects.requireNonNull(tvRemainingVaccineNumber2.getText()).toString().trim());
        animalInfo.put(R_SECOND__VACCINE_DATE, Objects.requireNonNull(tvRemainingVaccineNumberDate2.getText()).toString().trim());

        animalInfo.put(R_THIRD_VACCINE_NAME, Objects.requireNonNull(tvRemainingVaccineNumber3.getText()).toString().trim());
        animalInfo.put(R_THIRD_VACCINE_DATE, Objects.requireNonNull(tvRemainingVaccineNumberDate3.getText()).toString().trim());


        animalInfo.put(WEIGHT, stvWeight);

        animalInfo.put(OBTAINED_SOURCE, menuData[1]);
        animalInfo.put(BREED, menuData[2]);
        animalInfo.put(DOB, dateOfBirth);
        animalInfo.put(DOB_FARM, dateEntryFarm);

        animalInfo.put(MOTHER_TAG, stvMotherTag);
        animalInfo.put(FATHER_TAG, stvFatherTag);
        animalInfo.put(ANIMAL_DESC, stvDescription);
        animalInfo.put(VACCINE_DETAIL, stvVaccine);

    }

    private boolean vaccineCompletedValidation() {
        switch (completedVaccine) {
            case 1:
                if (Objects.requireNonNull(tvVaccineName1.getText()).toString().trim().length() < 6) {
                    Log.d(TAG, "vaccineCompletedValidation: " + tvVaccineName1.getText().toString().trim().length())
                    ;
                    messageLayout(tvVaccineName1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (Objects.requireNonNull(tvVaccineNameDate1.getText()).toString().length() < 6) {
                    messageLayout(tvVaccineName1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                return true;
            case 2:
                if (Objects.requireNonNull(tvVaccineName1.getText()).toString().length() < 6) {
                    messageLayout(tvVaccineName1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (Objects.requireNonNull(tvVaccineNameDate1.getText()).toString().length() < 6) {
                    messageLayout(tvVaccineName1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (Objects.requireNonNull(tvVaccineName2.getText()).toString().length() < 6) {
                    messageLayout(tvVaccineName2Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate2.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName2DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                return true;
            case 3:
                if (tvVaccineName1.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate1.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvVaccineName2.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName2Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate2.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName2DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvVaccineName3.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName3Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate3.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName3DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                return true;
            case 4:
                if (tvVaccineName1.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName1Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate1.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName1DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvVaccineName2.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName2Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate2.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName2DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvVaccineName3.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName3Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate3.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName3DateLayout, "Vaccine Date Is Required");
                    return false;
                }
                if (tvVaccineName4.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName4Layout, "Minimum Length 6 Is Required");
                    return false;
                }
                if (tvVaccineNameDate4.getText().toString().length() < 6) {
                    messageLayout(tvVaccineName4DateLayout, "Vaccine Date Is Required");
                    return false;
                }
            default:
                return true;
        }
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
        stvChildNumberLayout = tvChildNumberLayout.getText().toString();
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
                            lmonthPregnancies.setVisibility(View.GONE);
                            isCheckedMonth = false;
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
        Snackbar.make(findViewById(R.id.select_obtained), message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.redish1)).show();
    }

    public void showSnackBar(String message, String Color) {
        Snackbar.make(findViewById(R.id.select_obtained), message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.redish1)).show();
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
        tvVaccineName1Layout.setError(null);
        tvVaccineName2Layout.setError(null);
        tvVaccineName3Layout.setError(null);
        tvVaccineName4Layout.setError(null);
        tvVaccineName1DateLayout.setError(null);
        tvVaccineName2DateLayout.setError(null);
        tvVaccineName3DateLayout.setError(null);
        tvVaccineName4DateLayout.setError(null);


        tvRemainingVaccineNumber1Layout.setError(null);
        tvRemainingVaccineNumber2Layout.setError(null);
        tvRemainingVaccineNumber3Layout.setError(null);


        tvRemainingVaccineNumber1DateLayout.setError(null);
        tvRemainingVaccineNumber2DateLayout.setError(null);
        tvRemainingVaccineNumber3DateLayout.setError(null);

        tvVaccineCompletedLayout.setError(null);
    }

    private void initViews() {
        gender = findViewById(R.id.select_gender);
        health_status = findViewById(R.id.health_status);
        healthLayout = findViewById(R.id.healthLayout);
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
        btnAdd = findViewById(R.id.btnAddAnimal);
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
        tvChildNumberLayout = findViewById(R.id.tvChildNumberLayout);
        tvChildLayout = findViewById(R.id.tvChildLayout);
        tvVaccinate = findViewById(R.id.tvVaccinate);
        tvVaccineLayout = findViewById(R.id.tvVaccineLayout);
        tvVaccineCompelete = findViewById(R.id.tvVaccineCompelete);
        tvVaccineCompletedLayout = findViewById(R.id.tvVaccineCompletedLayout);
        tvVaccineName1 = findViewById(R.id.tvVaccineName1);
        tvVaccineName2 = findViewById(R.id.tvVaccineName2);
        tvVaccineName3 = findViewById(R.id.tvVaccineName3);
        tvVaccineName4 = findViewById(R.id.tvVaccineName4);
        tvVaccineNameDate1 = findViewById(R.id.tvVaccineNameDate1);
        tvVaccineNameDate2 = findViewById(R.id.tvVaccineNameDate2);
        tvVaccineNameDate3 = findViewById(R.id.tvVaccineNameDate3);
        tvVaccineNameDate4 = findViewById(R.id.tvVaccineNameDate4);
        tvVaccineName1Layout = findViewById(R.id.tvVaccineName1Layout);
        tvVaccineName2Layout = findViewById(R.id.tvVaccineName2Layout);
        tvVaccineName3Layout = findViewById(R.id.tvVaccineName3Layout);
        tvVaccineName4Layout = findViewById(R.id.tvVaccineName4Layout);
        tvVaccineName1DateLayout = findViewById(R.id.tvVaccineName1DateLayout);
        tvVaccineName2DateLayout = findViewById(R.id.tvVaccineName2DateLayout);
        tvVaccineName3DateLayout = findViewById(R.id.tvVaccineName3DateLayout);
        tvVaccineName4DateLayout = findViewById(R.id.tvVaccineName4DateLayout);
        tvRemainingVaccineNumber = findViewById(R.id.tvRemainingVaccineNumber);
        tvRemainingVaccineNumberLayout = findViewById(R.id.tvRemainingVaccineNumberLayout);
        tvRemainingVaccineNumber1Layout = findViewById(R.id.tvRemainingVaccineNumber1Layout);
        tvRemainingVaccineNumber2Layout = findViewById(R.id.tvRemainingVaccineNumber2Layout);
        tvRemainingVaccineNumber3Layout = findViewById(R.id.tvRemainingVaccineNumber3Layout);
        tvRemainingVaccineNumber3DateLayout = findViewById(R.id.tvRemainingVaccineNumber3DateLayout);
        tvRemainingVaccineNumber2DateLayout = findViewById(R.id.tvRemainingVaccineNumber2DateLayout);
        tvRemainingVaccineNumber1DateLayout = findViewById(R.id.tvRemainingVaccineNumber1DateLayout);
        tvRemainingVaccineNumber1 = findViewById(R.id.tvRemainingVaccineNumber1);
        tvRemainingVaccineNumber2 = findViewById(R.id.tvRemainingVaccineNumber2);
        tvRemainingVaccineNumber3 = findViewById(R.id.tvRemainingVaccineNumber3);
        tvRemainingVaccineNumberDate1 = findViewById(R.id.tvRemainingVaccineNumberDate1);
        tvRemainingVaccineNumberDate2 = findViewById(R.id.tvRemainingVaccineNumberDate2);
        tvRemainingVaccineNumberDate3 = findViewById(R.id.tvRemainingVaccineNumberDate3);


    }

}