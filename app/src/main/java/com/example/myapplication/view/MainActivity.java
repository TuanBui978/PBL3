package com.example.myapplication.view;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.HeaderNavBinding;
import com.example.myapplication.models.User;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public enum PAGE {
        HOME_PAGE,
        ALL_JOB,
        COMPANY
    }
    public  PAGE currentPage = PAGE.HOME_PAGE;

    HeaderNavBinding headerNavBinding;
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCallBackButton();
        setUserInfoToNavHead();
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
                        replaceFragment(new AllJobFragment());
                        currentPage = PAGE.ALL_JOB;
                    }
                    return true;
                }
                if (id == R.id.nav_companies) {
                    if (currentPage != PAGE.COMPANY) {
                        replaceFragment(new AllCompanyFragment());
                    }
                    return true;

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

    public void setUserInfoToNavHead() {
        String jsonUser = getIntent().getExtras().getBundle("UserBundle").getString("currentUserLogin");
        User user = new Gson().fromJson(jsonUser, User.class);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        headerNavBinding = HeaderNavBinding.bind(mainBinding.navView.getHeaderView(0));
        headerNavBinding.emailTv.setText(user.getEmail());
        Picasso.get().load(user.getAvatar_scr()).into(headerNavBinding.avatar);
        headerNavBinding.nameTv.setText(user.getName());
    }






}