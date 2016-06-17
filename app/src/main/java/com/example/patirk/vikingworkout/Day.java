package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import org.mortbay.jetty.Main;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Day implements Serializable {
    private int date_id;
    public List<Integer> events;

    public Day(int date_id, List<Integer> events) {
        this.date_id = date_id;
        this.events = events;
    }

    public int getDateId() {
        return date_id;
    }

    public List<Integer> getEvents() {
        return events;
    }

    public void addEvent(Integer i) {
        this.events.add(i);
    }

    public void removeEvent(int wID) {
        events.remove((Object)wID);
        MainActivity.saveProfile(MainActivity.mainActivity);
    }

    public void setEvents(List<Integer> listW) {
        this.events = new ArrayList<>();
        this.events = listW;
    }

}
