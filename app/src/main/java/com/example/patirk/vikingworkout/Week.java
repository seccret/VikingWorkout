package com.example.patirk.vikingworkout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Week implements Serializable {
    private int week_id;

    public List<Integer> days;

    public Week(int week_id, List<Integer> days) {
        this.week_id = week_id;
        this.days = days;
    }

    public int getWeekId() {
        return week_id;
    }

    public List<Integer> getDay() {
        return days;
    }

    public void addDay(int i) {
        this.days.add(i);
    }

    public void setDay(List<Integer> listD) {
        this.days = new ArrayList<>();
        this.days = listD;
    }
}
