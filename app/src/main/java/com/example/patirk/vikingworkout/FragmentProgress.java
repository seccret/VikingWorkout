package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Patirk on 03/09/2015.
 */

public class FragmentProgress extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns mainActivity new instance of this fragment for the given section
     * number.
     */
    public static FragmentProgress newInstance() {
        FragmentProgress fragment = new FragmentProgress();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentProgress() {
    }

    List<Integer> workoutList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_progress, container, false);
        final ImageView plus = (ImageView) rootView.findViewById(R.id.ivProgressPlus);
        final SlidingUpPanelLayout supl = (SlidingUpPanelLayout) rootView.findViewById(R.id.progress_sliding_layout);
        final CalendarView calendar = (CalendarView) rootView.findViewById(R.id.cvProgress);
        final ListView lvagenda = (ListView) rootView.findViewById(R.id.lvProgress);
        final TextView tvdate = (TextView) rootView.findViewById(R.id.tvProgressDate);
        final Button toggleCalendar = (Button) rootView.findViewById(R.id.bProgressToggleL);
        final Button toggleProgress = (Button) rootView.findViewById(R.id.bProgressToggleR);
        final SlidingUpPanelLayout slidingUpPanel = (SlidingUpPanelLayout) rootView.findViewById(R.id.progress_sliding_layout);


        final SlidingUpPanelLayout.PanelState wut = supl.getPanelState();
        final String wut2 = wut.toString();

        toggleCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCalendar.setBackgroundResource(R.drawable.button_selected);
                toggleProgress.setBackgroundResource(R.drawable.button_unselected);
                toggleCalendar.setTextColor(Color.parseColor("#fba500"));
                toggleProgress.setTextColor(Color.parseColor("#c1c1c1"));
                slidingUpPanel.setVisibility(View.VISIBLE);

                long epoch = System.currentTimeMillis();
                long dateIdLong = epoch/(24*60*60*1000);
                DecimalFormat rDFormat = new DecimalFormat("#");
                int dateId = Integer.valueOf(rDFormat.format(dateIdLong));
                workoutList =  MainActivity.profile.getDayByID(dateId).getWorkouts();
                AdapterProfileWorkout ap = new AdapterProfileWorkout(rootView.getContext(), workoutList);
                lvagenda.setAdapter(ap);
            }
        });

        toggleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleProgress.setBackgroundResource(R.drawable.button_selected);
                toggleCalendar.setBackgroundResource(R.drawable.button_unselected);
                toggleProgress.setTextColor(Color.parseColor("#fba500"));
                toggleCalendar.setTextColor(Color.parseColor("#c1c1c1"));
                slidingUpPanel.setVisibility(View.GONE);
            }
        });

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

                MainActivity.currentDay = dateId;

                Date today = new Date(((long) dateId *24*60*60*1000));
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                String text = format.format(today);
                tvdate.setText(text);

                workoutList =  MainActivity.profile.getDayByID(dateId).getWorkouts();

                AdapterProfileWorkout AI = new AdapterProfileWorkout(rootView.getContext(), workoutList);
                lvagenda.setAdapter(AI);

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}
