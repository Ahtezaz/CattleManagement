package com.app.cattlemanagement.activities.auth;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.remote.FirebaseRegistration;
import com.app.cattlemanagement.utils.DialogUtils;
import com.app.cattlemanagement.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class AuthRegistration extends AppCompatActivity {

    public static Activity context;

    public static String strEtPassword;
    boolean isPassVisible = false;
    AutoCompleteTextView feedRole;
    EditText etEmail;
    EditText etPhone;
    EditText etPassword;
    EditText etConfirmPassword;
    EditText etUserName;
    TextInputLayout selectedRole;

    String strUserName;

    String strEtEmail;
    String strEtPhone;
    String strEtConfirmPassword;
    String relationStatus = "";
    FirebaseRegistration firebaseRegistration;
    Dialog dgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = this;
        initViews();
        firebaseRegistration = new FirebaseRegistration();
        handleDropdownMenu();
        /**
         * init diaglogue loading
         */
        dgLoading = new Dialog(this);
        DialogUtils.initLoadingDialog(dgLoading);
    }

    private void handleDropdownMenu() {
        String[] getRole = {"Farmer", "Buyer"};
        ArrayAdapter roleAdapter = new ArrayAdapter(this, R.layout.marketing_list, getRole);
        feedRole.setAdapter(roleAdapter);
        ((AutoCompleteTextView) selectedRole.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                relationStatus = roleAdapter.getItem(position).toString();
            }
        });
    }

    public void showPassword(View view) {
        if (!isPassVisible) {
            etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPassVisible = true;
        } else {
            etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPassVisible = false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseRegistration = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void castStrings() {
        strUserName = etUserName.getText().toString();
        strEtEmail = etEmail.getText().toString();
        strEtPhone = etPhone.getText().toString();
        strEtPassword = etPassword.getText().toString();
        strEtConfirmPassword = etConfirmPassword.getText().toString();
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_user_name);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_pass);
        etConfirmPassword = findViewById(R.id.et_confirm_pass);
        etUserName = findViewById(R.id.et_first_name);
        feedRole = findViewById(R.id.select_relation);
        selectedRole = findViewById(R.id.textInputLayout);
    }

    /**
     * On Click Listener for <- Button
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * On Click Listener for signUp Button
     *
     * @param view
     */
    public void SignUp(View view) {

        castStrings();
        if (!Utils.validateUserName(etUserName, strUserName)) {
            return;
        }

        if (!Utils.validateEmail(etEmail, strEtEmail))
            return;

        if (!Utils.validatePhoneNumber(etPhone, strEtPhone))
            return;

        if (!Utils.validatePassword(etPassword, strEtPassword))
            return;

        if (!strEtPassword.equals(strEtConfirmPassword)) {
            showToast("Password do not match.");
            return;
        }
        if (relationStatus.isEmpty()) {
            showToast("Please Select Your Role");
            return;
        }
        dgLoading.show();
        firebaseRegistration.registerUser(strEtEmail, strUserName, strEtPassword, this, dgLoading);


    }


    public void showToast(String message) {
        Snackbar.make(this, findViewById(R.id.et_user_name), message, Snackbar.LENGTH_LONG).
                setTextColor(getResources().getColor(R.color.redish)).show();
    }

}
