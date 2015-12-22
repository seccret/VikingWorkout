package com.example.patirk.vikingworkout;

import android.graphics.Movie;
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
import java.io.InputStream;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
        final ImageView gif = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
        TextView tips = (TextView) rootView.findViewById(R.id.tvWorkoutTips);
        ListView lvExercises = (ListView) rootView.findViewById(R.id.lvWorkoutList);
        Workout workout = MainActivity.currentWorkout;
        final List<Integer> exercises = workout.getExercises();

        if (workout.getPicture() == 1) {
            gif.setBackgroundResource(R.drawable.workout1);
        } else if (workout.getPicture() == 2) {
            gif.setBackgroundResource(R.drawable.workout2);
        } else if (workout.getPicture() == 3) {
            gif.setBackgroundResource(R.drawable.workout3);
        }

        tips.setText("This is an " + workout.getName() + " workout!");

        AdapterImage2 ai = new AdapterImage2(MainActivity.mainActivity, exercises);
        lvExercises.setAdapter(ai);

        return rootView;
    }
}
