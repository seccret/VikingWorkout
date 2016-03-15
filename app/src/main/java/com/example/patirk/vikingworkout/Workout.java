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
public class Workout {
    private int id;
    private String name;
    private int picture;
    public String tagtemplate;

    private List<Integer> exercises;
    public List<Integer> repetitions;

    public Workout(int id, String name, int picture, String tagtemplate, List<Integer> exercises, List<Integer> repetitions) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.exercises = exercises;
        this.tagtemplate = tagtemplate;
        if(repetitions.size()!=4){
            repetitions = new ArrayList<>();
            repetitions.add(0);
            repetitions.add(0);
            repetitions.add(0);
            repetitions.add(0);
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

    public int getPicture() {
        return picture;
    }

    public String getTagTemplate () {
        return tagtemplate;
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
