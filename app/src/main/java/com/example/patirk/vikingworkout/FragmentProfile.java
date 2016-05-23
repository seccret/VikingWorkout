package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.mortbay.jetty.Main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

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
        final ListView lv = (ListView) rootView.findViewById(R.id.lvWorkouts);
        final ImageView profilePic = (ImageView) rootView.findViewById(R.id.ivProfileImage);
        final TextView profileName = (TextView) rootView.findViewById(R.id.tvProfileName);
        final TextView profileDesc = (TextView) rootView.findViewById(R.id.tvProfileInfo);
        final ImageView plus = (ImageView) rootView.findViewById(R.id.ivProfilePlus);
        final ImageView wplus = (ImageView) rootView.findViewById(R.id.ivProfilePlusW);
        final ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        final SlidingUpPanelLayout supl = (SlidingUpPanelLayout) rootView.findViewById(R.id.sliding_layout);
        final Button myAgendaButton = (Button) rootView.findViewById(R.id.bToggleLeft);
        final Button myWorkoutButton = (Button) rootView.findViewById(R.id.bToggleMiddle);
        final Button myBlockButton = (Button) rootView.findViewById(R.id.bToggleRight);

        Drawable pPic = MainActivity.profile.getPicture();
        String pName = MainActivity.profile.getName();
        String pDesc = MainActivity.profile.getDesc();

        if (pPic != null) {
            profilePic.setImageDrawable(null);
            profilePic.setBackground(pPic);
        }
        profileName.setText(pName);
        profileDesc.setText(pDesc);

        //Create list of users workout
        final List<Integer> myWorkout = MainActivity.profile.getMyWorkoutsAsID();

        final SlidingUpPanelLayout.PanelState wut = supl.getPanelState();
        final String wut2 = wut.toString();

        List<Integer> agendaDates = new ArrayList<>();
        long tday = System.currentTimeMillis()/(60*60*24*1000);
        DecimalFormat rDFormat = new DecimalFormat("#");
        int todaysDate = Integer.valueOf(rDFormat.format(tday));
        for (int i = todaysDate; i<todaysDate+14 ; i++) {
            agendaDates.add(i);
        }
        viewPager.setAdapter(new CustomPagerAdapter(getContext(), agendaDates));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> nullMuscle = new ArrayList<String>();
                Exercise e = new Exercise(-1, "Add Exercise", null, nullMuscle, null, null);
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentAddBlock.newInstance(e, e, e, e))
                        .commit();
            }
        });
        wplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nullMuscle = new String();
                List<Integer> nullRep = new ArrayList<Integer>();
                Block b = new Block(-1, "Add Block", null, null, nullRep, nullMuscle);
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentAddWorkout.newInstance(b, b, b, b))
                        .commit();
            }
        });

        myAgendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAgendaButton.setBackgroundResource(R.drawable.button_selected);
                myWorkoutButton.setBackgroundResource(R.drawable.button_unselected);
                myBlockButton.setBackgroundResource(R.drawable.button_unselected);
                myAgendaButton.setTextColor(Color.parseColor("#fba500"));
                myBlockButton.setTextColor(Color.parseColor("#c1c1c1"));
                myWorkoutButton.setTextColor(Color.parseColor("#c1c1c1"));
                lv.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                if (supl.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    Toast.makeText(MainActivity.mainActivity, "Expanded", Toast.LENGTH_SHORT).show();
                    profilePic.setVisibility(View.INVISIBLE);

                } else {
                    Toast.makeText(MainActivity.mainActivity, "Collapsed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        myWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWorkoutButton.setBackgroundResource(R.drawable.button_selected);
                myBlockButton.setBackgroundResource(R.drawable.button_unselected);
                myAgendaButton.setBackgroundResource(R.drawable.button_unselected);
                myWorkoutButton.setTextColor(Color.parseColor("#fba500"));
                myBlockButton.setTextColor(Color.parseColor("#c1c1c1"));
                myAgendaButton.setTextColor(Color.parseColor("#c1c1c1"));
                lv.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.GONE);
                AdapterProfileWorkout AI = new AdapterProfileWorkout(rootView.getContext(), myWorkout);
                lv.setAdapter(AI);
            }
        });

        myBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBlockButton.setBackgroundResource(R.drawable.button_selected);
                myWorkoutButton.setBackgroundResource(R.drawable.button_unselected);
                myAgendaButton.setBackgroundResource(R.drawable.button_unselected);
                myBlockButton.setTextColor(Color.parseColor("#fba500"));
                myAgendaButton.setTextColor(Color.parseColor("#c1c1c1"));
                myWorkoutButton.setTextColor(Color.parseColor("#c1c1c1"));
                lv.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.GONE);
                AdapterProfileBlock AI = new AdapterProfileBlock(rootView.getContext(), MainActivity.profile.getMyBlocks());
                lv.setAdapter(AI);
            }
        });

      /*  lvProfile = (ListView) rootView.findViewById(R.id.lvWorkouts);
        AdapterProfileWorkout AI = new AdapterProfileWorkout(rootView.getContext(), myWorkout);
        lvProfile.setAdapter(AI);
        registerForContextMenu(lvProfile);
        */
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
