package com.example.patirk.vikingworkout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentExerciseList extends android.support.v4.app.Fragment {

    public static FragmentExerciseList newInstance() {
        FragmentExerciseList fragment = new FragmentExerciseList();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentExerciseList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_exercise_list, container, false);
        final ListView exerciselist = (ListView) rootView.findViewById(R.id.lvExerciseList);

        List<Exercise> e = new ArrayList<>();
        Exercise e1 = new Exercise(0, "Add Exercise", null, null);
        Exercise e2 = new Exercise(1, "Add Exercise", null, null);
        Exercise e3 = new Exercise(2, "Add Exercise", null, null);
        Exercise e4 = new Exercise(3, "Add Exercise", null, null);
        e.add(e1);
        e.add(e2);
        e.add(e3);
        e.add(e4);

        Adapter4ExerciseList el = new Adapter4ExerciseList(MainActivity.mainActivity, e);
        exerciselist.setAdapter(el);
        return rootView;
    }
}
