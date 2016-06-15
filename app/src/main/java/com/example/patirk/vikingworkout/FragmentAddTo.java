package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentAddTo extends android.support.v4.app.Fragment {
    public static FragmentAddTo fragment;
    public static FragmentAddTo newInstance() {
        fragment = new FragmentAddTo();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentAddTo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_to, container, false);
        final TextView headline = (TextView) rootView.findViewById(R.id.tvAddToHeadline);
        final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.llAddTo);
        final TextView addToMyWorkouts = (TextView) rootView.findViewById(R.id.tvAddToMyWorkouts);
        final TextView addToMyAgenda = (TextView) rootView.findViewById(R.id.tvAddToMyAgenda);
        final Workout workout = MainActivity.currentWorkout;
        final int workoutId = workout.getId();

        headline.setText(workout.getName());
        if (MainActivity.profile.containWorkout(workoutId)) {
            addToMyWorkouts.setText("Remove from My Workouts");
        } else {
            addToMyWorkouts.setText("Add to My Workouts");
        }

        addToMyAgenda.setText("Add to Agenda");


        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentAddTo.fragment)
                        .commit();
            }

        });

        addToMyWorkouts.setOnClickListener(new View.OnClickListener() {
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
                        .remove(FragmentAddTo.fragment)
                        .commit();
            }

        });

        addToMyAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentCalendar.newInstance())
                        .commit();

            }

        });

        return rootView;
    }
}
