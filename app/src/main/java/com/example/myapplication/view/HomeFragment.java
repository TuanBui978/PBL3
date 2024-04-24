package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiService;
import com.example.myapplication.config.CompanyAdapter;
import com.example.myapplication.config.JobAdapter;
import com.example.myapplication.config.ScreenSlidePagerAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.Job;
import com.example.myapplication.models.Company;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    List<Job> jobList;

    List<Company> companyList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCompanyListFromApi();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.latestJobViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView5, new AllJobFragment());
                fragmentTransaction.commit();
            }
        });



        return binding.getRoot();
    }
    public void getJobListFromAPI() {
        ApiService.apiService.getJobList().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    setMainView();
                }
                else {
                    Toast.makeText(HomeFragment.this.getContext(), "Something Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable throwable) {
                    Toast.makeText(HomeFragment.this.getContext(), "False on call API", Toast.LENGTH_LONG).show();
            }
        });



    }
    public void setMainView() {
        JobAdapter rvAdapter = new JobAdapter(jobList.subList(0, 5), new JobAdapter.OnRecycleViewClickListener() {
            @Override
            public void onRecycleViewClickListener(View v, int position) {
                        Bundle bundle = new Bundle();
                        String jsonJob = new Gson().toJson(jobList.subList(0, 5).get(position));
                        bundle.putString("jsonJob", jsonJob);
                        Intent intent = new Intent(HomeFragment.this.getContext(), JobInfoActivity.class);
                        intent.putExtra("jobBundle", bundle);
                        startActivity(intent);
                    }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeFragment.this.getContext(), RecyclerView.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(HomeFragment.this.getContext(), RecyclerView.VERTICAL, false);
        binding.latestJobRecyclerview.setLayoutManager(layoutManager2);
        binding.latestJobRecyclerview.setAdapter(rvAdapter);

        LinearLayoutManager hotJobRvManager = new LinearLayoutManager(HomeFragment.this.getContext(), RecyclerView.HORIZONTAL, false);
        binding.hotJobRecyclerview.setLayoutManager(hotJobRvManager);
        binding.hotJobRecyclerview.setAdapter(rvAdapter);


//        binding.recyclerView.setLayoutManager(layoutManager);
//        binding.recyclerView.setAdapter(rvAdapter);
    }

    void getCompanyListFromApi() {
        ApiService.apiService.getCompanyList().enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if (response.isSuccessful()) {
                    companyList = response.body();
                    CompanyAdapter companyAdapter = new CompanyAdapter(companyList.subList(0, 5), binding.companyViewPager, new JobAdapter.OnRecycleViewClickListener() {
                        @Override
                        public void onRecycleViewClickListener(View v, int position) {
                            //can them vao
                        }
                    });
                    binding.companyViewPager.setAdapter(companyAdapter);
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