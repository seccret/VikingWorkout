package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by olivia on 2015-10-26.
 */
public class FragmentCalendar extends android.support.v4.app.Fragment {

    public static FragmentCalendar newInstance() {
        FragmentCalendar fragment = new FragmentCalendar();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentCalendar() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView calendar = (CalendarView) rootView.findViewById(R.id.calendar);
        final Workout workout = MainActivity.currentWorkout;
        final int workoutId = workout.getId();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                String dateString = String.valueOf((i2+1)+ "/"+ (i1+1)+ "/"+i);
                DateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = dF.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long epoch = date.getTime();

                long dateIdLong = epoch/(24*60*60*1000);
                DecimalFormat rDFormat = new DecimalFormat("#");
                int dateId = Integer.valueOf(rDFormat.format(dateIdLong));

                ArrayList<Integer> workouts = new ArrayList<Integer>();
                workouts.add(workoutId);
                String sProfileID = String.valueOf(MainActivity.profile.getId());
                String sEventID = String.valueOf(MainActivity.profile.getMyEvents().size());
                int newEventId = Integer.valueOf(sProfileID + sEventID);
                Event newEvent = new Event(newEventId, workout, 0, "No");
                MainActivity.profile.addToMyEvents(newEvent);
                ArrayList<Integer> events = new ArrayList<Integer>();
                events.add(newEvent.getEventId());

                if (MainActivity.profile.containDay(dateId)){
                    MainActivity.profile.getDayByID(dateId).addEvent(newEventId);
                    Toast.makeText(MainActivity.mainActivity, workout.getName() + " workout added to agenda", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.profile.addToMyAgena(new Day(dateId, events));
                    Toast.makeText(MainActivity.mainActivity, workout.getName() + " workout added to agenda", Toast.LENGTH_SHORT).show();
                }
           }
        });

        return rootView;
    }

}