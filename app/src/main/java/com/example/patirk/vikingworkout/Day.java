package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import org.mortbay.jetty.Main;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Day implements Serializable {
    private int date_id;

    public List<Integer> workouts;

    public Day(int date_id, List<Integer> workouts) {
        this.date_id = date_id;
        this.workouts = workouts;
    }

    public int getDateId() {
        return date_id;
    }

    public List<Integer> getWorkouts() {
        return workouts;
    }

    public void addWorkouts(int i) {
        this.workouts.add(i);
    }

    public void removeWorkouts(int wID) {
        workouts.remove((Object)wID);
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public void setWorkouts(List<Integer> listW) {
        this.workouts = new ArrayList<>();
        this.workouts = listW;
    }
}
