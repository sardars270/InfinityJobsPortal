package com.example.infinityjobportal.Adapters;

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
import com.example.infinityjobportal.add_exp;
import com.example.infinityjobportal.model.LOEModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class LOEAdapter extends RecyclerView.Adapter<LOEAdapter.ViewHolder> {

    private ArrayList<LOEModel> l = new ArrayList<>();
    private Context context;
   FirebaseFirestore db;

    public LOEAdapter(ArrayList<LOEModel> o, Context context,String af) {
        this.l = o;

        this.context = context;

    }



    @NonNull
    @Override
    public LOEAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_exp_outer, parent,false);
        LOEAdapter.ViewHolder holder=new LOEAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  final ViewHolder holder, int position) {
        final LOEModel o =l.get(position);
        FirebaseStorage firebaseStorage;
        StorageReference storageReference;

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

//        StorageReference imageRef = storageReference.child("Images/my.png");
        StorageReference imageRef = storageReference.child("Images/"+o.getImg());

        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

              //  Glide.with(context.getApplicationContext())
                //        .load(uri)
                  //      .into(holder.img);

                Glide.with(context).load(uri).into(holder.img);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(),"fail.",Toast.LENGTH_SHORT).show();
            }
        });







        holder.companyName.setText(o.getCompanyName());
        holder.subtitle.setText(o.getTitle());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(context, add_exp.class);

                context.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView companyName,subtitle,plus;

        LinearLayout layoutclick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            companyName=itemView.findViewById(R.id.company_name);
            plus=itemView.findViewById(R.id.plus);
            subtitle=itemView.findViewById(R.id.subtitle);
            layoutclick=itemView.findViewById(R.id.layoutclick);

        }

    }
}
