package com.example.timerapplicationgregoryjesionowskimidterm;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.os.Looper.getMainLooper;

public class TimerFragment extends Fragment implements View.OnClickListener {

    private String title;
    private TextView congrats;
    private int seconds;
    private boolean paused = true;
    private TextView tv;
    private FloatingActionButton fab;
    private Drawable PLAY;
    private Drawable PAUSE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.floatingActionButton).setOnClickListener(this);

        title = this.getArguments().getString("title");
        seconds = this.getArguments().getInt("seconds");

        PLAY = getResources().getDrawable(android.R.drawable.ic_media_play);
        PAUSE = getResources().getDrawable(android.R.drawable.ic_media_pause);
        tv = view.findViewById(R.id.time_text);
        fab = view.findViewById(R.id.floatingActionButton);
        congrats = view.findViewById(R.id.congratsMessage);

        fab.setClickable(true);
        fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
        if (savedInstanceState != null) {
            paused = savedInstanceState.getBoolean("paused");
            seconds = savedInstanceState.getInt("seconds");
            setIcon();
        }
        runTimer();
    }

    private void setIcon() {
        Drawable icon = paused ? PLAY : PAUSE;
        fab.setImageDrawable(icon);
    }

    public void runTimer() {
        final Handler handler = new Handler(getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                Log.i("INFO", "Reached Runnable");
                int sec = seconds % 60;
                int min = (seconds % 3600) / 60;
                int hour = seconds / 3600;
                tv.setText(String.format("%02d : %02d : %02d", hour, min, sec));
                if (!paused) {
                    seconds--;
                    if (seconds == 0) {
                        paused = true;
                        fab.setImageDrawable(getResources().getDrawable(android.R.drawable.star_big_off));
                        fab.setClickable(false);
                        congrats.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        startTimer();
    }

    public void startTimer() {
        paused = !paused;
        setIcon();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("paused", paused);
        outState.putInt("seconds", seconds);
    }

    public static TimerFragment newInstance(int seconds, String title) {
        TimerFragment tf = new TimerFragment();
        Bundle args = new Bundle();
        args.putInt("seconds", seconds);
        args.putString("title", title);
        tf.setArguments(args);
        return tf;
    }

}