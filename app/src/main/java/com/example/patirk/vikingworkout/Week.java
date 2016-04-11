package com.example.patirk.vikingworkout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Week implements Serializable {
    private int week_id;

    public List<Day> days;

    public Week(int week_id, List<Day> days) {
        this.week_id = week_id;
        this.days = days;
    }

    public int getWeekId() {
        return week_id;
    }

    public List<Day> getDays() {
        return days;
    }

    public void addDay(Day d) {
        this.days.add(d);
    }

    public void setDay(List<Day> listD) {
        this.days = new ArrayList<>();
        this.days = listD;
    }
}
