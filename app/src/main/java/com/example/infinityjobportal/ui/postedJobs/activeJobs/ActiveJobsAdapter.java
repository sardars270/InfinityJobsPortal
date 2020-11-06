package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.ClientLogin;
import com.example.infinityjobportal.JobDetails;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActiveJobsAdapter extends FirestoreRecyclerAdapter<PostJobPojo, ActiveJobsAdapter.ActiveJobsViewHolder> {
    private static final String TAG = "ActiveJobsAdapter";


    private Context context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActiveJobsAdapterListener activeJobsAdapterListener;


    public ActiveJobsAdapter(@NonNull FirestoreRecyclerOptions<PostJobPojo> options) {
        super(options);
    }

    public ActiveJobsAdapter(@NonNull FirestoreRecyclerOptions<PostJobPojo> options, Context context, FirebaseFirestore db, ActiveJobsAdapterListener activeJobsAdapterListener) {
        super(options);
        this.context = context;
        this.db = db;
        this.activeJobsAdapterListener = activeJobsAdapterListener;
    }

    //    public ActiveJobsAdapter(@NonNull FirestoreRecyclerOptions<PostJobPojo> options, Context context) {
//        super(options);
//        this.context = context;
//    }

    @NonNull
    @Override
    public ActiveJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_jobs_row_layout, parent, false);
        ActiveJobsViewHolder viewHolder = new ActiveJobsViewHolder(view);
        return viewHolder;
    }


    @Override
    protected void onBindViewHolder(@NonNull final ActiveJobsViewHolder activeJobsViewHolder, int i, @NonNull PostJobPojo postJobPOJO) {
        Log.d(TAG, "onBindViewHolder: called");
        activeJobsViewHolder.jobTitle.setText(postJobPOJO.getJobTitle());
        activeJobsViewHolder.companyName.setText(postJobPOJO.getCompanyName());
        activeJobsViewHolder.companyAddress.setText(postJobPOJO.getCityAddress() + " " + postJobPOJO.getProvinceAddress());


    }


    public class ActiveJobsViewHolder extends RecyclerView.ViewHolder {

        public TextView jobTitle, companyName, companyAddress, numberOfApplications;
        public Button viewDetails, viewApplication;
        public ConstraintLayout constraintLayout; //used to attach onClickListener.
        int position = getAdapterPosition();

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

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && activeJobsAdapterListener != null) {
                        activeJobsAdapterListener.viewDetailsOnClick(getSnapshots().getSnapshot(position), position);
                    }
                    Intent intent = new Intent(view.getContext(), JobDetails.class);
                    view.getContext().startActivity(intent);

                }
            });

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ClientLogin.class);
                    view.getContext().startActivity(intent);

                }
            });

            viewApplication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: View Applications");

                }
            });

        }
    }

    public interface ActiveJobsAdapterListener {
        void viewDetailsOnClick(DocumentSnapshot documentSnapshot, int position);
        void viewApplicationsOnClick(View view, int position);
    }

    public void setActiveJobsAdapterListener(ActiveJobsAdapterListener activeJobsAdapterListener){
        this.activeJobsAdapterListener = activeJobsAdapterListener;
    }

}
