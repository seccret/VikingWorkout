package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Patirk on 28/03/2016.
 */
public class FragmentChooseTimeRep extends android.support.v4.app.Fragment {
    public static FragmentChooseTimeRep fragment;
    public static List<Integer> clock = new ArrayList<>(4);
    public static FragmentChooseTimeRep newInstance(List<Integer> values) {
        clock = values;
        fragment = new FragmentChooseTimeRep();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_choose_time_rep, container, false);
        final TextView chooseTime = (TextView) rootView.findViewById(R.id.tvChooseTime);
        final TextView chooseRep = (TextView) rootView.findViewById(R.id.tvChooseRep);
        final TextView LMValue = (TextView) rootView.findViewById(R.id.tvChooseLeftMinute);
        final TextView RMValue = (TextView) rootView.findViewById(R.id.tvChooseRightMinute);
        final TextView LSValue = (TextView) rootView.findViewById(R.id.tvChooseLeftSecond);
        final TextView RSValue = (TextView) rootView.findViewById(R.id.tvChooseRightSecond);
        final ImageView clearButton = (ImageView) rootView.findViewById(R.id.ivChooseErase);
        final GridView keypad = (GridView) rootView.findViewById(R.id.gvChooseKeypad);

        if(clock.size()==0) {
            LMValue.setText("-");
            RMValue.setText("-");
            LSValue.setText("-");
            RSValue.setText("-");
        }if(clock.size()==1) {
            LMValue.setText(String.valueOf(clock.get(0)));
            RMValue.setText("-");
            LSValue.setText("-");
            RSValue.setText("-");
        }if(clock.size()==2) {
            LMValue.setText(String.valueOf(clock.get(0)));
            RMValue.setText(String.valueOf(clock.get(1)));
            LSValue.setText("-");
            RSValue.setText("-");
        }if(clock.size()==3) {
            LMValue.setText(String.valueOf(clock.get(0)));
            RMValue.setText(String.valueOf(clock.get(1)));
            LSValue.setText(String.valueOf(clock.get(2)));
            RSValue.setText("-");
        }if(clock.size()==4) {
            LMValue.setText(String.valueOf(clock.get(0)));
            RMValue.setText(String.valueOf(clock.get(1)));
            LSValue.setText(String.valueOf(clock.get(2)));
            RSValue.setText(String.valueOf(clock.get(3)));
        }

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.mainActivity, "clear", Toast.LENGTH_SHORT).show();
                clock = new ArrayList<Integer>(4);
                MainActivity.fragmentManager.beginTransaction()
                        .remove(MainActivity.fragmentManager.findFragmentByTag("keypad"))
                        .commit();
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentChooseTimeRep.newInstance(clock), "keypad")
                        .commit();
            }
        });
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.mainActivity, "Time", Toast.LENGTH_SHORT).show();
            }

        });
        chooseRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.mainActivity, "Rep", Toast.LENGTH_SHORT).show();
            }

        });

        AdapterGridViewKeypad AI = new AdapterGridViewKeypad(rootView.getContext(), this);
        keypad.setAdapter(AI);

        return rootView;
    }
    public static void keyDown(int key){
        if(clock.size() < 4){
            clock.add(key);
        }
        MainActivity.fragmentManager.beginTransaction()
                .remove(MainActivity.fragmentManager.findFragmentByTag("keypad"))
                .commit();
        MainActivity.fragmentManager.beginTransaction()
                .add(R.id.container, FragmentChooseTimeRep.newInstance(clock), "keypad")
                .commit();
    }
}