package com.example.infinityjobportal.admin.ui.queries;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.admin.ViewQuery;

public class QueriesFragment extends Fragment {
LinearLayout loadQueryDetails;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.admin_fragment_queries, container, false);
            loadQueryDetails = root.findViewById(R.id.loadQueryDetails);

            loadQueryDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), ViewQuery.class));
                }
            });

        return root;
    }
}