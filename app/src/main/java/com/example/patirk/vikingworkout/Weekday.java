package com.example.patirk.vikingworkout;

/**
 * Created by olivia on 2015-09-07.
 */
public class Weekday {
    private long id;
    private String name;
    private int picture;
    private Workout workout;

    public Weekday(long id, String name, int picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        workout = new Workout(0, "",0);
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

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout newWorkout) {
        newWorkout.getId();
        newWorkout.getName();
        workout = new Workout(newWorkout.getId(), newWorkout.getName(), newWorkout.getPicture());
        workout.setExercises(newWorkout.getExercises());
    }
}