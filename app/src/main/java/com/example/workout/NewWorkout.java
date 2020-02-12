package com.example.workout;

import java.io.Serializable;

public class NewWorkout implements Serializable {


    private String workoutName;
    private int seconds;

    public NewWorkout(String workoutName, int seconds) {
        this.workoutName = workoutName;
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    @Override
    public String toString() {

        return this.workoutName + "        "  + Integer.toString(this.seconds) + " sekuntia ";
    }
}
