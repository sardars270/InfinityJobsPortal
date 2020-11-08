package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.example.infinityjobportal.model.User;

import java.util.ArrayList;

public class ViewApplicationAdapter extends RecyclerView.Adapter<ViewApplicationAdapter.ViewApplicationViewHolder>{
    private static final String TAG = "ViewApplicationAdapter";
    Context context;
    private ArrayList<User> userArrayList;

    public ViewApplicationAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public ViewApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_row_layout, parent, false);
        ViewApplicationAdapter.ViewApplicationViewHolder viewHolder = new ViewApplicationAdapter.ViewApplicationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewApplicationViewHolder holder, int position) {
        User postJobPOJO = userArrayList.get(position);

        holder.applicantName.setText(postJobPOJO.getFirstName()+" " +postJobPOJO.getLastName());
        //holder.applicantAddress.setText("postJobPOJO.getCompanyName()");
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewApplicationViewHolder extends RecyclerView.ViewHolder {

        public TextView applicantName, applicantAddress;
        public Button contactApplicant, viewProfile;


        public ViewApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ActiveJobsViewHolder: called");
            applicantName = itemView.findViewById(R.id.applicantNameTextView);
            applicantAddress = itemView.findViewById(R.id.applicantAddressTextView);
            contactApplicant = itemView.findViewById(R.id.contactApplicantButton);
            viewProfile = itemView.findViewById(R.id.viewApplicantProfileButton);


        }
    }
}
