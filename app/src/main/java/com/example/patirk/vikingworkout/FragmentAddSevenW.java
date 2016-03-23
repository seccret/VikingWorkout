package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.mortbay.jetty.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentAddSevenW extends android.support.v4.app.Fragment {
    public static List<Exercise> e;
    public static List<Block> b;
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
        //final EditText woTag = (EditText) rootView.findViewById(R.id.etAddSTag);

        final EditText blockName = (EditText) rootView.findViewById(R.id.etAddSBlockName);

        final Spinner woTag = (Spinner) rootView.findViewById(R.id.spinner);
        String[] items = new String[]{"Seven Workout", "List Workout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.mainActivity, android.R.layout.simple_spinner_dropdown_item, items);
        woTag.setAdapter(adapter);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newBlockId = MainActivity.profile.getMyBlocks().size();
                String newBlockName = blockName.getText().toString();

                List<Integer> newExercise = new ArrayList<>(4);
                for (int i = 0; i < e.size(); i++) {
                    newExercise.add(e.get(i).getId());
                }
                List<Integer> r = new ArrayList<Integer>(4);
                r.add(newRepetitions.get(0));
                r.add(newRepetitions.get(1));
                r.add(newRepetitions.get(2));
                r.add(newRepetitions.get(3));
                Block newBlock = new Block(newBlockId, newBlockName, newExercise, r);
                MainActivity.profile.addBlock(newBlock);
                Toast.makeText(getActivity(), "Block saved", Toast.LENGTH_SHORT).show();

                int newId = MainActivity.profile.getMyWorkouts().size();
                String newName = woName.getText().toString();
                int newPic = 1;
                TextView textTag = (TextView) woTag.getSelectedView();
                String newTag = textTag.getText().toString();
                //String newTag = woTag.getText().toString();

                List<Integer> b = new ArrayList<>();
                b.add(newBlock.getId());
                Workout newWorkout = new Workout(newId, newName,newTag, newPic, b);

                Toast.makeText(getActivity(), "Workout saved", Toast.LENGTH_SHORT).show();
                MainActivity.profile.addWorkout(newWorkout);
                MainActivity.saveProfile(MainActivity.mainActivity);
            }
        });

        Adapter4AddSevenWorkout ad = new Adapter4AddSevenWorkout(MainActivity.mainActivity, fragment, e);
    exercises.setAdapter(ad);
        return rootView;
    }
}
