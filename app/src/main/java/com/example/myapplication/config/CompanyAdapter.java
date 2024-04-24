package com.example.myapplication.config;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.models.Company;
import com.squareup.picasso.Picasso;

import java.util.List;

import kotlinx.coroutines.Job;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.companyViewHolder> {
    List<Company> companyList;

    ViewPager2 viewPager2;

    JobAdapter.OnRecycleViewClickListener onRecycleViewClickListener;

    public CompanyAdapter(List<Company> companyList, ViewPager2 viewPager2, JobAdapter.OnRecycleViewClickListener onRecycleViewClickListener) {
        this.companyList = companyList;
        this.viewPager2 = viewPager2;
        this.onRecycleViewClickListener = onRecycleViewClickListener;
    }

    @NonNull
    @Override
    public companyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_pager, parent, false);
        return new companyViewHolder(view);
    }

    @Override
        public void onBindViewHolder(@NonNull companyViewHolder holder, int position) {
            if (!companyList.isEmpty()) {
                Company company = companyList.get(position);
                holder.name.setText(company.getCompanyName());
                holder.infomation.setText(company.getCompanyDescription());
                Picasso.get().load(company.getCompanyLogo()).resize(2048,1600).onlyScaleDown().into(holder.logoView);
            }
            else {
                Log.d("check", "EMPTY");
            }

        }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class companyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView logoView;
        ImageView imageView;
        TextView infomation;
        TextView name;
        public companyViewHolder(@NonNull View itemView) {
            super(itemView);
            logoView = itemView.findViewById(R.id.company_logo);
            imageView = itemView.findViewById(R.id.company_image);
            infomation = itemView.findViewById(R.id.company_intro);
            name = itemView.findViewById(R.id.company_name);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onRecycleViewClickListener.onRecycleViewClickListener(view, this.getLayoutPosition());
        }
    }




}
