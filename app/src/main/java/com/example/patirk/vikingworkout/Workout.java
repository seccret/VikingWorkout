package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Workout {
    private long id;
    private String name;
    private int picture;

    private List<Exercise> exercises;

    public Workout(long id, String name, int picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        Drawable img1 = (Drawable) MainActivity.mainActivity.getDrawable(R.mipmap.image1);
        Drawable img2 = (Drawable) MainActivity.mainActivity.getDrawable(R.mipmap.image2);
        Drawable img3 = (Drawable) MainActivity.mainActivity.getDrawable(R.mipmap.image3);

        Exercise exercise1 = new Exercise(0, "Sit Ups", img1);
        Exercise exercise2 = new Exercise(1, "Push Up", img2);
        Exercise exercise3 = new Exercise(2, "Pull up", img3);
        exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    public void setExercises(List<Exercise> listE) {
        exercises = listE;
    }

}
