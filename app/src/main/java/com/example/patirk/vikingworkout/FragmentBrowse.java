package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2015-09-07.
 */
public class FragmentBrowse extends android.support.v4.app.Fragment {

    public static List<Workout> catWorkouts = null;

    public static FragmentBrowse newInstance() {
        FragmentBrowse fragment = new FragmentBrowse();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentBrowse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        TextView cat = (TextView) rootView.findViewById(R.id.tvBrowseCat);
        GridView category = (GridView) rootView.findViewById(R.id.gvBrowseCategory);
        Workout workout = MainActivity.currentWorkout;

        catWorkouts = new ArrayList<Workout>();
        catWorkouts.add(new Workout(0, "Mage", 2));
        catWorkouts.add(new Workout(1, "Ben", 3));
        catWorkouts.add(new Workout(2, "Rygg", 2));
        catWorkouts.add(new Workout(3, "Armar", 3));
        catWorkouts.add(new Workout(4, "Cross-training", 2));
        catWorkouts.add(new Workout(5, "Ultimate situps", 3));
        catWorkouts.add(new Workout(6, "Mage", 2));
        catWorkouts.add(new Workout(7, "Ben", 3));
        catWorkouts.add(new Workout(8, "Rygg", 2));

        AdapterImage AI2 = new AdapterImage(rootView.getContext(), catWorkouts);
        category.setAdapter(AI2);


        return rootView;
    }
}
