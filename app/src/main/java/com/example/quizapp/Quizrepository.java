package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.model.Quizlist;
import com.example.quizapp.service.RetrofitInstance;
import com.example.quizapp.service.Quizserviceapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quizrepository {
    Quizserviceapi questionApi;

    public Quizrepository() {
        this.questionApi = new RetrofitInstance().getService();
    }

    public LiveData<Quizlist> get1() {
        MutableLiveData<Quizlist> data = new MutableLiveData<>();



        Call<Quizlist> call = questionApi.getData();
        call.enqueue(new Callback<Quizlist>() {
            @Override
            public void onResponse(@NonNull Call<Quizlist> call, @NonNull Response<Quizlist> response) {
                if(response.isSuccessful()){
                    Quizlist quizlist= response.body();
                    if(quizlist!=null){
                        data.setValue(quizlist);
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<Quizlist> call, @NonNull Throwable t) {

            }
        });


        return data;
    }
}
