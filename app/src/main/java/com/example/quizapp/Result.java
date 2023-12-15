package com.example.quizapp;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityResultBinding;

public class Result extends AppCompatActivity {
ActivityResultBinding activityResultBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        activityResultBinding=DataBindingUtil.setContentView(this,R.layout.activity_result);


        activityResultBinding.buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDaiolg();
            }
        });

        activityResultBinding.buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(Result.this,MainActivity.class);
               startActivity(intent);
            }
        });

    }

    private void showDaiolg() {



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("SCORE")
                    .setMessage("You Answered "+(MainActivity.result)+ " questions right out of ")
                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    })

                    .setCancelable(false)
                    .show();
        }
    }
