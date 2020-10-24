package com.example.infinityjobportal.ui.postedJobs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.adapter.PostedJobsPagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class PostedJobsFragment extends Fragment {
    private static final String TAG = "PostedJobsFragment";
    TabLayout tabLayout;
    TabItem activeTab, closedTab, draftTab;
    ViewPager2 viewPager2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: has started");
        View root = inflater.inflate(R.layout.fragment_posted_jobs, container, false);

        tabLayout = root.findViewById(R.id.postedJobsTabBar);
        activeTab = root.findViewById(R.id.tabBar_active);
        closedTab = root.findViewById(R.id.tabBar_closed);
        draftTab = root.findViewById(R.id.tabBar_draft);

        viewPager2 = root.findViewById(R.id.viewPager);
        viewPager2.setAdapter(new PostedJobsPagerAdapter(this));
        Log.d(TAG, "onCreateView: has ended");
        return root;
    }
}