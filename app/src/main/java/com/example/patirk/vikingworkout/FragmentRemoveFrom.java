package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentRemoveFrom extends android.support.v4.app.Fragment {
    public static FragmentRemoveFrom fragment;
    public static FragmentRemoveFrom newInstance() {
        fragment = new FragmentRemoveFrom();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentRemoveFrom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_to, container, false);
        final TextView headline = (TextView) rootView.findViewById(R.id.tvAddToHeadline);
        final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.llAddTo);
        final TextView removeFromMyWorkouts = (TextView) rootView.findViewById(R.id.tvAddToMyWorkouts);
        final TextView removeFromMyAgenda = (TextView) rootView.findViewById(R.id.tvAddToMyAgenda);
        final Workout workout = MainActivity.currentWorkout;
        final int workoutId = workout.getId();
        final int dayId = MainActivity.currentDay;

        headline.setText(workout.getName());
        if (MainActivity.profile.containWorkout(workoutId)) {
            removeFromMyWorkouts.setText("Remove from My Workouts");
        } else {
            removeFromMyWorkouts.setText("Add to My Workouts");
        }
        removeFromMyAgenda.setText("Remove from Agenda");


        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentRemoveFrom.fragment)
                        .commit();
            }

        });

        removeFromMyWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.profile.containWorkout(workoutId)) {
                    MainActivity.lastLongClick = workoutId;
                    MainActivity.profile.removeWorkout(workoutId);
                    Toast.makeText(MainActivity.mainActivity, workout.getName() + " workout removed from profile", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.profile.addToMyWorkout(MainActivity.getWorkoutByID(workoutId));
                    Toast.makeText(MainActivity.mainActivity, workout.getName() + " workout added to profile", Toast.LENGTH_SHORT).show();
                }
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentRemoveFrom.fragment)
                        .commit();
            }

        });

        removeFromMyAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.lastLongClick = workoutId;
                MainActivity.profile.getDayByID(dayId).removeWorkouts(workoutId);
                Toast.makeText(MainActivity.mainActivity, workout.getName() + " workout has been removed from day", Toast.LENGTH_SHORT).show();
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentRemoveFrom.fragment)
                        .commit();
            }

        });

        return rootView;
    }
}
