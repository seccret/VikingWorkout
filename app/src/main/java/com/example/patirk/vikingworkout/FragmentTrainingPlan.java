package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2015-10-27.
 */
public class FragmentTrainingPlan extends android.support.v4.app.Fragment {

    public static List<Weekday> weekdays = null;
    public static List<Block> blocks = null;

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
                AdapterTrainingPlan AI = new AdapterTrainingPlan(rootView.getContext(), weekdays);
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
                AdapterTrainingPlan AI = new AdapterTrainingPlan(rootView.getContext(), weekdays);
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
        blocks = new ArrayList<Block>();
        blocks.add(new Block(0, "Power Block", e, r));
        blocks.add(new Block(1, "Core", e, r));
        blocks.add(new Block(2, "Arms", e, r));
        blocks.add(new Block(3, "Rest", e, r));
        blocks.add(new Block(4, "Cross-training", e, r));
        blocks.add(new Block(5, "Ultimate situps", e, r));
        blocks.add(new Block(6, "Rest", e, r));

        ListView lv = (ListView) rootView.findViewById(R.id.lvThisWeek);
        registerForContextMenu(lv);
        AdapterTrainingPlan AI = new AdapterTrainingPlan(rootView.getContext(), weekdays);
        lv.setAdapter(AI);


        return rootView;
    }

}