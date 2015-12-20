package com.example.patirk.vikingworkout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by olivia on 2015-10-24.
 */
public class FragmentWorkout extends android.support.v4.app.Fragment {

    public static FragmentWorkout newInstance() {
        FragmentWorkout fragment = new FragmentWorkout();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentWorkout() {
    }

    private Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
        final ImageView gif = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
        TextView tips = (TextView) rootView.findViewById(R.id.tvWorkoutTips);
        ListView lvExercises = (ListView) rootView.findViewById(R.id.lvWorkoutList);
        Workout workout = MainActivity.currentWorkout;
        final List<Exercise> exercises = workout.getExercises();

        if (workout.getPicture() == 1) {
            gif.setBackgroundResource(R.mipmap.image1);
        } else if (workout.getPicture() == 2) {
            gif.setBackgroundResource(R.mipmap.image2);
        } else if (workout.getPicture() == 3) {
            gif.setBackgroundResource(R.mipmap.image3);
        }

   /*     gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < exercises.size(); i++) {
                    try {
                        Thread.sleep(2000);
                        gif.setBackground(exercises.get(i).getPicture());
                    } catch (InterruptedException e) {

                    }

                }

            }
        });
*/


        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gif.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < exercises.size(); i++) {
                            try {
                                gif.setBackground(exercises.get(i).getPicture());
                            } finally {

                            }

                        }
                    }
                }, 1000);
            }
        });


        tips.setText("This is an " + workout.getName() + " workout!");

        AdapterImage2 ai = new AdapterImage2(MainActivity.mainActivity, workout.getExercises());
        lvExercises.setAdapter(ai);

        return rootView;
    }
}
