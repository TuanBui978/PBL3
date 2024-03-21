package com.example.myapplication.config;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.JobInfomationBinding;


public class MainActivityRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        JobInfomationBinding jobInfomationBinding = JobInfomationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new JobNormalViewHolder(jobInfomationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class JobNormalViewHolder extends RecyclerView.ViewHolder {
        private JobInfomationBinding viewBinding;

        public JobNormalViewHolder(@NonNull JobInfomationBinding binding) {
            super(binding.getRoot());
            this.viewBinding = binding;
        }
    }

}
