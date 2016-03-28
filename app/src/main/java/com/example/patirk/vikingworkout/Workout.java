package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Workout implements Serializable {
    private int id;
    private String name;
    private int picture;

    public List<Integer> blocks;
    private String musclegroup;
    private String madeby;

    public Workout(int id, String name, int picture, List<Integer> blocks, String musclegroup, String madeby) {
        this.id = id;
        this.name = name;
        this.blocks = blocks;
        this.picture = picture;
        this.musclegroup = musclegroup;
        this.madeby = madeby;
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

    public List<Integer> getBlocks() {
        return blocks;
    }

    public String getMuscleGroup() {
        return musclegroup;
    }

    public String getMadeBy() {
        return madeby;
    }

    public void addBlock(int i) {
        this.blocks.add(i);
    }

    public void setBlocks(List<Integer> listE) {
        this.blocks = new ArrayList<>();
        this.blocks = listE;
    }
}
