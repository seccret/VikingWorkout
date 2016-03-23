package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Block implements Serializable {
    private int id;
    private String name;

    private List<Integer> exercises;
    public List<Integer> repetitions;
    private String muscleGroup;

    public Block(int id, String name, List<Integer> exercises, List<Integer> repetitions) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
        this.muscleGroup = ExternalFunctions.findMuscleGroup(exercises);
        Toast.makeText(MainActivity.mainActivity, "Muskelgrupp = " + muscleGroup, Toast.LENGTH_SHORT).show();
        if(repetitions.size()!=4){
            this.repetitions = new ArrayList<>(4);
            this.repetitions.add(0);
            this.repetitions.add(0);
            this.repetitions.add(0);
            this.repetitions.add(0);
        }else{
            this.repetitions = repetitions;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public List<Integer> getRepetitions() {
        return repetitions;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void addExercise(int i) {
        this.exercises.add(i);
    }

    public void setExercises(List<Integer> listE) {
        this.exercises = new ArrayList<>();
        this.exercises = listE;
    }
    public void setRepetition(int index, int repetitions) {
        this.repetitions.set(index, repetitions);
    }
}
