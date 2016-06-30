package com.example.patirk.vikingworkout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2015-09-07.
 */
public class Category {
    private int id;
    private String name;
    private int picture;
    private List<Workout> workouts;

    public Category(int id, String name, int picture, List<Workout> workouts) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        if(workouts==null){
            this.workouts = new ArrayList<>();
        }else{
            this.workouts = workouts;
        }
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

    public List<Workout> getWorkouts() {
        return workouts;
    }

}
