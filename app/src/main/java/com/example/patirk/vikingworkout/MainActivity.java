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
    public static int currentDay;
    public static FragmentManager fragmentManager = null;
    public static int lastLongClick, activeWorkoutCounter=0;
    private static List<Block> blocksList = null;
    private static List<Exercise> exerciseList = null;
    private static List<Workout> workouts = null;
    private static List<Event> events = null;
    private static List<Day> days = null;
    private static List<Week> weeks = null;
    private static List<Month> months = null;


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
        MainActivity.days = new ArrayList<Day>();
        MainActivity.weeks = new ArrayList<Week>();
        MainActivity.months = new ArrayList<Month>();

        loadExercises();
        loadProfile();
        loadBlocks();
        loadWorkouts();
        loadWeeks();
        loadMonths();
      //  List<Workout> l = new ArrayList<>();
       // List<Day> dayprofile = new ArrayList<>();
        // profile = new Profile(1337,"Olivia", "hej",l,dayprofile);
      //  List<Integer> l = new ArrayList<>();
       // profile.setWorkout(l);
 //       GoogleSpreadsheet gs = new GoogleSpreadsheet();
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
                fragmentManager.beginTransaction().replace(R.id.container, FragmentProgress.newInstance())
                        .commit();

                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                fragmentManager.beginTransaction().replace(R.id.container, FragmentBrowse.newInstance())
                        .commit();

                break;
            case 5:
                mTitle = "Settings";
                fragmentManager.beginTransaction().replace(R.id.container, FragmentSettings.newInstance())
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

    public static Exercise getExerciseByID (int id){
        for(Exercise e : exerciseList){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    public static Block getBlockByID (int id){
        for(Block b : blocksList){
            if(b.getId() == id){
                return b;
            }
        }
        for(Block b : profile.getMyBlocks()){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }
    public static Day getDayByID (int dateid) {
        for (Day d : days) {
            if (d.getDateId() == dateid) {
                return d;
            }
        }
        for(Day d : profile.getMyDays()){
            if(d.getDateId() == dateid){
                return d;
            }
        }
        return null;
    }
    public static Workout getWorkoutByID (int id){
        for(Workout w : workouts){
            if(w.getId() == id){
                return w;
            }
        }
        for(Workout w : profile.getMyWorkouts()){
            if(w.getId() == id){
                return w;
            }
        }
        return null;
    }
    public static Event getEventByID (int id){
        for(Event e : events){
            if(e.getEventId() == id){
                return e;
            }
        }
        return null;
    }
    public static List<Exercise> getExercises (){
        return exerciseList;
    }

    public static List<Workout> getWorkouts (){
       return workouts;
    }

    public static List<Day> getDays (){
        return days;
    }

    public static List<Week> getWeeks (){
        return weeks;
    }

    public static List<Month> getMonths (){
        return months;
    }

    public boolean loadMonths(){
        List<Integer> we = new ArrayList<>();
        we.add(0);
        we.add(1);
        we.add(2);
        we.add(3);
        MainActivity.months = new ArrayList<Month>();
        MainActivity.months.add(new Month(1604, we));
        MainActivity.months.add(new Month(1605, we));
        return true;
    }

    public boolean loadWeeks(){
        List<Integer> workouts = new ArrayList<>();
        workouts.add(2);
        workouts.add(1);
        workouts.add(2);
        workouts.add(1);
        List<Integer> w = new ArrayList<>();
        w.add(2);
        w.add(2);
        w.add(2);
        w.add(2);
        MainActivity.days = new ArrayList<Day>();
        List<Day> d0 = new ArrayList<Day>();
        d0.add(new Day(16928, workouts));
        d0.add(new Day(16929, w));
        d0.add(new Day(16930, workouts));
        d0.add(new Day(16931, w));
        d0.add(new Day(16932, workouts));
        d0.add(new Day(16933, w));
        d0.add(new Day(16934, workouts));
        List<Day> d1 = new ArrayList<Day>();
        d1.add(new Day(16935, workouts));
        d1.add(new Day(16936, w));
        d1.add(new Day(16937, workouts));
        d1.add(new Day(16938, w));
        d1.add(new Day(16939, w));
        d1.add(new Day(16940, w));
        d1.add(new Day(16941, w));
        days.addAll(d0);
        days.addAll(d1);
        MainActivity.weeks = new ArrayList<Week>();
        MainActivity.weeks.add(new Week(1613, d0));
        MainActivity.weeks.add(new Week(1614, d1));
        MainActivity.weeks.add(new Week(1615, d0));
        MainActivity.weeks.add(new Week(1616, d1));
        return true;
    }

    public boolean loadWorkouts(){
        List<Integer> b = new ArrayList<>();
        b.add(0);
        b.add(1);
        b.add(2);
        b.add(3);
        String muscleGroup = ExternalFunctions.findMuscleGroupWo(b);
        MainActivity.workouts = new ArrayList<Workout>();
        MainActivity.workouts.add(new Workout(0, "Mage", 1, b, muscleGroup, "Viking"));
        MainActivity.workouts.add(new Workout(1, "Ben", 1, b, muscleGroup, "Viking"));
        MainActivity.workouts.add(new Workout(2, "Rygg", 1, b, muscleGroup, "Viking"));
        MainActivity.workouts.add(new Workout(3, "Armar", 1, b, muscleGroup, "Viking"));
        MainActivity.workouts.add(new Workout(4, "Cross-training", 1, b, muscleGroup, "Viking"));
        MainActivity.workouts.add(new Workout(5, "Ultimate situps", 1, b, muscleGroup, "Viking"));

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
            String muscleGroup = ExternalFunctions.findMuscleGroup(e);
            if (i == 0) {
                block = new Block(0, "Block Name", "Seven Block", e, r, muscleGroup);
            } else if (i == 1) {
                block = new  Block(1, "Block Name", "List Block", e, r, muscleGroup);
            } else if (i == 2) {
                block = new  Block(2, "Block Name", "Seven Block", e, r, muscleGroup);
            } else if (i == 3) {
                block = new  Block(3, "Block Name", "List Block", e, r, muscleGroup);
            }
            blocksList.add(block);
        }
    }
    public void loadExercises(){
        InputStream gifInputStream;
        MainActivity.exerciseList = new ArrayList<Exercise>();
        Exercise exercise = null;

        for(int i = 0; i<4; i++) {
            List<String> muscleList = new ArrayList<>();
            if (i == 0) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.commandos);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                muscleList.add("Arms"); muscleList.add("Chest"); muscleList.add("Abs");
                exercise = new Exercise(0, "Commandos", "Description", muscleList ,img, gif);
            } else if (i == 1) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.pushups);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                muscleList.add("Butt"); muscleList.add("Legs");
                exercise = new Exercise(1, "Push Up", "Description", muscleList, img, gif);
            } else if (i == 2) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.situps);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                muscleList.add("Abs");
                exercise = new Exercise(2, "Sit Up", "Description", muscleList, img, gif);
            } else if (i == 3) {
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.running);
                Movie gif = Movie.decodeStream(gifInputStream);
                Drawable img = (Drawable) Drawable.createFromStream(gifInputStream, "img");
                muscleList.add("Abs"); muscleList.add("Legs");
                exercise = new Exercise(3, "Runner", "Description", muscleList, img, gif);
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
      //  Bitmap bitbit = ExternalFunctions.getImageBitmap(this, "profile", "JPEG");
     //   Bitmap loadedImage = ExternalFunctions.getCroppedBitmap(bitbit);
     //   profile.setProfilePicture(loadedImage);
        Toast.makeText(this, "Loading profile: '" + MainActivity.profile.getName() + "'..", Toast.LENGTH_SHORT).show();

      }


    public static void saveProfile(Context c) {
        Serializer serializer = new Serializer();
        serializer.serializeProfile();
        ExternalFunctions.saveImage(mainActivity,profile.getPictureAsBitmap(),"profile","JPEG");
        Toast.makeText(mainActivity, "Saving profile: '" + MainActivity.profile.getName() + "'..", Toast.LENGTH_SHORT).show();

    }

}
