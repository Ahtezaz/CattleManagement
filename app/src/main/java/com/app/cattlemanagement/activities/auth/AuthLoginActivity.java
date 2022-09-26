package com.app.cattlemanagement.activities.auth;

import static com.app.cattlemanagement.utils.Constant.BUYER;
import static com.app.cattlemanagement.utils.Constant.FARM_OWNER;
import static com.app.cattlemanagement.utils.Constant.GET_ROLE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.data.remote.FirebaseLogin;
import com.app.cattlemanagement.utils.DialogUtils;
import com.app.cattlemanagement.utils.Utils;
import com.google.android.material.snackbar.Snackbar;

/**
 * Email = cattlefyp@gmail.com
 * Password = finalyearproject
 */

public class AuthLoginActivity extends AppCompatActivity {

    public static Activity context;
    EditText etEmail;
    EditText etPassword;
    String strEtEmail;
    String strEtPassword;
    boolean isPassVisible = false;
    private Dialog loadingDialog;
    String getRole = "";
    FirebaseLogin firebaseLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseLogin = new FirebaseLogin();
        context = this;
        initViews();
        getRole();
        initDialogue();

    }

    private void setErrorNull() {
        etEmail.setError(null);
        etPassword.setError(null);
    }

    private void getRole() {
        getRole = getIntent().getStringExtra(GET_ROLE);
    }

    private void initDialogue() {
        loadingDialog = new Dialog(this);
        DialogUtils.initLoadingDialog(loadingDialog);
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);
    }


    public void signUp(View view) {
        startActivity(new Intent(this, AuthRegistration.class));
    }

    public void showPassword(View view) {
        if (!isPassVisible) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPassVisible = true;
        } else {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPassVisible = false;
        }

    }


    private void castStrings() {
        strEtEmail = etEmail.getText().toString().trim();
        strEtPassword = etPassword.getText().toString();
    }

    private boolean isEverythingValid() {
        if (!Utils.validEt(etEmail, strEtEmail))
            return false;
        return Utils.validEt(etPassword, strEtPassword);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseLogin = null;
    }

    public void Login(View view) {
        castStrings();
        setErrorNull();
        if (!Utils.validateEmail(etEmail, strEtEmail)) {
            return;
        }
        if (!Utils.validatePassword(etPassword, strEtPassword)) {
            etPassword.setError("Password can't be empty.");
            return;
        }
        loadingDialog.show();
        switch (getRole) {
            case FARM_OWNER: {
                firebaseLogin.authenticateFarmer(context, loadingDialog, strEtEmail, strEtPassword);
                break;
            }
            case BUYER: {
                firebaseLogin.authenticateBuyer(context, loadingDialog, strEtEmail, strEtPassword);
                break;
            }
            default:
                showToast("Select Your Role First");
        }
    }

    private void showToast(String message) {
        Snackbar.make(this, findViewById(R.id.et_user_name), message, Snackbar.LENGTH_LONG).
                setTextColor(getResources().getColor(R.color.redish)).show();
    }
}