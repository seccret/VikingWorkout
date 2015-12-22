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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Patirk on 23/10/2015.
 */
public class Profile {
    private long id;
    private String name;
    private Bitmap picture;
    private List<Integer> myWorkout;

    public Profile(long id, String name, List<Integer> myWorkout) {
        this.id = id;
        this.name = name;
        this.picture = null;
        if(myWorkout==null){
            this.myWorkout = new ArrayList<>();
        }else{
            this.myWorkout = myWorkout;
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Drawable getPicture() {
        Drawable drawable = new BitmapDrawable(picture);
        return drawable;
    }

    public List getMyWorkouts(){
        return myWorkout;
    }

    public void addWorkout(int wID){
        myWorkout.add(wID);
    }

    public void removeWorkout(int wID){
        boolean remove = myWorkout.remove((Object)wID);
    }

    public boolean contains(int wID){
        boolean exist = myWorkout.contains(wID);
        return exist;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setProfilePicture(Bitmap newPic) {
        this.picture = newPic;
        //ExernalFunctions.saveToInternalStorage(newPic);
    }

}
