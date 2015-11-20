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
        ImageView gif = (ImageView) rootView.findViewById(R.id.ivExerciseImage);
        TextView des = (TextView) rootView.findViewById(R.id.tvExerciseDes);
        Exercise exercise = MainActivity.currentExercise;

        if(exercise.getPicture()==1){
            gif.setBackgroundResource(R.mipmap.image1);
        }else if(exercise.getPicture()==2){
            gif.setBackgroundResource(R.mipmap.image2);
        }else if(exercise.getPicture()==3){
            gif.setBackgroundResource(R.mipmap.image3);
        }

        des.setText("How to do a "+exercise.getName()+" exercise!");

        return rootView;
    }

}
