package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.CompanyInfomationBinding;
import com.example.myapplication.models.Company;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyRecycleViewAdapter extends RecyclerView.Adapter<CompanyRecycleViewAdapter.CompanyRecycleViewHolder> {

    private int PAGE = 0;

    List<Company> companyList;

    OnRecycleViewOnClickListener recycleViewOnClickListener;

    public void insertPage() {
        this.PAGE++;
    }

    public int getPage() {
        return PAGE;
    }

    public CompanyRecycleViewAdapter(List<Company> companyList, OnRecycleViewOnClickListener recycleViewOnClickListener) {
        this.companyList = companyList;
        this.recycleViewOnClickListener = recycleViewOnClickListener;
    }

    @NonNull
    @Override
    public CompanyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompanyRecycleViewHolder(CompanyInfomationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyRecycleViewHolder holder, int position) {
        holder.binding.companyName.setText(companyList.get(position).getCompanyName());
        Picasso.get().load(companyList.get(position).getCompanyLogo()).resize(640,480).onlyScaleDown().into(holder.binding.companyLogo);
        holder.binding.locationTv.setText(companyList.get(position).getLocation());
        holder.binding.staffSizeTv.setText(companyList.get(position).getStaffSize());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public interface OnRecycleViewOnClickListener {
        public void onClickListener(View view, int position);
    }

    class CompanyRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CompanyInfomationBinding binding;

        public CompanyRecycleViewHolder(CompanyInfomationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recycleViewOnClickListener.onClickListener(view, getLayoutPosition());
        }
    }
}
