package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiService;
import com.example.myapplication.config.CompanyAdapter;
import com.example.myapplication.config.JobAdapter;
import com.example.myapplication.databinding.FragmentAllJobBinding;
import com.example.myapplication.models.Company;
import com.example.myapplication.models.Industry;
import com.example.myapplication.models.Job;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllJobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllJobFragment extends Fragment {

    private FragmentAllJobBinding binding;

    private List<Job> jobList;

    private List<Company> companyList;

    private final List<Job> jobInAdapter = new ArrayList<>();

    private List<Industry> industryList;

    private List<String> industryNames;

    private int indexOfSubList = 0;
    public AllJobFragment() {

    }

    public static AllJobFragment newInstance(String param1, String param2) {
        AllJobFragment fragment = new AllJobFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllJobBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
                        int itemInsert;
                        if (jobList.size() - indexOfSubList <= 0) return;
                        if (jobList.size() - indexOfSubList <= 20) {
                            itemInsert = jobList.size() - indexOfSubList;
                        }
                        else itemInsert = 20;
                        jobInAdapter.addAll(jobList.subList(indexOfSubList,indexOfSubList += itemInsert));
                        recyclerView.getAdapter().notifyItemRangeInserted(jobInAdapter.size(), itemInsert); ;
                    }
                }
            }
        });
        getCompanyListFromApi();
        getIndustryListFromApi();

        return binding.getRoot();
    }



    public void getJobListFromAPI() {
        ApiService.apiService.getJobList().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    JobAdapter jobAdapter;
                    try {

                        jobInAdapter.addAll(jobList.subList(indexOfSubList,indexOfSubList += 20));
                        jobAdapter = new JobAdapter(jobInAdapter, new JobAdapter.OnRecycleViewClickListener() {
                            @Override
                            public void onRecycleViewClickListener(View v, int position) {
                                Bundle bundle = new Bundle();
                                String jsonJob = new Gson().toJson(jobInAdapter.get(position));
                                bundle.putString("jsonJob", jsonJob);
                                Intent intent = new Intent(AllJobFragment.this.getContext(), JobInfoActivity.class);
                                intent.putExtra("jobBundle", bundle);
                                startActivity(intent);
                            }
                        });
                    }
                    catch (IndexOutOfBoundsException e){
                        jobInAdapter.addAll(jobList.subList(indexOfSubList,indexOfSubList += jobList.size()));
                        jobAdapter = new JobAdapter(jobInAdapter, new JobAdapter.OnRecycleViewClickListener() {
                            @Override
                            public void onRecycleViewClickListener(View v, int position) {
                                Bundle bundle = new Bundle();
                                String jsonJob = new Gson().toJson(jobInAdapter.get(position));
                                bundle.putString("jsonJob", jsonJob);
                                Intent intent = new Intent(AllJobFragment.this.getContext(), JobInfoActivity.class);
                                intent.putExtra("jobBundle", bundle);
                                startActivity(intent);
                            }
                        });
                    }
                    binding.recyclerView.setAdapter(jobAdapter);
                }
                else {
                    Toast.makeText(AllJobFragment.this.getContext(), "Something Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable throwable) {
                Toast.makeText(AllJobFragment.this.getContext(), "False on call API", Toast.LENGTH_LONG).show();
            }
        });



    }

    void getIndustryListFromApi() {
        ApiService.apiService.getIndustryList().enqueue(new Callback<List<Industry>>() {
            @Override
            public void onResponse(Call<List<Industry>> call, Response<List<Industry>> response) {
                if (response.isSuccessful()) {
                    industryList = response.body();
                    industryNames = new ArrayList<>();
                    for (int i = 0; i < industryList.size(); i++) {
                        industryNames.add(industryList.get(i).getIndustryName());
                    }
                    ArrayAdapter<String> industrySpinnerAdapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, industryNames);
                    binding.industrySpinner.setAdapter(industrySpinnerAdapter);
                }
                else {
                    Toast.makeText(AllJobFragment.this.getContext(), "Something wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Industry>> call, Throwable throwable) {
                Toast.makeText(AllJobFragment.this.getContext(), "Server or internet is error", Toast.LENGTH_LONG).show();
            }
        });

    }

    void getCompanyListFromApi() {
        ApiService.apiService.getCompanyList().enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if (response.isSuccessful()) {
                    companyList = response.body();
                    getJobListFromAPI();
                }
                else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable throwable) {
                Toast.makeText(getContext(), "Something Wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

}