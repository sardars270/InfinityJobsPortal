package com.example.infinityjobportal.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.JobDetails;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;

import java.util.ArrayList;
import java.util.List;

public class JobSearchAdapter extends RecyclerView.Adapter<JobSearchAdapter.ExampleViewHolder> implements Filterable {
    private List<PostJobPojo> exampleList;
    private List<PostJobPojo> exampleListFull;

Context context;

    class ExampleViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        CardView lout;

        TextView title, at, location,id;
        ImageView saveJob;



        ExampleViewHolder(View itemView) {
            super(itemView);
          //  imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.title);
            at=itemView.findViewById(R.id.at);
            location=itemView.findViewById(R.id.location);

            lout=itemView.findViewById(R.id.lout);
            saveJob  = itemView.findViewById(R.id.saveJob);
            id  = itemView.findViewById(R.id.id);
           // textView2 = itemView.findViewById(R.id.text_view2);
        }
    }

    public JobSearchAdapter(Context applicationContext, List<PostJobPojo> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
        this.context=applicationContext;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,
                parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        PostJobPojo currentItem = exampleList.get(position);

      //  holder.imageView.setImageResource(currentItem.gett());
        holder.textView1.setText(currentItem.getJobTitle());
        holder.at.setText(currentItem.getCompanyName());
        holder.location.setText(currentItem.getCityAddress());
        holder.id.setText(currentItem.getId());

/*
        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, JobDetails.class);
                i.putExtra("id", holder.id.getText().toString());
                context.startActivity(i);
            }
        });


 */
        // holder.textView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PostJobPojo> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PostJobPojo item : exampleListFull) {
                    if (item.getJobTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}