package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.webkit.WebView;

import java.io.InputStream;

/**
 * Created by olivia on 2015-09-07.
 */
public class Exercise {
    private int id;
    private String name;
    private Drawable picture;
    private Movie gifMovie;
    private String desc;

    public Exercise(int id, String name, Drawable picture, Movie gif, String desc) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.gifMovie = gif;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
