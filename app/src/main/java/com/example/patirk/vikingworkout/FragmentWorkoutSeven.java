package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by olivia on 2015-10-24.
 */
public class FragmentWorkoutSeven extends android.support.v4.app.Fragment {
    public static int test = 0;
    long timeWhenStopped = 0;
    long startTime;
    public static FragmentWorkoutSeven newInstance() {
        FragmentWorkoutSeven fragment = new FragmentWorkoutSeven();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentWorkoutSeven() {
    }
    public static ViewPager viewPager;

    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;
    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_workout_seven, container, false);

        final LinearLayout llTime = (LinearLayout) rootView.findViewById(R.id.llSevenTime);
        final LinearLayout pausestop = (LinearLayout) rootView.findViewById(R.id.llSevenPlay);
        final ImageView play = (ImageView) rootView.findViewById(R.id.ivSevenBanner);
        final RelativeLayout banner = (RelativeLayout) rootView.findViewById(R.id.rlWorkoutSeven);
        final TextView tvTime = (TextView) rootView.findViewById(R.id.tvSevenTime);
        final TextView woname = (TextView) rootView.findViewById(R.id.tvSevenName);
        final TextView pause = (TextView) rootView.findViewById(R.id.tvSevenPause);
        final TextView cancel = (TextView) rootView.findViewById(R.id.tvsevenCancel);
        final TextView resume = (TextView) rootView.findViewById(R.id.tvsevenResume);
        final TextView musclegroup = (TextView) rootView.findViewById(R.id.tvSevenMuscleGroup);
        final TextView madeby = (TextView) rootView.findViewById(R.id.tvSevenMadeBy);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        final Workout workout = MainActivity.currentWorkout;
        final List<Integer> blocks = workout.getBlocks();
        final Adapter4BlockPager a = new Adapter4BlockPager(MainActivity.mainActivity, blocks);
        final Chronometer stopWatch = (Chronometer) rootView.findViewById(R.id.chrono);


        woname.setText(workout.getName());
        musclegroup.setText(workout.getMuscleGroup());
        madeby.setText("Made By:" + workout.getMadeBy());

        play.setOnClickListener(new View.OnClickListener() {
            int currentBlock = 0;

            @Override
            public void onClick(View v) {
                llTime.setVisibility(View.VISIBLE);
                pausestop.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                stopWatch.setBase(SystemClock.elapsedRealtime());

                stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer arg0) {
                        long time = SystemClock.elapsedRealtime() - arg0.getBase();
                        int h = (int) (time / 3600000);
                        int m = (int) (time - h * 3600000) / 60000;
                        int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                        String mm = m < 10 ? "0" + m : m + "";
                        String ss = s < 10 ? "0" + s : s + "";
                        tvTime.setText(mm + ":" + ss);
                    }
                });
                stopWatch.start();
            }

        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = stopWatch.getBase() - SystemClock.elapsedRealtime();
                stopWatch.stop();
                pause.setVisibility(View.GONE);
                resume.setVisibility(View.VISIBLE);
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopWatch.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                stopWatch.start();
                pause.setVisibility(View.VISIBLE);
                resume.setVisibility(View.GONE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopWatch.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                Toast.makeText(getActivity(), "Workout canceled", Toast.LENGTH_SHORT).show();
                llTime.setVisibility(View.GONE);
                pausestop.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                viewPager.setAdapter(a);
                
            }
        });

        viewPager.setAdapter(a);

        return rootView;
    }

public void setTest(int i){
            this.test=i;
        }
}
