package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ActiveJobsAdapter extends FirestoreRecyclerAdapter<PostJobPojo, ActiveJobsAdapter.ActiveJobsViewHolder> {
    private static final String TAG = "ActiveJobsAdapter";
    PostJobPojo postJobPOJO;


    public ActiveJobsAdapter(@NonNull FirestoreRecyclerOptions<PostJobPojo> options) {
        super(options);
    }


    @NonNull
    @Override
    public ActiveJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_jobs_row_layout, parent, false);
        ActiveJobsViewHolder viewHolder = new ActiveJobsViewHolder(view);
        return viewHolder;
    }


    @Override
    protected void onBindViewHolder(@NonNull ActiveJobsViewHolder activeJobsViewHolder, int i, @NonNull PostJobPojo postJobPOJO) {
        Log.d(TAG, "onBindViewHolder: called");
        activeJobsViewHolder.jobTitle.setText(postJobPOJO.getJobTitle());
        activeJobsViewHolder.companyName.setText(postJobPOJO.getCompanyName());
        activeJobsViewHolder.companyAddress.setText(postJobPOJO.getCityAddress() + " " + postJobPOJO.getProvinceAddress());

        activeJobsViewHolder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: View Jobs");
                Navigation.findNavController(view).navigate(R.id.myJobsFragment);
            }
        });
        activeJobsViewHolder.viewApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: View Applications");
                Navigation.findNavController(view).navigate(R.id.homeFragment);
            }
        });

    }

    public class ActiveJobsViewHolder extends RecyclerView.ViewHolder {

        public TextView jobTitle, companyName, companyAddress, numberOfApplications;
        public Button viewDetails, viewApplication;
        public ConstraintLayout constraintLayout; //used to attach onClickListener

        public ActiveJobsViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ActiveJobsViewHolder: called");
            jobTitle = itemView.findViewById(R.id.jobTitleDraftJobsTextView);
            companyName = itemView.findViewById(R.id.companyNameDraftJobsTextView);
            companyAddress = itemView.findViewById(R.id.companyAddressDraftJobsTextView);
            numberOfApplications = itemView.findViewById(R.id.numberOfApplicationTextView);
            viewDetails = itemView.findViewById(R.id.jobDetailsDraftJobsButton);
            viewApplication = itemView.findViewById(R.id.viewApplicationsActiveJobButton);
            constraintLayout = itemView.findViewById(R.id.active_jobs_constraint_layout);


        }
    }
}
