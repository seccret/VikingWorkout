package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by olivia on 2015-10-24.
 */
public class FragmentAddWorkout extends android.support.v4.app.Fragment {

    public static FragmentAddWorkout newInstance() {
        FragmentAddWorkout fragment = new FragmentAddWorkout();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentAddWorkout() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_workout, container, false);

//        Spinner spinner = (Spinner) rootView.findViewById(R.id.spAddWTemplate);
// Create an ArrayAdapter using the string array and a default spinner layout
 //       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
 //               R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
 //       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
  //      spinner.setAdapter(adapter);


        return rootView;
    }
}