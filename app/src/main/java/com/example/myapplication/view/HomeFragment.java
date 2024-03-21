package com.example.myapplication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.config.CompanyAdapter;
import com.example.myapplication.config.MainActivityRecycleViewAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.company;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

//    public HomeFragment() {
//        super();
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setMainView();
        return binding.getRoot();
    }
    public List<company> putData() {
        company a = new company(R.drawable.microsoft_logo,R.drawable.microsoft_image, "Microsoft", R.string.microsoft_intro, "null");
        List<company> listCompany = new ArrayList<>();
        listCompany.add(a);
        a = new company(R.drawable.topdev_logo,R.drawable.topdev_image, "Top Dev", R.string.topdev_intro, "null");
        listCompany.add(a);
        a = new company(R.drawable.viettel_logo,R.drawable.viettel_image, "Viettel", R.string.viettel_intro, "null");
        listCompany.add(a);
        a = new company(R.drawable.pb_image,R.drawable.pb_logo, "Public bank", R.string.pb_intro, "null");
        listCompany.add(a);
        return listCompany;
    }
    public void setMainView() {
        CompanyAdapter adapter = new CompanyAdapter(putData(), binding.companyViewPager);
        MainActivityRecycleViewAdapter rvAdapter = new MainActivityRecycleViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        LinearLayoutManager horionzalRvManager = new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false);
        binding.companyViewPager.setAdapter(adapter);
        binding.horizontalRecyclerview.setLayoutManager(horionzalRvManager);
        binding.horizontalRecyclerview.setAdapter(rvAdapter);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(rvAdapter);
        binding.latestJobRecyclerview.setLayoutManager(layoutManager2);
        binding.latestJobRecyclerview.setAdapter(rvAdapter);
    }
}