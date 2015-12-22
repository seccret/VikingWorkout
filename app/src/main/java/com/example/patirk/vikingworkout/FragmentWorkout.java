package com.example.patirk.vikingworkout;

import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
        final ImageView workoutImage = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
       // final LinearLayout time = (LinearLayout) rootView.findViewById(R.id.llWorkoutTime);
        final TextView tvTime = (TextView) rootView.findViewById(R.id.tvWorkoutTime);
        final GifView gif = (GifView) rootView.findViewById(R.id.gif_view);
        final ImageView image = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
        final TextView tips = (TextView) rootView.findViewById(R.id.tvWorkoutTips);
        final ImageView play = (ImageView) rootView.findViewById(R.id.ivWorkoutPlay);
        final ListView lvExercises = (ListView) rootView.findViewById(R.id.lvWorkoutList);
        final Workout workout = MainActivity.currentWorkout;
        final List<Integer> exercises = workout.getExercises();

        if (workout.getPicture() == 1) {
            workoutImage.setBackgroundResource(R.drawable.workout1);
        } else if (workout.getPicture() == 2) {
            workoutImage.setBackgroundResource(R.drawable.workout2);
        } else if (workout.getPicture() == 3) {
            workoutImage.setBackgroundResource(R.drawable.workout3);
        }

        tips.setText("This is an " + workout.getName() + " workout!");

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    time.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.VISIBLE);
                gif.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
                image.setVisibility(View.GONE);
                MainActivity.activeWorkoutCounter=0;
                final Workout w = MainActivity.currentWorkout;
                Exercise e = MainActivity.exerciseList.get(w.getExercises().get(MainActivity.activeWorkoutCounter));
                gif.setGifMovie(e.getGif());
                lvExercises.setSelection(MainActivity.activeWorkoutCounter);
                new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tvTime.setText("" + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        tvTime.setText("done!");
                        ++MainActivity.activeWorkoutCounter;
                        lvExercises.setSelection(MainActivity.activeWorkoutCounter);
                        if(MainActivity.activeWorkoutCounter < MainActivity.currentWorkout.getExercises().size()){
                            Exercise e = MainActivity.exerciseList.get(w.getExercises().get(MainActivity.activeWorkoutCounter));
                            gif.setGifMovie(e.getGif());
                            this.start();
                        }else{
                            MainActivity.activeWorkoutCounter=0;
                            tvTime.setVisibility(View.GONE);
                            gif.setVisibility(View.GONE);
                            play.setVisibility(View.VISIBLE);
                            image.setVisibility(View.VISIBLE);
                        }
                    }
                }.start();
            }
        });

        AdapterImage2 ai = new AdapterImage2(MainActivity.mainActivity, exercises);
        lvExercises.setAdapter(ai);

        return rootView;
    }
}
