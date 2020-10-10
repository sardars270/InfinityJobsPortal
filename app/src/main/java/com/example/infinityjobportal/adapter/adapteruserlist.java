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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class adapteruserlist extends RecyclerView.Adapter<adapteruserlist.ViewHolder>{
    Context context;
    List<User> ar1;
    public adapteruserlist(Context context, List<User> ar1) {

        this.context=context;
        this.ar1=ar1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsuserlist, parent, false);

        ViewHolder holder=new ViewHolder(listItem);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        User pj=ar1.get(position);




    }

    @Override
    public int getItemCount() {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout lout;
        TextView uid;
        TextView nm,eml,loc;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nm=itemView.findViewById(R.id.name);
            eml=itemView.findViewById(R.id.email);
            loc=itemView.findViewById(R.id.location);
            uid=itemView.findViewById(R.id.usid);
            lout=itemView.findViewById(R.id.ulist);
            imageView  = itemView.findViewById(R.id.imageView1);
        }
    }
}

