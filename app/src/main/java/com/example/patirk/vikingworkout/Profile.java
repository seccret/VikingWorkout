package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Patirk on 23/10/2015.
 */
public class Profile {
    private long id;
    private String name;
    private Bitmap picture;

    public Profile(long id, String name) {
        this.id = id;
        this.name = name;
        this.picture = null;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Drawable getPicture() {
        Drawable drawable=new BitmapDrawable(picture);
        return drawable;
    }

    public void setName(String newName){
        this.name=newName;
    }

    public void setProfilePicture(Bitmap newPic){
        this.picture = newPic;
        //ExernalFunctions.saveToInternalStorage(newPic);
    }

}
