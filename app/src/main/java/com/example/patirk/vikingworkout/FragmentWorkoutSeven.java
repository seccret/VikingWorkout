package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
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
    TextView textGoesHere;
    long startTime;
    long countUp;
    public static FragmentWorkoutSeven newInstance() {
        FragmentWorkoutSeven fragment = new FragmentWorkoutSeven();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentWorkoutSeven() {
    }

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

        final ListView lvExercises = (ListView) rootView.findViewById(R.id.lvSevenList);
        final LinearLayout llTime = (LinearLayout) rootView.findViewById(R.id.llSevenTime);
        final LinearLayout pausestop = (LinearLayout) rootView.findViewById(R.id.llSevenPlay);
        final ImageView play = (ImageView) rootView.findViewById(R.id.ivSevenBanner);
        final RelativeLayout banner = (RelativeLayout) rootView.findViewById(R.id.rlWorkoutSeven);
        final TextView tvTime = (TextView) rootView.findViewById(R.id.tvSevenTime);
        final TextView woname = (TextView) rootView.findViewById(R.id.tvSevenName);
        final TextView pause = (TextView) rootView.findViewById(R.id.tvSevenPause);
        final TextView cancel = (TextView) rootView.findViewById(R.id.tvsevenCancel);
        final TextView resume = (TextView) rootView.findViewById(R.id.tvsevenResume);
        final TextView playnext = (TextView) rootView.findViewById(R.id.tvsevenPlayNext);
        final TextView musclegroup = (TextView) rootView.findViewById(R.id.tvSevenMuscleGroup);
        final TextView madeby = (TextView) rootView.findViewById(R.id.tvSevenMadeBy);
        final Workout workout = MainActivity.currentWorkout;
        final List<Integer> blocks = workout.getBlocks();
        final Adapter4InceptionBase a = new Adapter4InceptionBase(MainActivity.mainActivity, blocks);
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
                Adapter4WorkoutSeven ai = new Adapter4WorkoutSeven(MainActivity.mainActivity, MainActivity.getBlockByID(blocks.get(currentBlock)));
                lvExercises.setAdapter(ai);

                startTime = SystemClock.elapsedRealtime();

                textGoesHere = (TextView) rootView.findViewById(R.id.tvSevenTime);
                stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer arg0) {
                        long time = SystemClock.elapsedRealtime() - arg0.getBase();
                        int h   = (int)(time /3600000);
                        int m = (int)(time - h*3600000)/60000;
                        int s= (int)(time - h*3600000- m*60000)/1000 ;
                        String mm = m < 10 ? "0"+m: m+"";
                        String ss = s < 10 ? "0"+s: s+"";
                        countUp = (SystemClock.elapsedRealtime() - arg0.getBase()) / 1000;
                        String asText = (countUp / 60) + ":" + (countUp % 60);
                        textGoesHere.setText(mm+":"+ss);
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
                lvExercises.setAdapter(a);
                
            }
        });

        playnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playnext.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                tvTime.setTextSize(110);
                CountDownTimer timer;
                long millisInFuture = 10000; //30 seconds
                long countDownInterval = 1000; //1 second
                new CountDownTimer(millisInFuture, countDownInterval) {
                    public void onTick(long millisUntilFinished) {
                        if (isPaused || isCanceled) {
                            //If the user request to cancel or paused the
                            //CountDownTimer we will cancel the current instance
                            cancel();
                        } else {
                            tvTime.setText("" + String.format("%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                            //Put count down timer remaining time in a variable
                            timeRemaining = millisUntilFinished;
                        }
                    }

                    public void onFinish() {
                        if (test < blocks.size() - 1) {
                            ++test;
                            Adapter4WorkoutSeven ai = new Adapter4WorkoutSeven(MainActivity.mainActivity, MainActivity.getBlockByID(blocks.get(test)));
                            lvExercises.setAdapter(ai);
                            playnext.setVisibility(View.VISIBLE);
                            pause.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            tvTime.setTextSize(50);
                            tvTime.setText("BLOCK "+test+"/"+blocks.size()+"\n COMPLETED!");
                        } else {
                            llTime.setVisibility(View.GONE);
                            pausestop.setVisibility(View.GONE);
                            banner.setVisibility(View.VISIBLE);
                            lvExercises.setAdapter(a);
                        }
                    }
                }.start();
            }
        });

        lvExercises.setAdapter(a);

        return rootView;
    }

public void setTest(int i){
            this.test=i;
        }
}
