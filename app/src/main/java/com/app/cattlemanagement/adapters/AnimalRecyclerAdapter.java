package com.app.cattlemanagement.adapters;

import static com.app.cattlemanagement.utils.Constant.COWBULL;
import static com.app.cattlemanagement.utils.Constant.GOAT;
import static com.app.cattlemanagement.utils.Constant.SHEEP;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.cattlemanagement.R;
import com.app.cattlemanagement.activities.farmer.AnimalDetailInfoActivity;
import com.app.cattlemanagement.data.entity.Animal;
import com.app.cattlemanagement.data.remote.FirebaseAnimalSource;
import com.app.cattlemanagement.utils.Constant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class AnimalRecyclerAdapter extends RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalViewHolder> {
    Context context;
    ArrayList<Animal> animalArrayList;
    Dialog dialog;
    String animalType;
    int errorView;
    FirebaseAnimalSource animalSource;

    public AnimalRecyclerAdapter(Context context, List<Animal> animalArrayList, Dialog dialog, String animalType) {
        this.context = context;
        this.animalArrayList = (ArrayList<Animal>) animalArrayList;
        this.dialog = dialog;
        animalSource = new FirebaseAnimalSource();
        this.animalType = animalType;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_animal_loader, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        switch (animalType) {
            case COWBULL: {
                errorView = R.drawable.cow;
                break;
            }
            case GOAT: {
                errorView = R.drawable.goat;
                break;
            }
            case SHEEP: {
                errorView = R.drawable.sheepss;
                break;
            }
            default:
                errorView = R.drawable.goat;


        }
        Animal animal = animalArrayList.get(position);
        holder.tvTitle.setText(animal.getName());
        holder.tvTag.setText(animal.getBreed());
        holder.tvDesc.setText(animal.getDesc());
        try {
            Glide.with(context).load(animal.getImageUri()).diskCacheStrategy(DiskCacheStrategy.DATA).error(errorView).into(holder.imageSrc);

        } catch (Exception exception) {
            Toast.makeText(context, "Error Loading Image", Toast.LENGTH_LONG).show();
        }
        holder.itemView.setOnClickListener(v -> {
            holder.materialCardView.setBackgroundColor(ContextCompat.getColor(context , R.color.cardColor));
            context.startActivity(new Intent(context, AnimalDetailInfoActivity.class).putExtra(Constant.ANIMAL, animal));
        });
        holder.btnDelete.setOnClickListener(view -> {
            dialog.show();
            animalSource.deleteAnimal(animal.getRefDoc(), dialog);
        });

        holder.btnEdit.setOnClickListener(view -> animalSource.editProfile(context, animal.getRefDoc(), animal));

    }


    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    public   class AnimalViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageSrc;
        public TextView tvTitle, tvTag, tvDesc;
        public Button btnEdit, btnDelete;
        public MaterialCardView materialCardView;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSrc = itemView.findViewById(R.id.ImSrc);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            materialCardView = itemView.findViewById(R.id.card);

        }
    }
}
