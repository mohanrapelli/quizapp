package com.example.quizapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.model.Quizlist;

public class Viewmodel extends ViewModel {
    Quizrepository repository = new Quizrepository();


    LiveData<Quizlist> questionListLiveData;

    public Viewmodel(){
        questionListLiveData = repository.get1();
    }

    public LiveData<Quizlist> get2() {
        return questionListLiveData;
    }
}
