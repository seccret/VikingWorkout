package com.example.patirk.vikingworkout;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Workout {
    private long id;
    private String name;

    public Workout(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
