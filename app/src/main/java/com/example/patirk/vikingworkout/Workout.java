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

    private List<Exercise> exercises;

    public Workout(int id, String name, int picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;

        exercises = new ArrayList<>();
        InputStream gifInputStream;
        for(int i = 0; i<4; i++){
            Exercise exercise =null;
            if(i==0){
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.commandos);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream,"img");
                exercise = new Exercise(0, "Commandos", img, gif);
            }else if(i==1){
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.pushups);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream,"img");
                exercise = new Exercise(1, "Push Up", img, gif);
            }else if(i==2){
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.situps);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream,"img");
                exercise = new Exercise(2, "Sit Up", img, gif);
            }else if(i==3){
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.running);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream,"img");
                exercise = new Exercise(2, "Runner", img, gif);
            }
            exercises.add(exercise);
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
