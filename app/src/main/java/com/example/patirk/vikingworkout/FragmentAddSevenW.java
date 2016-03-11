package com.example.patirk.vikingworkout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentAddSevenW extends android.support.v4.app.Fragment {
    public static List<Exercise> e;
    public static FragmentAddSevenW fragment;
    public static int index = 0;
    public static List<Integer> newRepetitions = new ArrayList<>();

    public static FragmentAddSevenW newInstance(Exercise e1, Exercise e2, Exercise e3, Exercise e4) {
        List<Exercise> eList = new ArrayList<>();
        eList.add(e1); eList.add(e2); eList.add(e3); eList.add(e4);
        newRepetitions.add(0); newRepetitions.add(0); newRepetitions.add(0); newRepetitions.add(0);
        e = eList;
        fragment = new FragmentAddSevenW();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentAddSevenW() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_seven, container, false);
        final EditText woName = (EditText) rootView.findViewById(R.id.etAddSName);
        final ImageView done = (ImageView) rootView.findViewById(R.id.ivSevenDone);
        final GridView exercises = (GridView) rootView.findViewById(R.id.gvAddSevenExercises);
        final EditText woTag = (EditText) rootView.findViewById(R.id.etAddSTag);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getActivity(), "Workout saved", Toast.LENGTH_SHORT).show();
                int newId = MainActivity.workouts.size();
                String newName = woName.getText().toString();
                int newPic = 1;
                String newTag = woTag.getText().toString();

                List<Integer> newExercises = new ArrayList<>();
                for(int i=0; i< e.size(); i++) {
                    newExercises.add(i, e.get(i).getId());
                }
                List<Integer> r = new ArrayList<Integer>();
                r.add(newRepetitions.get(0));r.add(newRepetitions.get(1));r.add(newRepetitions.get(2));r.add(newRepetitions.get(3));
                Workout newWorkout = new Workout(newId, newName, newPic,newTag, newExercises , r);
                Toast.makeText(getActivity(), "Workout saved", Toast.LENGTH_SHORT).show();
                MainActivity.workouts.add(newWorkout);
            }
        });

        Adapter4AddSevenWorkout ad = new Adapter4AddSevenWorkout(MainActivity.mainActivity, fragment, e);
    exercises.setAdapter(ad);
        return rootView;
    }
}
