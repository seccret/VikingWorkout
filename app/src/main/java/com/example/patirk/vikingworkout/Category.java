package com.example.patirk.vikingworkout;

/**
 * Created by olivia on 2015-09-07.
 */
public class Category {
    private long id;
    private String name;
    private int picture;

    public Category(long id, String name, int picture) {
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
