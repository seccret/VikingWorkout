package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.webkit.WebView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by olivia on 2015-09-07.
 */
public class Exercise {
    private int id;
    private String name;
    private Drawable picture;
    private Movie gifMovie;
    private String desc;

    //"Arms", "Shoulders", "Chest", "Back", "Abs", "Butt", "Legs"
    //--Upper Body
    // "Arms" + "Shoulders" = "Upper body", "Arms" + "Chest" = "Upper body", "Arms" + "Back" = "Upper body", "Arms" + "Abs" = "Upper body",
    // "Shoulders" + "Chest" = "Upper body", "Shoulders" + "Back" = "Upper body", "Shoulders" + "Abs" = "Upper body",
    // "Chest" + "Back" = "Upper body", "Chest" + "Abs" = "Upper body",
    // "Back" + "Abs" = "Upper body",
    //--Lower Body
    // "Butt" + "Legs" = "Lower body",
    //Cross
    // "Arms" + "Butt" = "Cross", "Arms" + "Legs" = "Cross",
    // "Shoulders" + "Butt" = "Cross", "Shoulders" + "Legs" = "Cross",
    // "Chest" + "Butt" = "Cross", "Chest" + "Legs" = "Cross",
    // "Back" + "Butt" = "Cross", "Back" + "Legs" = "Cross",
    // "Abs" + "Butt" = "Cross", "Abs" + "Legs" = "Cross",
    private List<String> muscle;


    public Exercise(int id, String name, String desc, List<String> muscle, Drawable picture, Movie gif) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.gifMovie = gif;
        this.desc = desc;
        this.muscle = muscle;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getMuscle() {
        return muscle;
    }

    public Drawable getPicture() {
        //Drawable drawable=new BitmapDrawable(picture);
        return picture;
    }

    public Movie getGif(){
        return gifMovie;
    }

    public String getDesc() {
        return desc;
    }
}
