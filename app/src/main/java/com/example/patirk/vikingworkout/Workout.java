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
    private String tagtemplate;
    private int picture;

    public List<Integer> blocks;

    public Workout(int id, String name, String tagtemplate, int picture, List<Integer> blocks) {
        this.id = id;
        this.name = name;
        this.tagtemplate = tagtemplate;
        this.blocks = blocks;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagTemplate () {
        return tagtemplate;
    }

    public int getPicture() {
        return picture;
    }

    public List<Integer> getBlocks() {
        return blocks;
    }

    public void addBlock(int i) {
        this.blocks.add(i);
    }

    public void setBlocks(List<Integer> listE) {
        this.blocks = new ArrayList<>();
        this.blocks = listE;
    }
}
