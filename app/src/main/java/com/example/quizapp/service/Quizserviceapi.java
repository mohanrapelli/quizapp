package com.example.quizapp.service;

import com.example.quizapp.model.Quizlist;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Quizserviceapi {
    @GET("myquizapi.php")
    Call<Quizlist> getData();

}
