package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 03/09/2015.
 */

public class FragmentProfile extends android.support.v4.app.Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns mainActivity new instance of this fragment for the given section
     * number.
     */
    public static FragmentProfile newInstance() {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentProfile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rootView.findViewById(R.id.tvItemWorkout);
        GridView gvProfile;
        Drawable pPic = MainActivity.profile.getPicture();
        String pName = MainActivity.profile.getName();
        ImageView profilePic = (ImageView) rootView.findViewById(R.id.ivProfileImage);
        TextView profileName = (TextView) rootView.findViewById(R.id.tvProfileName);
        if (pPic != null) {
            profilePic.setImageDrawable(null);
            profilePic.setBackground(pPic);
        }
        profileName.setText(pName);

        final Button myworkoutButton = (Button) rootView.findViewById(R.id.bToggleLeft);
        final Button globalButton = (Button) rootView.findViewById(R.id.bToggleMiddle);
        final Button statisticButton = (Button) rootView.findViewById(R.id.bToggleRight);

        //Craete list of users workout
        final List<Workout> myWorkout = new ArrayList<Workout>();
        for(int i=0; i< MainActivity.profile.getMyWorkouts().size(); i++){
            myWorkout.add(MainActivity.workouts.get((int)MainActivity.profile.getMyWorkouts().get(i)));
        }


        myworkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myworkoutButton.setBackgroundResource(R.drawable.button_selected);
                myworkoutButton.setTextColor(getResources().getColor(R.color.halfTrans));
                globalButton.setBackgroundResource(R.drawable.button_unselected);
                globalButton.setTextColor(getResources().getColor(R.color.white));
                statisticButton.setBackgroundResource(R.drawable.button_unselected);
                statisticButton.setTextColor(getResources().getColor(R.color.white));
                GridView gv = (GridView) rootView.findViewById(R.id.gvWorkouts);
                AdapterImage AI = new AdapterImage(rootView.getContext(), myWorkout);
                gv.setAdapter(AI);
            }
        });

        globalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalButton.setBackgroundResource(R.drawable.button_selected);
                globalButton.setTextColor(getResources().getColor(R.color.halfTrans));
                myworkoutButton.setBackgroundResource(R.drawable.button_unselected);
                myworkoutButton.setTextColor(getResources().getColor(R.color.white));
                statisticButton.setBackgroundResource(R.drawable.button_unselected);
                statisticButton.setTextColor(getResources().getColor(R.color.white));
                GridView gv = (GridView) rootView.findViewById(R.id.gvWorkouts);
                AdapterImage AI = new AdapterImage(rootView.getContext(), MainActivity.workouts);
                gv.setAdapter(AI);


            }
        });

        statisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statisticButton.setBackgroundResource(R.drawable.button_selected);
                statisticButton.setTextColor(getResources().getColor(R.color.halfTrans));
                myworkoutButton.setBackgroundResource(R.drawable.button_unselected);
                myworkoutButton.setTextColor(getResources().getColor(R.color.white));
                globalButton.setBackgroundResource(R.drawable.button_unselected);
                globalButton.setTextColor(getResources().getColor(R.color.white));

            }
        });

        gvProfile = (GridView) rootView.findViewById(R.id.gvWorkouts);
        AdapterImage AI = new AdapterImage(rootView.getContext(), myWorkout);
        gvProfile.setAdapter(AI);
        registerForContextMenu(gvProfile);
        registerForContextMenu(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Toast.makeText(MainActivity.mainActivity, "U did a long click!", Toast.LENGTH_SHORT).show();
        MenuInflater inflater = MainActivity.mainActivity.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    public boolean onContextItemSelected(final MenuItem item){
        if(item.getTitle().equals("Remove")){
            MainActivity.profile.removeWorkout(MainActivity.lastLongClick);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
        return true;
    }
}
