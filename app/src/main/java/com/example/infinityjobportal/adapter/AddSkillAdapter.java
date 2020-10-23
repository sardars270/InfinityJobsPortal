package com.example.infinityjobportal.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.EditEducation;
import com.example.infinityjobportal.MainEducation;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.MySkill;
import com.example.infinityjobportal.model.Skill;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSkillAdapter extends RecyclerView.Adapter<AddSkillAdapter.ViewHolder> {

    private MySkill skill;
    private Context context;
    private List<MySkill> skillList;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSkill;
        ImageView ivEdit;


        ViewHolder(View view) {
            super(view);

            tvSkill = view.findViewById(R.id.tvSkill);

            ivEdit = view.findViewById(R.id.ivEdit);
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    skill = skillList.get(getAdapterPosition());

                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.dialog_edit_skill, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    alertDialogBuilder.setView(promptsView);

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.etSkill);
                    userInput.setText(skill.getName());
                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Update",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            db.collection("myskills").document(skill.getId()).update("name",userInput.getText().toString())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                                                        @Override
                                                        public void onSuccess(Void oid) {
                                                            Toast.makeText(context, "Data Updated Successfully", Toast.LENGTH_SHORT).show();



                                                        }
                                                    });

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            });


        }


    }

    public AddSkillAdapter(Context mContext, List<MySkill> skillList) {
        this.context = mContext;
        this.skillList = skillList;

    }

    @NonNull
    @Override
    public AddSkillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_skill, parent, false);


        return new AddSkillAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final AddSkillAdapter.ViewHolder holder, final int position) {

        skill = skillList.get(position);
        holder.tvSkill.setText(skill.getName());
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }


}