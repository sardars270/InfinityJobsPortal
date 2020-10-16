package com.example.infinityjobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.Jobs;
import com.example.infinityjobportal.model.User;

import java.util.List;

public class Adapterjoblist extends RecyclerView.Adapter<Adapterjoblist.ViewHolder>{
    Context context;
    List<Jobs> ar1;
    public Adapterjoblist(Context context, List<Jobs> ar1) {

        this.context=context;
        this.ar1=ar1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_job, parent, false);

        ViewHolder holder=new ViewHolder(listItem);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Jobs pj=ar1.get(position);

        holder.title.setText(pj.getTitle());
        holder.at.setText(pj.getAt());
        holder.location.setText(pj.getLocation());



    }

    @Override
    public int getItemCount() {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView lout;

        TextView title, at, location;
        ImageView saveJob;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            at=itemView.findViewById(R.id.at);
            location=itemView.findViewById(R.id.location);

            lout=itemView.findViewById(R.id.ulist);
            saveJob  = itemView.findViewById(R.id.saveJob);
        }
    }
}

