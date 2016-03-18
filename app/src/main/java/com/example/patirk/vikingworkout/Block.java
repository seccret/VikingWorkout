package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Block {
    private int id;
    private String name;

    private List<Integer> exercises;
    public List<Integer> repetitions;

    public Block(int id, String name, List<Integer> exercises, List<Integer> repetitions) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
        if(repetitions.size()!=4){
            this.repetitions = new ArrayList<>();
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
