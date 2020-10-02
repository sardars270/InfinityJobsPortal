package com.example.infinityjobportal.ui.postedJobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.infinityjobportal.R;

public class PostedJobsFragment extends Fragment {

    private PostedJobsViewModel postedJobsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postedJobsViewModel =
                ViewModelProviders.of(this).get(PostedJobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_posted_jobs, container, false);
        final TextView textView = root.findViewById(R.id.text_posted_jobs);
        postedJobsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}