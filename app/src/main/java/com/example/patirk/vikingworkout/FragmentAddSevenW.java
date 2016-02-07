package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentAddSevenW extends android.support.v4.app.Fragment {

    public static FragmentAddSevenW newInstance() {
        FragmentAddSevenW fragment = new FragmentAddSevenW();
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

        List<Exercise> e = new ArrayList<>();
        Exercise e1 = new Exercise(-1, "Add Exercise", null, null);
        Exercise e2 = new Exercise(-1, "Add Exercise", null, null);
        Exercise e3 = new Exercise(-1, "Add Exercise", null, null);
        Exercise e4 = new Exercise(-1, "Add Exercise", null, null);
        e.add(e1);
        e.add(e2);
        e.add(e3);
        e.add(e4);

        Adapter4AddSevenWorkout ad = new Adapter4AddSevenWorkout(MainActivity.mainActivity, e);
    exercises.setAdapter(ad);
        return rootView;
    }
}
