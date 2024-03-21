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
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
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
        COMPANY
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

        mainBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    if (currentPage != PAGE.HOME_PAGE) {
                        replaceFragment(new HomeFragment());
                        currentPage = PAGE.HOME_PAGE;
                    }
                    return true;
                }
                if (id == R.id.nav_job) {
                    if (currentPage != PAGE.ALL_JOB) {
                        replaceFragment(new all_job_fragment());
                        currentPage = PAGE.ALL_JOB;
                    }
                    return true;
                }
                if (id == R.id.nav_companies) {
//                    if (currentPage != PAGE.HOME_PAGE) {
//                        replaceFragment(new HomeFragment());
//                    }
//                    return true;

                }
                return false;
            }

        });

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

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mainBinding.fragmentContainerView5.getId(),fragment);
        fragmentTransaction.commit();
    }






}