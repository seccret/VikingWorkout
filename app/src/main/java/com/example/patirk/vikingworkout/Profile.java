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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Patirk on 23/10/2015.
 */
public class Profile implements Serializable{
    private long id;
    private String name;
    private String desc;
    private transient Bitmap picture;
    private List<Workout> myWorkout;

    public Profile(long id, String name,String desc, List<Workout> myWorkout) {
        this.id = id;
        this.name = name;
        this.desc = desc;
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

    public String getDesc () {
        return desc;
    }

    public Drawable getPicture() {
        Drawable drawable = new BitmapDrawable(picture);
        return drawable;
    }
    public Bitmap getPictureAsBitmap() {
        return picture;
    }
    public List<Workout> getMyWorkouts(){
        return myWorkout;
    }

    public void addWorkout(Workout wID){
        myWorkout.add(wID);
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public void setWorkout(List<Workout> wIDList){
        myWorkout = wIDList;
    }

    public void removeWorkout(int wID){
        myWorkout.remove((Object)wID);
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public boolean contains(int wID){
        boolean exist = myWorkout.contains(wID);
        return exist;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setDesc (String newDesc) {
        this.desc = newDesc;
    }

    public void setProfilePicture(Bitmap newPic) {
        this.picture = newPic;
        //ExernalFunctions.saveToInternalStorage(newPic);
    }

}
