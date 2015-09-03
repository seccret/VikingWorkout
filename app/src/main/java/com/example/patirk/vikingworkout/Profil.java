package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */

public class Profil extends android.support.v4.app.Fragment {

    public static List<Workout> workouts = null;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Profil newInstance() {
        Profil fragment = new Profil();
        Bundle args = new Bundle();
     //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public Profil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        rootView.findViewById(R.id.tvItemWorkout);

        workouts = new ArrayList<Workout>();
        workouts.add(new Workout(0, "Mage"));
        workouts.add(new Workout(1, "Ben"));
        workouts.add(new Workout(2, "Rygg"));
        workouts.add(new Workout(3, "Armar"));
        workouts.add(new Workout(4, "Cross-training"));
        workouts.add(new Workout(5, "Ultimate situps"));

       GridView gv = (GridView) rootView.findViewById(R.id.gvWorkouts);
       AdapterImage AI = new AdapterImage(rootView.getContext());
       gv.setAdapter(AI);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
