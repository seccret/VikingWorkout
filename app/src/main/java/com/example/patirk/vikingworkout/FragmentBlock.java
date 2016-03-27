package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2015-10-24.
 */
public class FragmentBlock extends android.support.v4.app.Fragment {

    public static FragmentBlock newInstance() {
        FragmentBlock fragment = new FragmentBlock();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentBlock() {
    }

    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;
    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
        final TextView blockName = (TextView) rootView.findViewById(R.id.tvWorkoutName);
        final ListView lvExercises = (ListView) rootView.findViewById(R.id.lvWorkoutList);
        final Block block = MainActivity.currentBlock;

        blockName.setText(block.getMuscleGroup());

        List<String> template = new ArrayList<String>();
        template.add("Seven");
        template.add("List");

        Adapter4WorkoutSeven ai = new Adapter4WorkoutSeven(MainActivity.mainActivity, block);
        lvExercises.setAdapter(ai);

        return rootView;
    }
}
