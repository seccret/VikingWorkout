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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        final TextView tvTime = (TextView) rootView.findViewById(R.id.tvWorkoutTime);
        final GifView gif = (GifView) rootView.findViewById(R.id.gif_view);
        final ImageView image = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
        final RelativeLayout rlName = (RelativeLayout) rootView.findViewById(R.id.rlWorkoutName);
        final TextView woname = (TextView) rootView.findViewById(R.id.tvWorkoutName);
        final RelativeLayout rlplayimage = (RelativeLayout) rootView.findViewById(R.id.rlWorkoutPlay);
        final LinearLayout llplay = (LinearLayout) rootView.findViewById(R.id.llWorkoutPlay);
        final TextView woby = (TextView) rootView.findViewById(R.id.tvWorkoutBy);
        final ImageView play = (ImageView) rootView.findViewById(R.id.ivWorkoutPlay);
        final TextView next = (TextView) rootView.findViewById(R.id.tvWorkoutExercise);
        final RelativeLayout rlplay = (RelativeLayout) rootView.findViewById(R.id.rlWorkout);
        final ListView lvExercises = (ListView) rootView.findViewById(R.id.lvWorkoutList);
        final RelativeLayout rvExercises = (RelativeLayout) rootView.findViewById(R.id.rvWorkoutList);
        final TextView pause = (TextView) rootView.findViewById(R.id.tvWorkoutPause);
        final TextView stop = (TextView) rootView.findViewById(R.id.tvWorkoutStop);
        final Workout workout = MainActivity.currentWorkout;
        final List<Integer> exercises = workout.getExercises();
        final Exercise exercise = MainActivity.currentExercise;
        final ProgressBar progressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        final int[] i = {0};
        progressbar.setProgress(i[0]);

        if (workout.getPicture() == 1) {
            workoutImage.setBackgroundResource(R.drawable.workout1);
        } else if (workout.getPicture() == 2) {
            workoutImage.setBackgroundResource(R.drawable.workout2);
        } else if (workout.getPicture() == 3) {
            workoutImage.setBackgroundResource(R.drawable.workout3);
        }

        woname.setText(workout.getName());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlplayimage.setVisibility(View.VISIBLE);
                llplay.setVisibility(View.VISIBLE);
                rlplay.setVisibility(View.GONE);
                rlName.setVisibility(View.GONE);
                rvExercises.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.6f));
                MainActivity.activeWorkoutCounter = 0;
                final Workout w = MainActivity.currentWorkout;
                Exercise e = MainActivity.exerciseList.get(w.getExercises().get(MainActivity.activeWorkoutCounter));
                gif.setGifMovie(e.getGif());
                next.setText("Next up:");
                lvExercises.setSelection(MainActivity.activeWorkoutCounter);
                new CountDownTimer(5000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tvTime.setText("" + String.format("%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                        progressbar.setProgress((int) (millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        tvTime.setText("done!");
                        ++MainActivity.activeWorkoutCounter;
                        lvExercises.setSelection(MainActivity.activeWorkoutCounter);
                        if (MainActivity.activeWorkoutCounter < MainActivity.currentWorkout.getExercises().size()) {
                            Exercise e = MainActivity.exerciseList.get(w.getExercises().get(MainActivity.activeWorkoutCounter));
                            gif.setGifMovie(e.getGif());
                            this.start();
                        } else {
                            MainActivity.activeWorkoutCounter = 0;
                            rlplayimage.setVisibility(View.GONE);
                            llplay.setVisibility(View.GONE);
                            rlplay.setVisibility(View.VISIBLE);
                            rlName.setVisibility(View.VISIBLE);
                            image.setVisibility(View.VISIBLE);
                            next.setText("Exercises");
                            rvExercises.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.95f));
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
