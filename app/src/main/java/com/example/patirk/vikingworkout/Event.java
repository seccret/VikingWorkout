package com.example.patirk.vikingworkout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Event implements Serializable {
    private int event_id;
    public Workout workout;
    public long time;
    public String performed;

    public Event(int event_id, Workout workout, long time, String performed) {
        this.event_id = event_id;
        this.workout = workout;
        this.time = time;
        this.performed = performed;
    }

    public int getEventId() {
        return event_id;
    }

    public Workout getEventWorkout() {
        return workout;
    }

    public long getTime() {
        return time;
    }

    public String getPerformed() {
        return performed;
    }

    public void setEventWorkout(Workout w) {
        this.workout = w;
    }

    public void setTime(long t) {
        this.time = t;
    }

    public void setPerformed(String p) {
        this.performed = p;
    }

    public boolean containWorkout(Workout w){
            if(workout == w){
                return true;
            }
        return false;
    }
}
