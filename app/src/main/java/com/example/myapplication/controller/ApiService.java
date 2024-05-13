package com.example.myapplication.controller;


import com.example.myapplication.models.Company;
import com.example.myapplication.models.Industry;
import com.example.myapplication.models.Job;
import com.example.myapplication.models.Message;
import com.example.myapplication.models.PostUser;
import com.example.myapplication.models.User;
import com.example.myapplication.models.UserJob;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService{

    Gson gson = new Gson().newBuilder().create();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.11:3000").addConverterFactory(GsonConverterFactory.create(gson)).build();

    ApiService apiService = retrofit.create(ApiService.class);


    @POST("/register")
    Call<Message> postRegister(@Body PostUser user);

    @POST("/login")
    Call<User> postLogin(@Body PostUser user);

    @GET("/job/job-list")
    Call<List<Job>> getJobList();

    @GET("/industry/industry-list")
    Call<List<Industry>> getIndustryList();

    @GET("/company/company-list")
    Call<List<Company>> getCompanyList();

    @GET("/job/job-list-by-company")
    Call<List<Job>> getJobListByCompany(@Query("companyId") int companyId);

    @GET("/company/id")
    Call<Company> getCompanyById(@Query("companyId") int companyId);

    @GET("/job/job-list-by-name")
    Call<List<Job>> getJobListByName(@Query("jobName") String name);


    @PUT("/user/update")
    Call<Message> updateUser(@Query("id")int id, @Body PostUser user);

    @GET("/user/user-job/my-apply-job")
    Call<List<UserJob>> getUserJob(@Query("userID")int userId, @Query("jobID")int jobId);

    @POST("/user/user-job/create")
    Call<Message> createUserJob(@Query("userID") int userId, @Query("jobID") int jobId);

    @DELETE("/user/user-job/delete")
    Call<Message> deleteUserJob(@Query("id") int id);
}
