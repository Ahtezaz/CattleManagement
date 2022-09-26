package com.app.cattlemanagement.data.remote;

import static com.app.cattlemanagement.utils.Constant.EMAIl;
import static com.app.cattlemanagement.utils.Constant.PASSWORD;
import static com.app.cattlemanagement.utils.Constant.USERNAME;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.app.cattlemanagement.activities.farmer.FarmerDashboard;
import com.app.cattlemanagement.utils.Constant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FirebaseRegistration {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context contextApplication;
    HashMap<String, String> farmer = null;

    public void registerUser(String email, String userName, String password, Context context, Dialog dgLoading) {
        contextApplication = context;
        auth.createUserWithEmailAndPassword(email.trim(), password.trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                farmer = new HashMap<>();
                farmer.put(USERNAME, userName);
                farmer.put(EMAIl, email);
                farmer.put(PASSWORD, password);
                db.collection(Constant.FARMER_COLLECTION).document(auth.getCurrentUser().getUid()).set(
                        farmer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showToast("Registration Success");
                        dgLoading.dismiss();
                        farmer = null;
                        context.startActivity(new Intent(context, FarmerDashboard.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dgLoading.dismiss();
                        showToast("Registration Data Failed");
                        farmer = null;
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dgLoading.dismiss();
                Log.d("TAG", "onFailure:" + e.getMessage());
                showToast("Sign Up Failed");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(contextApplication, message, Toast.LENGTH_LONG).show();
    }
}
