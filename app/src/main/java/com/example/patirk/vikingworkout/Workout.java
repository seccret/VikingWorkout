package com.example.patirk.vikingworkout;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Workout {
    private long id;
    private String name;
    private int picture;

    public Workout(long id, String name, int picture) {
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

    public int getPicture() {
        return picture;
    }

}
