package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by olivia on 2015-10-26.
 */
public class FragmentExercise extends android.support.v4.app.Fragment {

    public static FragmentExercise newInstance() {
        FragmentExercise fragment = new FragmentExercise();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentExercise() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);
        GifView gif = (GifView) rootView.findViewById(R.id.gif_view);
        TextView name = (TextView) rootView.findViewById(R.id.tvExerciseName);
        Exercise exercise = MainActivity.currentExercise;
        gif.setGifMovie(exercise.getGif());
        name.setText(exercise.getName());


        return rootView;
    }

}
