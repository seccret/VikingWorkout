package com.example.patirk.vikingworkout;

import android.os.Bundle;
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
 * Created by olivia on 2015-10-24.
 */
public class FragmentAddWorkout extends android.support.v4.app.Fragment {
    public static List<Block> b;
    public static FragmentAddWorkout fragment;
    public static int index = 0;

    public static FragmentAddWorkout newInstance(Block b1, Block b2, Block b3, Block b4) {
        List<Block> bList = new ArrayList<>();
        bList.add(b1); bList.add(b2); bList.add(b3); bList.add(b4);
        b = bList;
        fragment = new FragmentAddWorkout();
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
        final ImageView done = (ImageView) rootView.findViewById(R.id.ivAddWDone);
        final GridView blocks = (GridView) rootView.findViewById(R.id.gvAddWBlock);
        final EditText workoutName = (EditText) rootView.findViewById(R.id.etAddWWoName);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sProfileID = String.valueOf(MainActivity.profile.getId());
                String sWorkoutID = String.valueOf(MainActivity.profile.getMyWorkouts().size());
                int newWorkoutId = Integer.valueOf(sProfileID + sWorkoutID);
                String newWorkoutName = workoutName.getText().toString();

                List<Integer> newBlock = new ArrayList<>(4);
                for (int i = 0; i < b.size(); i++) {
                    newBlock.add(b.get(i).getId());
                }
                String newMuscleGroup = ExternalFunctions.findMuscleGroup(newBlock);
                Workout newWorkout = new Workout(newWorkoutId, newWorkoutName, 1, newBlock);
                MainActivity.profile.addWorkout(newWorkout);
                Toast.makeText(getActivity(), "Workout saved", Toast.LENGTH_SHORT).show();
                MainActivity.saveProfile(MainActivity.mainActivity);
            }
        });

        Adapter4AddWorkout ad = new Adapter4AddWorkout(MainActivity.mainActivity, fragment, b);
        blocks.setAdapter(ad);
        return rootView;
    }
}