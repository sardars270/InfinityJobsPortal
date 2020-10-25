package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NumberOfApplicationsAdapter extends FirestoreRecyclerAdapter<User, NumberOfApplicationsAdapter.NumberOfApplicationsViewHolder> {
    private static final String TAG = "NoOfApplicationsAdapter";

    public NumberOfApplicationsAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @NonNull
    @Override
    public NumberOfApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_of_applications_row_layout, parent, false);
        NumberOfApplicationsViewHolder viewHolder = new NumberOfApplicationsViewHolder(view);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull NumberOfApplicationsViewHolder numberOfApplicationsViewHolder, int i, @NonNull User user) {
        Log.d(TAG, "onBindViewHolder: started");
        numberOfApplicationsViewHolder.contactApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        numberOfApplicationsViewHolder.viewApplicantProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    public class NumberOfApplicationsViewHolder extends RecyclerView.ViewHolder {
        TextView applicantName, applicantAddress;
        Button contactApplicant, viewApplicantProfile;

        public NumberOfApplicationsViewHolder(@NonNull View itemView) {
            super(itemView);
            applicantName = itemView.findViewById(R.id.applicantNameTextView);
            applicantAddress = itemView.findViewById(R.id.applicantAddressTextView);
            contactApplicant = itemView.findViewById(R.id.contactApplicantButton);
            viewApplicantProfile = itemView.findViewById(R.id.viewApplicantProfileButton);

        }
    }

}
