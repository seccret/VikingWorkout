package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.util.ServiceException;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static Profile profile = null;
    public static Activity mainActivity = null;
    public static FileInputStream fis = null;
    public static FileOutputStream fos = null;
    public static Workout currentWorkout = null;
    public static Block currentBlock = null;
    public static Exercise currentExercise = null;
    public static FragmentManager fragmentManager = null;
    public static int lastLongClick, activeWorkoutCounter=0;
    public static List<Block> blocksList = null;
    public static List<Exercise> exerciseList = null;
    public static List<Workout> workouts = null;



    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        // = savedInstanceState.get("allrecipes");
        //Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
        MainActivity.workouts = new ArrayList<Workout>();
        loadExercises();
        loadProfile();
        loadWorkouts();
        loadBlocks();
        //  List<Integer> l = new ArrayList<>();
        // profile = new Profile(0,"Olivia", "hej",l);
        List<Integer> l = new ArrayList<>();
        profile.setWorkout(l);
        GoogleSpreadsheet gs = new GoogleSpreadsheet();
  /*      try{
            gs.addToSheet();
            String s = gs.getFeed();
           // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }catch (IOException IOe){
        }catch (ServiceException Se){
        }
        */
//
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        fragmentManager = getSupportFragmentManager();
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                fragmentManager.beginTransaction().replace(R.id.container, FragmentProfile.newInstance())
                    .commit();

                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                fragmentManager.beginTransaction().replace(R.id.container, FragmentBrowse.newInstance())
                        .commit();

                break;
            case 4:
                mTitle = "Settings";
                fragmentManager.beginTransaction().replace(R.id.container, FragmentSettings.newInstance())
                        .commit();
                break;
            case 5:
                mTitle = "Training Plan";
                fragmentManager.beginTransaction().replace(R.id.container, FragmentTrainingPlan.newInstance())
                        .commit();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify mainActivity parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean loadWorkouts(){
        List<Integer> b = new ArrayList<>();
        b.add(0);
        b.add(1);
        b.add(2);
        b.add(3);
        MainActivity.workouts = new ArrayList<Workout>();
        MainActivity.workouts.add(new Workout(0, "Mage","Seven Workout", 1, b));
        MainActivity.workouts.add(new Workout(1, "Ben", "List Workout", 1, b));
        MainActivity.workouts.add(new Workout(2, "Rygg", "List Workout", 1, b));
        MainActivity.workouts.add(new Workout(3, "Armar", "Seven Workout", 1, b));
        MainActivity.workouts.add(new Workout(4, "Cross-training", "List Workout", 1, b));
        MainActivity.workouts.add(new Workout(5, "Ultimate situps", "Seven Workout", 1, b));

        return true;
    }
    public void loadBlocks(){
        InputStream gifInputStream;
        MainActivity.blocksList = new ArrayList<Block>();
        Block block = null;
        List<Integer> e = new ArrayList<>();
        e.add(0);
        e.add(1);
        e.add(2);
        e.add(3);
        List<Integer> r = new ArrayList<>();
        r.add(15);
        r.add(15);
        r.add(15);
        r.add(15);
        for(int i = 0; i<4; i++) {
            if (i == 0) {
                block = new Block(0, "Mage", e, r);
            } else if (i == 1) {
                block = new  Block(1, "Ben", e, r);
            } else if (i == 2) {
                block = new  Block(2, "Armar", e, r);
            } else if (i == 3) {
                block = new  Block(3, "Rygg", e, r);
            }
            blocksList.add(block);
        }
    }
    public void loadExercises(){
        InputStream gifInputStream;
        MainActivity.exerciseList = new ArrayList<Exercise>();
        Exercise exercise = null;
        for(int i = 0; i<4; i++) {
            if (i == 0) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.commandos);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                exercise = new Exercise(0, "Commandos", img, gif, "Description");
            } else if (i == 1) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.pushups);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                exercise = new Exercise(1, "Push Up", img, gif, "Description");
            } else if (i == 2) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.situps);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                exercise = new Exercise(2, "Sit Up", img, gif, "Description");
            } else if (i == 3) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.running);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                exercise = new Exercise(3, "Runner", img, gif, "Description");
            }
            exerciseList.add(exercise);
        }
    }

    /**
     * A placeholder fragment containing mainActivity simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns mainActivity new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void loadProfile(){
        Deserializer deserializer = new Deserializer();
        MainActivity.profile = deserializer.deserialzeProfile();

        //Load Profile-pic
        Bitmap bitbit = ExernalFunctions.getImageBitmap(this, "profile", "JPEG");
        Bitmap loadedImage = ExernalFunctions.getCroppedBitmap(bitbit);
        profile.setProfilePicture(loadedImage);
        Toast.makeText(this, "Loading profile: '" + MainActivity.profile.getName() + "'..", Toast.LENGTH_SHORT).show();

    }


    public static void saveProfile(Context c) {
        Serializer serializer = new Serializer();
        serializer.serializeProfile();

        ExernalFunctions.saveImage(mainActivity,profile.getPictureAsBitmap(),"profile","JPEG");
        Toast.makeText(mainActivity, "Saving profile: '" + MainActivity.profile.getName() + "'..", Toast.LENGTH_SHORT).show();
    }

}
