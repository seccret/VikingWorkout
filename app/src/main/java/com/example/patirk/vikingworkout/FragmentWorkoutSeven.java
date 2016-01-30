package com.example.patirk.vikingworkout;

import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by olivia on 2015-10-24.
 */
public class FragmentWorkoutSeven extends android.support.v4.app.Fragment {

    public static FragmentWorkoutSeven newInstance() {
        FragmentWorkoutSeven fragment = new FragmentWorkoutSeven();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentWorkoutSeven() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
        final TextView tvTime = (TextView) rootView.findViewById(R.id.tvSevenTime);
        final GridView lvExercises = (GridView) rootView.findViewById(R.id.gvSevenList);
        final ImageView play = (ImageView) rootView.findViewById(R.id.ivSevenPlay);
        final TextView woname = (TextView) rootView.findViewById(R.id.tvSevenName);
        final RelativeLayout rlplay = (RelativeLayout) rootView.findViewById(R.id.rlSevenPlay);
        final LinearLayout pausestop = (LinearLayout) rootView.findViewById(R.id.llSevenPlay);
        final RelativeLayout banner = (RelativeLayout) rootView.findViewById(R.id.rlWorkoutSeven);
        final Workout workout = MainActivity.currentWorkout;
        final List<Integer> exercises = workout.getExercises();


        woname.setText(workout.getName());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlplay.setVisibility(View.VISIBLE);
                pausestop.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
                banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.5f));
                new CountDownTimer(420000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tvTime.setText("" + String.format("%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
                        tvTime.setText("done!");
                            rlplay.setVisibility(View.GONE);
                            pausestop.setVisibility(View.GONE);
                            play.setVisibility(View.VISIBLE);
                            banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
                    }
                }.start();
            }
        });

        AdapterImage2 ai = new AdapterImage2(MainActivity.mainActivity, exercises);
        lvExercises.setAdapter(ai);

        return rootView;
    }
}
