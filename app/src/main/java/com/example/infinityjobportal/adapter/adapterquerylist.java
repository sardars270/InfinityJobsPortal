package com.example.infinityjobportal.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.Query;
import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class adapterquerylist extends RecyclerView.Adapter<adapterquerylist.ViewHolder>{
    public Context context;
    private ArrayList<Query> list = new ArrayList<>();

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public adapterquerylist(ArrayList<Query> o,  Context context, String af) {
        this.list=o;

        this.context=context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsquerylist, parent,false);
        adapterquerylist.ViewHolder holder=new adapterquerylist.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final adapterquerylist.ViewHolder holder, int position) {

        final Query o =list.get(position);


        FirebaseStorage firebaseStorage;
        StorageReference storageReference;
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference= firebaseStorage.getReference();

        //Toast.makeText(context,o.getUserProfilePic(),Toast.LENGTH_SHORT).show();
      /** StorageReference imageRef=storageReference.child("user/"+o.getUserProfilePic());imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.imageView1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //
            }
        });*/










        ////////////////////////''''''''''''''''''''''///////////////////
      //  holder.nm.setText(o.getFirstName().toString() +" "+o.getLastName().toString());
        holder.message.setText(o.getEditSubject().toString());
        holder.username.setText(o.getFirstName());
        //holder.loc.setText(o.getCity().toString());
        //holder.imageView.

//ehna pehla v aa rea c .but jido user waale table da data add krde odo e problem ho rhi
        //wait
        // oky np
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id;
        TextView username;
        TextView message;
        ImageView imageView1;
        LinearLayout lout;
        TextView visit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.qid);
            username= itemView.findViewById(R.id.uName);
            message=itemView.findViewById(R.id.queryMessage);
            lout=itemView.findViewById(R.id.querylist);
            imageView1 = itemView.findViewById(R.id.imageView);
            visit= itemView.findViewById(R.id.visit);
        }
    }
}

