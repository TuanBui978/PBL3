package com.example.myapplication.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.CvViewHolderBinding;
import com.example.myapplication.models.CV;

import java.util.List;

public class CVRecycleViewAdapter extends RecyclerView.Adapter<CVRecycleViewAdapter.CVViewHolder> {

    private final List<CV> cvList;

    private final OnClickListener onClickListener;

    public CVRecycleViewAdapter(List<CV> list, OnClickListener onClickListener) {
        this.cvList = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CVRecycleViewAdapter.CVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CvViewHolderBinding binding = CvViewHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CVViewHolder(binding, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CVViewHolder holder, int position) {
        holder.getBinding().fileName.setText(cvList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cvList.size();
    }

    static class CVViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final CvViewHolderBinding binding;

        private final OnClickListener onClickListener;

        public CvViewHolderBinding getBinding() {
            return binding;
        }

        public CVViewHolder(@NonNull CvViewHolderBinding binding, OnClickListener onClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onClickListener = onClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(view, getLayoutPosition());
        }

    }

    public interface OnClickListener {
        public void onClick(View view, int pos);
    }
}
