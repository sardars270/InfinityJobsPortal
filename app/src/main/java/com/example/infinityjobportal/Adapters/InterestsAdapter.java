package com.example.infinityjobportal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.InterestsModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.ViewHolder> {

    private ArrayList<InterestsModel> l = new ArrayList<>();
    private Context context;
    FirebaseFirestore db;
    public InterestsAdapter(ArrayList<InterestsModel> o, Context context,String af) {
        this.l = o;

        this.context = context;

    }

    @NonNull
    @Override
    public InterestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_interests, parent,false);
        InterestsAdapter.ViewHolder holder=new InterestsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InterestsAdapter.ViewHolder holder, int position) {
        final InterestsModel o =l.get(position);
        FirebaseStorage firebaseStorage;
        holder.tv.setText(o.getInterests());
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        LinearLayout layoutclick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);


        }

    }
}