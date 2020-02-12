package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddWorkoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        findViewById(R.id.addWorkoutBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText nameEditor = findViewById(R.id.workoutName);
        EditText secondsEditor = findViewById(R.id.seconds);

        if(v.getId() == R.id.addWorkoutBtn)
        {
            if(nameEditor.getText().length() > 0 && secondsEditor.getText().length() > 0)
            {
                NewWorkout newWorkout = new NewWorkout(nameEditor.getText().toString(), Integer.parseInt(secondsEditor.getText().toString()));
                Intent returnIntent = new Intent();
                returnIntent.putExtra("NEW_PART", newWorkout);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

        }
    }
}
