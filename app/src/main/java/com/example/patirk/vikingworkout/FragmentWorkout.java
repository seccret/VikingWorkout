package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;
    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
        final ImageView workoutImage = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
        final TextView tvTime = (TextView) rootView.findViewById(R.id.tvWorkoutTime);
       // final GifView gif = (GifView) rootView.findViewById(R.id.gif_view);
        final ImageView gifimage = (ImageView) rootView.findViewById(R.id.gif_view);
        final ImageView image = (ImageView) rootView.findViewById(R.id.ivWorkoutImage);
        final TextView woname = (TextView) rootView.findViewById(R.id.tvWorkoutName);
        final RelativeLayout rlplayimage = (RelativeLayout) rootView.findViewById(R.id.rlWorkoutPlay);
        final LinearLayout llplay = (LinearLayout) rootView.findViewById(R.id.llWorkoutPlay);
        final RelativeLayout rlWorkout = (RelativeLayout) rootView.findViewById(R.id.rlWorkout);
        final ListView lvExercises = (ListView) rootView.findViewById(R.id.lvWorkoutList);
        final LinearLayout llExercises = (LinearLayout) rootView.findViewById(R.id.llWorkoutList);
        final TextView pause = (TextView) rootView.findViewById(R.id.tvWorkoutPause);
        final TextView cancel = (TextView) rootView.findViewById(R.id.tvWorkoutStop);
        final TextView resume = (TextView) rootView.findViewById(R.id.tvWorkoutResume);
        final Workout workout = MainActivity.currentWorkout;
        final List<Integer> blocks = workout.getBlocks();

        woname.setText(workout.getName());



        List<String> template = new ArrayList<String>();
        template.add("Seven");
        template.add("List");

    workoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlWorkout.setVisibility(View.GONE);
                woname.setVisibility(View.GONE);
                tvTime.setVisibility(View.VISIBLE);
                rlplayimage.setVisibility(View.VISIBLE);
                llplay.setVisibility(View.VISIBLE);
                llExercises.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.6f));
                CountDownTimer timer;
                long millisInFuture = 30000; //30 seconds
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
                        tvTime.setText("done!");
                        rlWorkout.setVisibility(View.VISIBLE);
                        woname.setVisibility(View.VISIBLE);
                        tvTime.setVisibility(View.GONE);
                        rlplayimage.setVisibility(View.GONE);
                        llplay.setVisibility(View.GONE);
                        llExercises.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2.05f));
                    }
                }.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When user request to pause the CountDownTimer
                isPaused = true;
                resume.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Specify the current state is not paused and canceled.
                isPaused = false;
                isCanceled = false;

                resume.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);

                //Initialize a new CountDownTimer instance
                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval){
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
                        tvTime.setText("done!");
                        rlWorkout.setVisibility(View.VISIBLE);
                        woname.setVisibility(View.VISIBLE);
                        tvTime.setVisibility(View.GONE);
                        rlplayimage.setVisibility(View.GONE);
                        llplay.setVisibility(View.GONE);
                        llExercises.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2.05f));
                    }
                }.start();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When user request to cancel the CountDownTimer
                isCanceled = true;
                Toast.makeText(getActivity(),"Block canceled", Toast.LENGTH_SHORT).show();
                rlWorkout.setVisibility(View.VISIBLE);
                woname.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.GONE);
                rlplayimage.setVisibility(View.GONE);
                llplay.setVisibility(View.GONE);
                llExercises.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2.05f));
            }
        });

        Adapter4InceptionBase ai = new Adapter4InceptionBase(MainActivity.mainActivity, blocks);
        lvExercises.setAdapter(ai);

        return rootView;
    }
}
