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
import com.example.myapplication.models.company;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.companyViewHolder> {
    List<company> companyList;
    ViewPager2 viewPager2;

    public CompanyAdapter(List<company> companyList, ViewPager2 viewPager2) {
        this.companyList = companyList;
        this.viewPager2 = viewPager2;
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
                holder.logoView.setImageResource(companyList.get(position).getCompanyLogo());
                holder.imageView.setImageResource(companyList.get(position).getCompanyImage());
                holder.infomation.setText(companyList.get(position).getInfomation());
                holder.name.setText(companyList.get(position).getName());
            }
            else {
                Log.d("check", "EMPTY");
            }
        }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class companyViewHolder extends RecyclerView.ViewHolder {
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
        }
    }

}
