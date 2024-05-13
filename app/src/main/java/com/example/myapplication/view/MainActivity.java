package com.example.myapplication.view;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controller.ApiService;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.HeaderNavBinding;
import com.example.myapplication.models.Job;
import com.example.myapplication.models.User;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final public static int HOME_PAGE = 1;
    final public static int ALL_JOB = 2;
    final public static int COMPANY = 3;
    final public static int USER_PAGE = 4;
    public int currentPage = HOME_PAGE;

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

//        mainBinding.searchBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentPage != ALL_JOB) {
//                    replaceFragment(new AllJobFragment());
//                    currentPage = ALL_JOB;
//                }
//            }
//        });

//        mainBinding.searchBar.

        this.getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mainBinding.navView.isActivated()) {
                    MainActivity.super.getOnBackPressedDispatcher().onBackPressed();
                }
                else {
                    moveTaskToBack(true);
                    finish();
                }
            }
        });

        mainBinding.searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainBinding.searchBar.setIconified(false);
                if (currentPage != ALL_JOB) {
                    replaceFragment(new AllJobFragment());
                    currentPage = ALL_JOB;
                }
            }
        });

        mainBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                ApiService.apiService.getJobListByName(query).enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        if (currentPage != ALL_JOB) {
                            replaceFragment(new AllJobFragment());
                            currentPage = ALL_JOB;
                        }
                        if (response.isSuccessful()) {
                            AllJobFragment allJobFragment = (AllJobFragment) mainBinding.fragmentContainerView5.getFragment();
                            allJobFragment.changeJobList(response.body());
                        }
                        else {
                            Toast.makeText(mainBinding.getRoot().getContext(), "Something wrong", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable throwable) {
                        if (currentPage != ALL_JOB) {
                            replaceFragment(new AllJobFragment());
                            currentPage = ALL_JOB;
                        }
                        Toast.makeText(mainBinding.getRoot().getContext(), "Fail on call api", Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ApiService.apiService.getJobListByName(newText).enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {

                        if (response.isSuccessful()) {
                            if (currentPage != ALL_JOB) {
                                replaceFragment(new AllJobFragment());
                                currentPage = ALL_JOB;
                            }
                            AllJobFragment allJobFragment = mainBinding.fragmentContainerView5.getFragment();
                            allJobFragment.changeJobList(response.body());
                        }
                        else {
                            Toast.makeText(mainBinding.getRoot().getContext(), "Something wrong", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable throwable) {
                        Toast.makeText(mainBinding.getRoot().getContext(), "Fail on call api", Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });



        mainBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    if (currentPage != HOME_PAGE) {
                        replaceFragment(new HomeFragment());
                        getOnBackPressedDispatcher().onBackPressed();
                        currentPage = HOME_PAGE;
                    }
                    return true;
                }
                if (id == R.id.nav_job) {
                    if (currentPage != ALL_JOB) {
                        replaceFragment(new AllJobFragment());
                        getOnBackPressedDispatcher().onBackPressed();
                        currentPage = ALL_JOB;
                    }
                    return true;
                }
                if (id == R.id.nav_companies) {
                    if (currentPage != COMPANY) {
                        replaceFragment(new AllCompanyFragment());
                        getOnBackPressedDispatcher().onBackPressed();
                        currentPage = COMPANY;
                    }
                    return true;

                }
                if (id == R.id.nav_logout) {
                    MainActivity.this.finish();
                    return false;
                }
                return false;
            }

        });

        mainBinding.navView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage != USER_PAGE) {
                    replaceFragment(new UserInfoFragment().newInstance(getIntent().getExtras().getBundle("UserBundle").getString("currentUserLogin")));
                    currentPage = USER_PAGE;
                }
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

    public ActivityMainBinding getBinding() {
        return this.mainBinding;
    }






}