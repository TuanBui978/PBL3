package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiService;
import com.example.myapplication.config.ScreenSlidePagerAdapter;
import com.example.myapplication.databinding.ActivityJobInfoBinding;
import com.example.myapplication.models.Company;
import com.example.myapplication.models.Job;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobInfoActivity extends AppCompatActivity {

    private ActivityJobInfoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityJobInfoBinding.inflate(getLayoutInflater());

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobInfoActivity.super.getOnBackPressedDispatcher().onBackPressed();
            }
        });

        String jsonJob = getIntent().getBundleExtra("jobBundle").getString("jsonJob");

        Job job = new Gson().fromJson(jsonJob, Job.class);

        binding.jobName.setText(job.getJobName());
        Picasso.get().load(job.getSourcePicture()).resize(2048, 1600).onlyScaleDown().into(binding.companyLogo);

        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

        JobTabFragment jobTabFragment = new JobTabFragment(job);

        fragmentList.add(jobTabFragment);

        ApiService.apiService.getCompanyById(job.getCompanyId()).enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Company company = response.body();
                        fragmentList.add(CompanyTabFragment.newInstance(company));
                        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(JobInfoActivity.this, fragmentList);

                        binding.viewPager.setAdapter(adapter);

                        String[] tabText = new String[2];
                        tabText[0] = "Job Details";
                        tabText[1] = "Company";
                        new TabLayoutMediator(binding.tabLayout, binding.viewPager, ((tab, position) -> {
                            tab.setText(tabText[position]);
                        })).attach();
                    }

                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable throwable) {
                Toast.makeText(binding.getRoot().getContext(), "something wrong", Toast.LENGTH_LONG).show();
            }
        });




        setContentView(binding.getRoot());
    }
}