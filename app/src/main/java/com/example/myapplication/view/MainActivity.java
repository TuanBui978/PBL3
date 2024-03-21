package com.example.myapplication.view;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.config.CompanyAdapter;
import com.example.myapplication.config.MainActivityRecycleViewAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.models.company;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public enum PAGE {
        HOME_PAGE,
        ALL_JOB,
        ACCOUNT
    }
    public  PAGE currentPage = PAGE.HOME_PAGE;
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setCallBackButton();
        mainBinding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainBinding.drawLayout.openDrawer(mainBinding.navView);
            }
        });
        setMainView();
        setContentView(mainBinding.getRoot());
    }

    public void setCallBackButton() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mainBinding.drawLayout.isDrawerOpen(mainBinding.navView)) {
                    mainBinding.drawLayout.closeDrawer(mainBinding.navView);
                }
                else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this,callback);
    }


    public void setMainView() {
        CompanyAdapter adapter = new CompanyAdapter(putData(), mainBinding.companyViewPager);
        MainActivityRecycleViewAdapter rvAdapter = new MainActivityRecycleViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager horionzalRvManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mainBinding.companyViewPager.setAdapter(adapter);
        mainBinding.horizontalRecyclerview.setLayoutManager(horionzalRvManager);
        mainBinding.horizontalRecyclerview.setAdapter(rvAdapter);
        mainBinding.recyclerView.setLayoutManager(layoutManager);
        mainBinding.recyclerView.setAdapter(rvAdapter);
        mainBinding.latestJobRecyclerview.setLayoutManager(layoutManager2);
        mainBinding.latestJobRecyclerview.setAdapter(rvAdapter);
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


}