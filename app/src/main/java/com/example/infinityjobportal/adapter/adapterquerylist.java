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
import com.example.infinityjobportal.model.Query;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class adapterquerylist extends RecyclerView.Adapter<adapterquerylist.ViewHolder>{

    List<Query> ar1;
    Context context;
    public adapterquerylist(Context context, List<Query> ar1) {
        this.context=context;
        this.ar1 = ar1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsquerylist, parent, false);
        ViewHolder holder=new ViewHolder(listItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Query pj=ar1.get(position);



    }

    @Override
    public int getItemCount() {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id;
        TextView username;
        TextView message;
        ImageView imageView;
        LinearLayout lout;
        TextView visit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.qid);
            username= itemView.findViewById(R.id.uName);
            message=itemView.findViewById(R.id.queryMessage);
            lout=itemView.findViewById(R.id.querylist);
            imageView = itemView.findViewById(R.id.imageView);
            visit= itemView.findViewById(R.id.visit);
        }
    }
}

