package com.example.patirk.vikingworkout;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentExerciseInfo extends android.support.v4.app.Fragment {
    public static FragmentExerciseInfo fragment;
    public static FragmentExerciseInfo newInstance() {
        fragment = new FragmentExerciseInfo();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentExerciseInfo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_exercise_info, container, false);
        final TextView exname = (TextView) rootView.findViewById(R.id.tvEInfoName);
        final ImageView eximage = (ImageView) rootView.findViewById(R.id.ivEInfoImage);
        final LinearLayout llinfo = (LinearLayout) rootView.findViewById(R.id.llEInfo);
        Exercise exercise = MainActivity.currentExercise;

        exname.setText(exercise.getName());
        eximage.setBackground(exercise.getPicture());

        llinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentExerciseInfo.fragment)
                        .commit();
            }

        });

        return rootView;
    }
}
