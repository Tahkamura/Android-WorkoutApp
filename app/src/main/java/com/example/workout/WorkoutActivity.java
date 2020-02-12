package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    TextView name;
    TextView seconds;
    TextToSpeech talker = null;
    ArrayList<NewWorkout> recvWorkouts;
    int taskCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        findViewById(R.id.stopBtn).setOnClickListener(this);
        talker = new TextToSpeech(this, this);
        recvWorkouts = (ArrayList<NewWorkout>) getIntent().getSerializableExtra(MainActivity.workOutData);
        name = findViewById(R.id.currentWorkoutName);
        seconds = findViewById(R.id.secondsLeft);

        name.setText(recvWorkouts.get(0).getWorkoutName());
        seconds.setText(Integer.toString(recvWorkouts.get(0).getSeconds()));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                String speakLine = recvWorkouts.get(0).getWorkoutName() + recvWorkouts.get(0).getSeconds() + " sekuntia";
                talker.speak(speakLine, TextToSpeech.QUEUE_FLUSH, null);

            }
        }, 1000);
        loopDaLoop();
    }

    @Override
    public void onInit(int status) {

    }

    public void loopDaLoop()
    {
        if(taskCounter != recvWorkouts.size())
        {
            String speakLine = recvWorkouts.get(taskCounter).getWorkoutName() + recvWorkouts.get(taskCounter).getSeconds() + " sekuntia";
            talker.speak(speakLine, TextToSpeech.QUEUE_FLUSH, null);
            name.setText(recvWorkouts.get(taskCounter).getWorkoutName());
            seconds.setText(Integer.toString(recvWorkouts.get(taskCounter).getSeconds()));
            workOutTimer(recvWorkouts.get(taskCounter).getSeconds());
        }
        else
        {
            name.setText("Harjoitus päättynyt!");
            seconds.setText("");
            String speakLine = "Harjoitus on päättynyt,    hyvä treeni";
            talker.speak(speakLine, TextToSpeech.QUEUE_FLUSH, null);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 4000);

        }
    }
    public void workOutTimer(int time)
    {
        new CountDownTimer(time*1000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished/1000;
                seconds.setText(Long.toString(timeLeft));
            }

            @Override
            public void onFinish() {
                taskCounter++;
                loopDaLoop();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.stopBtn)
        {
            finish();
        }
    }
}

