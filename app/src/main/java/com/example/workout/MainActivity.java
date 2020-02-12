package com.example.workout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  int ADD_WORKOUT_RETURN_CODE = 4949;
    ArrayList<NewWorkout> allWorkouts = new ArrayList<>();
    ArrayAdapter<NewWorkout> adapter;
    ListView list;

    public static final  String workOutData = "WORKOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter  = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, allWorkouts);

        findViewById(R.id.addNewWorkoutBtn).setOnClickListener(this);
        findViewById(R.id.startBtn).setOnClickListener(this);

        list = findViewById(R.id.workoutList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                allWorkouts.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_WORKOUT_RETURN_CODE && resultCode == Activity.RESULT_OK)
        {
            NewWorkout asd = (NewWorkout) data.getSerializableExtra("NEW_PART");
            allWorkouts.add(asd);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addNewWorkoutBtn)
        {
            Intent newWorkout = new Intent(this,AddWorkoutActivity.class);
            startActivityForResult(newWorkout,ADD_WORKOUT_RETURN_CODE);
        }
        if(v.getId() == R.id.startBtn)
        {
            if(allWorkouts.size() > 0) {
                Intent startWorkout = new Intent(this, WorkoutActivity.class);
                startWorkout.putExtra(workOutData, allWorkouts);
                startActivity(startWorkout);
            }
        }

    }

}
