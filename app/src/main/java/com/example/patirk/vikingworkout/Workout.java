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

    private List<Integer> exercises;

    public Workout(int id, String name, int picture, List<Integer> exercises) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.exercises = exercises;
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

}
