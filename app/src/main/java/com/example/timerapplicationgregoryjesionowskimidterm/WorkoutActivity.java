package com.example.timerapplicationgregoryjesionowskimidterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        int seconds = getIntent().getExtras().getInt("seconds");
        String title = getIntent().getExtras().getString("title");
        if (savedInstanceState==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, TimerFragment.newInstance(seconds, title))
                    .commit();
        }
    }
}