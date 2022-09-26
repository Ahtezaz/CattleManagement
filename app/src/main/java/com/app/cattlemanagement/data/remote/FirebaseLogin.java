package com.app.cattlemanagement.data.remote;

import static com.app.cattlemanagement.utils.Constant.FARMER_COLLECTION;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.app.cattlemanagement.activities.farmer.FarmerDashboard;
import com.app.cattlemanagement.utils.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseLogin {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context contextApplication;
    Dialog loadingDialog;

    public void authenticateFarmer(Activity context, Dialog loading, String strEtEmail, String strEtPassword) {
        contextApplication = context;
        loadingDialog = loading;
        authenticateCredentials(FARMER_COLLECTION, strEtEmail, strEtPassword.trim());
    }

    public void authenticateBuyer(Activity context, Dialog loadingDialog, String strEtEmail, String strEtPassword) {
        contextApplication = context;
    }

    private void authenticateCredentials(String collection, String email, String password) {
        db.collection(collection).whereEqualTo(Constant.EMAIl, email.trim()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                showToast("No Record Found");
                loadingDialog.dismiss();
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> contextApplication.
                                startActivity(new Intent(contextApplication, FarmerDashboard.class)))
                        .addOnFailureListener(e -> {
                            loadingDialog.dismiss();
                            showToast(e.getMessage());
                        });
            }
        }).addOnFailureListener(e -> {
            loadingDialog.dismiss();
            showToast(e.getMessage());
        });


    }


    private void showToast(String message) {
        Toast.makeText(contextApplication, message, Toast.LENGTH_LONG).show();
    }
}
