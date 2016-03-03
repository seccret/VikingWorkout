package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2015-10-27.
 */
public class FragmentTrainingPlan extends android.support.v4.app.Fragment {

    public static List<Weekday> weekdays = null;
    public static List<Workout> workouts = null;

    public static FragmentTrainingPlan newInstance() {
        FragmentTrainingPlan fragment = new FragmentTrainingPlan();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentTrainingPlan() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_trainingplan, container, false);


        final Button thisWeekButton = (Button) rootView.findViewById(R.id.bTogglePlanLeft);
        final Button calendarButton = (Button) rootView.findViewById(R.id.bTogglePlanMiddle);
        final Button statisticButton = (Button) rootView.findViewById(R.id.bTogglePlanRight);

        thisWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisWeekButton.setBackgroundResource(R.drawable.button_selected);
                thisWeekButton.setTextColor(getResources().getColor(R.color.halfTrans));
                calendarButton.setBackgroundResource(R.drawable.button_unselected);
                calendarButton.setTextColor(getResources().getColor(R.color.white));
                statisticButton.setBackgroundResource(R.drawable.button_unselected);
                statisticButton.setTextColor(getResources().getColor(R.color.white));
                ListView lv = (ListView) rootView.findViewById(R.id.lvThisWeek);
                AdapterImage3 AI = new AdapterImage3(rootView.getContext(), weekdays);
                lv.setAdapter(AI);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarButton.setBackgroundResource(R.drawable.button_selected);
                calendarButton.setTextColor(getResources().getColor(R.color.halfTrans));
                thisWeekButton.setBackgroundResource(R.drawable.button_unselected);
                thisWeekButton.setTextColor(getResources().getColor(R.color.white));
                statisticButton.setBackgroundResource(R.drawable.button_unselected);
                statisticButton.setTextColor(getResources().getColor(R.color.white));
                ListView lv = (ListView) rootView.findViewById(R.id.lvThisWeek);
                AdapterImage3 AI = new AdapterImage3(rootView.getContext(), weekdays);
                lv.setAdapter(AI);


            }
        });

        statisticButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                statisticButton.setBackgroundResource(R.drawable.button_selected);
                statisticButton.setTextColor(getResources().getColor(R.color.halfTrans));
                thisWeekButton.setBackgroundResource(R.drawable.button_unselected);
                thisWeekButton.setTextColor(getResources().getColor(R.color.white));
                calendarButton.setBackgroundResource(R.drawable.button_unselected);
                calendarButton.setTextColor(getResources().getColor(R.color.white));

                return false;
            }
        });

        List<Integer> e = new ArrayList<>();
        e.add(0);
        weekdays = new ArrayList<Weekday>();
        weekdays.add(new Weekday(0, "Monday", 1));
        weekdays.add(new Weekday(1, "Tuesday", 2));
        weekdays.add(new Weekday(2, "Wednesday", 3));
        weekdays.add(new Weekday(3, "Thursday", 1));
        weekdays.add(new Weekday(4, "Friday", 2));
        weekdays.add(new Weekday(5, "Saturday", 3));
        weekdays.add(new Weekday(6, "Sunday", 1));

        List<Integer> r = new ArrayList<>();
        e.add(0);
        e.add(1);
        e.add(2);
        e.add(3);
        workouts = new ArrayList<Workout>();
        workouts.add(new Workout(0, "Power Workout", 1,"Seven Workout", e, r));
        workouts.add(new Workout(1, "Core", 2,"Seven Workout", e, r));
        workouts.add(new Workout(2, "Arms", 3,"Seven Workout", e, r));
        workouts.add(new Workout(3, "Rest", 1,"Seven Workout", e, r));
        workouts.add(new Workout(4, "Cross-training", 2,"Seven Workout", e, r));
        workouts.add(new Workout(5, "Ultimate situps", 3,"Seven Workout", e, r));
        workouts.add(new Workout(6, "Rest", 1,"Seven Workout", e, r));

        ListView lv = (ListView) rootView.findViewById(R.id.lvThisWeek);
        registerForContextMenu(lv);
        AdapterImage3 AI = new AdapterImage3(rootView.getContext(), weekdays);
        lv.setAdapter(AI);


        return rootView;
    }

}