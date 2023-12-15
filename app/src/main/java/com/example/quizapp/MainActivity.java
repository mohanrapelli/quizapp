package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Quizlist;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding activityMainBinding;
    Viewmodel quizviewmodel;
    List<Quiz> quizquestionList;
    static int result = 0;
    static int totalQuestions = 0;
    int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        quizviewmodel = new ViewModelProvider(this).get(Viewmodel.class);


        result = 0;

        totalQuestions = 0;


        ShowFirstQuestion();
        activityMainBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shownextquestion();
            }
        });
    }

    private void ShowFirstQuestion() {
        quizviewmodel.get2().observe(this, new Observer<Quizlist>() {
            @Override
            public void onChanged(Quizlist quizzes) {
                quizquestionList=quizzes;

                activityMainBinding.txtQuestion.setText(String.format("Question 1 :%s", quizzes.get(0).getQuestion()));
                activityMainBinding.radio1.setText(quizzes.get(0).getOption1());
                activityMainBinding.radio2.setText(quizzes.get(0).getOption2());
                activityMainBinding.radio3.setText(quizzes.get(0).getOption3());
                activityMainBinding.radio4.setText(quizzes.get(0).getOption4());
            }
        });
    }
    private void Shownextquestion(){
        if(activityMainBinding.btnNext.getText().toString().equals("Done")){

            Intent intent=new Intent(MainActivity.this, Result.class);
           startActivity(intent);
            finish();
        }
        int selectedoption=activityMainBinding.radioGroup.getCheckedRadioButtonId();
        if(selectedoption!=-1){
            RadioButton radioButton=findViewById(selectedoption);
            if (quizquestionList.size() -1 > 0){
                totalQuestions=quizquestionList.size();
                if (radioButton.getText().toString().equals((quizquestionList.get(i).getCorrectOption()))){
                    result++;
                    activityMainBinding.txtResult.setText(String.valueOf(result));
                }
                if(i==0){
                    i++;
                }
                activityMainBinding.txtQuestion.setText(String.format("Question"+((i+1))+":"+ quizquestionList.get(i).getQuestion()));
                activityMainBinding.radio1.setText(quizquestionList.get(i).getOption1());
                activityMainBinding.radio2.setText(quizquestionList.get(i).getOption2());
                activityMainBinding.radio3.setText(quizquestionList.get(i).getOption3());
                activityMainBinding.radio4.setText(quizquestionList.get(i).getOption4());
                if(i==(quizquestionList.size() -1)){
                    activityMainBinding.btnNext.setText("Done");
                }
                activityMainBinding.radioGroup.clearCheck();
                i++;


            }

        }
        else {
            Toast.makeText(this, "please select answer", Toast.LENGTH_SHORT).show();
        }

    }

}