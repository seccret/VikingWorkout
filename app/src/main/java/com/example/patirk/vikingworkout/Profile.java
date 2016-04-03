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
    private List<Block> myBlocks;

    public Profile(long id, String name,String desc, List<Workout> myWorkout)  {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.picture = null;
        this.myBlocks = new ArrayList<>();
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
        if(picture != null) {
            Drawable drawable = new BitmapDrawable(picture);
            return drawable;
        }
        return null;
    }
    public Bitmap getPictureAsBitmap() {
        return picture;
    }

    public List<Workout> getMyWorkouts(){
        return myWorkout;
    }

    public List<Block> getMyBlocks(){
        return myBlocks;
    }

    public Workout getWorkoutByID(int wID){
        for(Workout wo : myWorkout){
            if(wo.getId() == wID){
                return wo;
            }
        }
        return myWorkout.get(0);
    }
    public Block getBlockByID(int bID){
        for(Block bo : myBlocks){
            if(bo.getId() == bID){
                return bo;
            }
        }
        return myBlocks.get(0);
    }

    public void addWorkout(Workout wID){
        myWorkout.add(wID);
        MainActivity.saveProfile(MainActivity.mainActivity);
    }
    public void addBlock(Block bID){
        myBlocks.add(bID);
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public void setWorkout(List<Workout> wIDList){
        myWorkout = wIDList;
    }

    public void removeWorkout(int wID){
        Workout remove=null;
        for(Workout wo : myWorkout){
            if(wo.getId() == wID){
                remove = wo;
            }
        }
        if(remove!=null){
            myWorkout.remove(remove);
        }
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public void removeBlock(int bID){
        Block remove=null;
        for(Block bo : myBlocks){
            if(bo.getId() == bID){
                remove = bo;
            }
        }
        if(remove!=null){
            myWorkout.remove(remove);
        }
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public void clearProfile(){
        List<Workout> w = new ArrayList<>();
        List<Block> b = new ArrayList<>();
        this.myWorkout = w;
        this.myBlocks = b;
    }

    public boolean containWorkout(int wID){
        for(Workout wo : myWorkout){
            if(wo.getId() == wID){
                return true;
            }
        }
        return false;
    }

    public boolean containBlock(int wID){
        for(Workout wo : myWorkout){
            if(wo.getId() == wID){
                return true;
            }
        }
        return false;
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
