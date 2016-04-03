package com.example.patirk.vikingworkout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */
public class Month implements Serializable {
    private int month_id;

    public List<Integer> weeks;

    public Month(int month_id, List<Integer> weeks) {
        this.month_id = month_id;
        this.weeks = weeks;
    }

    public int getMonthId() {
        return month_id;
    }

    public List<Integer> getWeek() {
        return weeks;
    }

    public void addWeek(int i) {
        this.weeks.add(i);
    }

    public void setWeek(List<Integer> listWe) {
        this.weeks = new ArrayList<>();
        this.weeks = listWe;
    }
}
