package com.app.cattlemanagement.data.remote;

import static com.app.cattlemanagement.utils.Constant.ANIMAL;
import static com.app.cattlemanagement.utils.Constant.FARMER_COLLECTION;
import static com.app.cattlemanagement.utils.Constant.REF_DOC;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.app.cattlemanagement.activities.farmer.EditAnimalProfile;
import com.app.cattlemanagement.adapters.AnimalRecyclerAdapter;
import com.app.cattlemanagement.data.entity.Animal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class FirebaseAnimalSource {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Context context;

    public void ListOfAnimal(Dialog loadingDialog, List<Animal> animalList, AnimalRecyclerAdapter animalRecyclerAdapter, Context appContext, String collectionName, TextView empty) {
        context = appContext;
        new Handler().post(() -> {
            try {
                db.collection(FARMER_COLLECTION).document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection(collectionName).addSnapshotListener((value, error) -> {
                    animalList.clear();
                    if (error != null) {
                        showToast(error.getMessage());
                        return;
                    }
                    assert value != null;
                    if (!value.isEmpty()) {
                        empty.setVisibility(View.GONE);

                        for (QueryDocumentSnapshot snapShot :
                                value) {
                            Animal appendAnimal = snapShot.toObject(Animal.class);
                            appendAnimal.setRefDoc(db.collection(FARMER_COLLECTION).
                                    document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).
                                    collection(collectionName).getPath() + "/" + snapShot.getId());
                            ;
                            animalList.add(appendAnimal);

                        }
                        animalRecyclerAdapter.notifyDataSetChanged();
                        loadingDialog.dismiss();

                    } else {
                        empty.setVisibility(View.VISIBLE);
                        loadingDialog.dismiss();
                        showToast("List Empty");
                    }
                });


            } catch (Exception exception) {
                loadingDialog.dismiss();
                showToast(exception.getMessage());
            }
        });

    }

    public void deleteAnimal(String getDocRef, Dialog dialog) {
        new Handler().post(() -> {
            db.document(getDocRef).delete();
            dialog.dismiss();
        });

    }

    void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public void editProfile(Context context, String refDoc, Animal animal) {
        context.startActivity(new Intent(context, EditAnimalProfile.class).putExtra(
                REF_DOC, refDoc
        ).putExtra(ANIMAL, animal));
    }
}
