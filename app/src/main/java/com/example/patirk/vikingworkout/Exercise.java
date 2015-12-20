package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by olivia on 2015-09-07.
 */
public class Exercise {
    private long id;
    private String name;
    private Drawable picture;

    public Exercise(long id, String name, Drawable picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Drawable getPicture() {
        //Drawable drawable=new BitmapDrawable(picture);
        return picture;
    }

}
