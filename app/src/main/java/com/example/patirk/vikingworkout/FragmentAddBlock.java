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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentAddBlock extends android.support.v4.app.Fragment {
    public static List<Exercise> e;
    public static List<Block> b;
    public static FragmentAddBlock fragment;
    public static int index = 0;
    public static List<Integer> newRepetitions = new ArrayList<>();

    public static FragmentAddBlock newInstance(Exercise e1, Exercise e2, Exercise e3, Exercise e4) {
        List<Exercise> eList = new ArrayList<>();
        eList.add(e1); eList.add(e2); eList.add(e3); eList.add(e4);
        newRepetitions.add(0); newRepetitions.add(0); newRepetitions.add(0); newRepetitions.add(0);
        e = eList;
        fragment = new FragmentAddBlock();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentAddBlock() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_block, container, false);
        final ImageView done = (ImageView) rootView.findViewById(R.id.ivSevenDone);
        final GridView exercises = (GridView) rootView.findViewById(R.id.gvAddSevenExercises);
        final EditText blockName = (EditText) rootView.findViewById(R.id.etAddSBlockName);
        final Spinner blockTTag = (Spinner) rootView.findViewById(R.id.spinner);

        String[] items = new String[]{"Seven Block", "List Block"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.mainActivity, android.R.layout.simple_spinner_dropdown_item, items);
        blockTTag.setAdapter(adapter);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sProfileID = String.valueOf(MainActivity.profile.getId());
                String sBlockID = String.valueOf(MainActivity.profile.getMyBlocks().size());
                int newBlockId = Integer.valueOf(sProfileID + sBlockID);
                String newBlockName = blockName.getText().toString();
                TextView blockTag = (TextView) blockTTag.getSelectedView();
                String newBlockTTag = blockTag.getText().toString();

                List<Integer> newExercise = new ArrayList<>(4);
                for (int i = 0; i < e.size(); i++) {
                    newExercise.add(e.get(i).getId());
                }
                List<Integer> r = new ArrayList<Integer>(4);
                r.add(newRepetitions.get(0));
                r.add(newRepetitions.get(1));
                r.add(newRepetitions.get(2));
                r.add(newRepetitions.get(3));
                String newMuscleGroup = ExternalFunctions.findMuscleGroup(newExercise);
                Block newBlock = new Block(newBlockId, newBlockName, newBlockTTag, newExercise, r, newMuscleGroup);
                MainActivity.profile.addBlock(newBlock);
                Toast.makeText(getActivity(), "Block saved", Toast.LENGTH_SHORT).show();
                MainActivity.saveProfile(MainActivity.mainActivity);
            }
        });

        Adapter4AddBlock ad = new Adapter4AddBlock(MainActivity.mainActivity, fragment, e);
    exercises.setAdapter(ad);
        return rootView;
    }
}
