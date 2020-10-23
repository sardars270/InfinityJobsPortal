package com.example.infinityjobportal.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infinityjobportal.CompanyProfileActivity;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.Company;


import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private Company company;
    private Context context;
    private List<Company> companyList;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCompanyName, tvIndustry, tvLocation;
        ConstraintLayout constraintLayout;
        AppCompatImageView ivCompanyLogo;


        ViewHolder(View view) {
            super(view);
            ivCompanyLogo = view.findViewById(R.id.ivCompanyLogo);
            tvCompanyName = view.findViewById(R.id.tvCompanyName);
            tvIndustry = view.findViewById(R.id.tvIndustry);
            tvLocation = view.findViewById(R.id.tvLocation);
            constraintLayout = view.findViewById(R.id.constraintLayout);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    company = companyList.get(getAdapterPosition());
                    Intent i = new Intent(context, CompanyProfileActivity.class);
                    i.putExtra("id", company.getId());
                    i.putExtra("name", company.getName());
                    i.putExtra("industry", company.getIndustry());
                    i.putExtra("country", company.getCountry());
                    i.putExtra("email", company.getEmail());
                    i.putExtra("contact", company.getContact());
                    i.putExtra("desc", company.getDesc());
                    i.putExtra("about", company.getAbout());
                    i.putExtra("web", company.getWeb());
                    i.putExtra("city", company.getCity());
                    i.putExtra("state", company.getState());
                    context.startActivity(i);
                }
            });

        }


    }

    public CompanyAdapter(Context mContext, List<Company> companyList) {
        this.context = mContext;
        this.companyList = companyList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);


        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        company = companyList.get(position);
        holder.tvCompanyName.setText(company.getName());
        holder.tvIndustry.setText(company.getIndustry());
        holder.tvLocation.setText(company.getLocation());
     //   Log.e("url", company.getLogoURL());
        Glide.with(context)
                .load(company.getLogoURL())
                .circleCrop()
                .error(R.drawable.company_thumb)
                .into(holder.ivCompanyLogo);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }


}