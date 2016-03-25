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
    public static FragmentExerciseList fragment;
    public static FragmentExerciseList newInstance() {
        fragment = new FragmentExerciseList();
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



        Adapter4ExerciseList el = new Adapter4ExerciseList(MainActivity.mainActivity, MainActivity.getExercises());
        exerciselist.setAdapter(el);
        return rootView;
    }
}
