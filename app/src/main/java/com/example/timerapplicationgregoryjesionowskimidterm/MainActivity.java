package com.example.timerapplicationgregoryjesionowskimidterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton) findViewById(R.id.workout1)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.workout2)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.workout3)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
        switch (v.getId()) {
            case R.id.workout1:
                intent.putExtra("seconds", 300);
                intent.putExtra("title", "Crossfit");
                startActivity(intent);
                break;
            case R.id.workout2:
                intent.putExtra("seconds", 30);
                intent.putExtra("title", "Dumbbells");
                startActivity(intent);
                break;
            case R.id.workout3:
                intent.putExtra("seconds", 14400);
                intent.putExtra("title", "Squats");
                startActivity(intent);
                break;
        }
    }
}