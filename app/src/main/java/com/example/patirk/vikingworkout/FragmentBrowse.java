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

    public static List<Workout> recWorkouts = null;
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
        TextView rec = (TextView) rootView.findViewById(R.id.tvBrowseRec);
        GridView recommended = (GridView) rootView.findViewById(R.id.gvBrowseRecommended);
        TextView cat = (TextView) rootView.findViewById(R.id.tvBrowseCat);
        GridView category = (GridView) rootView.findViewById(R.id.gvBrowseCategory);
        Workout workout = MainActivity.currentWorkout;


        recWorkouts = new ArrayList<Workout>();
        recWorkouts.add(new Workout(0, "Mage", 1));
        recWorkouts.add(new Workout(1, "Ben", 2));
        recWorkouts.add(new Workout(2, "Rygg", 3));
        recWorkouts.add(new Workout(3, "Armar", 1));
        recWorkouts.add(new Workout(4, "Cross-training", 2));
        recWorkouts.add(new Workout(5, "Ultimate situps", 3));

        catWorkouts = new ArrayList<Workout>();
        catWorkouts.add(new Workout(0, "Mage", 1));
        catWorkouts.add(new Workout(1, "Ben", 2));
        catWorkouts.add(new Workout(2, "Rygg", 3));
        catWorkouts.add(new Workout(3, "Armar", 1));
        catWorkouts.add(new Workout(4, "Cross-training", 2));
        catWorkouts.add(new Workout(5, "Ultimate situps", 3));

        AdapterImage AI = new AdapterImage(rootView.getContext(), recWorkouts);
        recommended.setAdapter(AI);

        AdapterImage AI2 = new AdapterImage(rootView.getContext(), catWorkouts);
        category.setAdapter(AI2);


        return rootView;
    }
}
