package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.user.IdentityManager;
import com.amazonaws.mobile.user.IdentityProvider;
import com.amazonaws.mobile.user.signin.FacebookSignInProvider;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.Profile;
import com.google.gdata.util.ServiceException;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    //For facebook
    public static CallbackManager callbackManager;
    public static Profile fbProfile;
    public static String fbID;
    public static com.example.patirk.vikingworkout.Profile profile = null;
    public static Activity mainActivity = null;
    public static FileInputStream fis = null;
    public static FileOutputStream fos = null;
    public static Workout currentWorkout = null;
    public static Block currentBlock = null;
    public static Exercise currentExercise = null;
    public static FragmentManager fragmentManager = null;
    public static int lastLongClick, activeWorkoutCounter=0;
    public static Bitmap facebookPicture;

    private static List<Block> blocksList = null;
    private static List<Exercise> exerciseList = null;
    private static List<Workout> workouts = null;


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

        //Amazon


        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    /*    LoginManager.getInstance().logInWithReadPermissions(MainActivity.mainActivity, Arrays.asList("public_profile", "user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(MainActivity.mainActivity, "in loggad via facebook", Toast.LENGTH_SHORT).show();
                        Profile.fetchProfileForCurrentAccessToken();
                        String name =Profile.getCurrentProfile().getName();
                        fbProfile = Profile.getCurrentProfile();
                        fbID = Profile.getCurrentProfile().getId();

                        loadProfile(name, Profile.getCurrentProfile().getId());

                        MainActivity.removeFragment("login");
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(MainActivity.mainActivity, "FB: Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(MainActivity.mainActivity, "FB: Error", Toast.LENGTH_SHORT).show();
                    }
                });

*/
        //Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
        MainActivity.workouts = new ArrayList<Workout>();
        loadExercises();
        loadWorkouts();
        loadBlocks();
        loadProfile("", "");
       //   List<Workout> l = new ArrayList<>();
       //  profile = new Profile(1337,"Olivia", "hej",l);
       // List<Integer> l = new ArrayList<>();
       // profile.setWorkout(l);

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
    public void onResume(){
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
  /*      fragmentManager.beginTransaction()
                .add(R.id.container, FragmentLogin.newInstance(),"login")
                .commit();
    */
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

    public static void removeFragment(String fragName){
        fragmentManager.beginTransaction()
                .remove(MainActivity.fragmentManager.findFragmentByTag(fragName))
                .commit();
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

    public static List<Exercise> getExercises (){
        return exerciseList;
    }

    public static List<Workout> getWorkouts (){
       return workouts;
    }

    public boolean loadWorkouts(){
        /*List<Integer> b = new ArrayList<>();
        b.add(0);
        b.add(1);
        b.add(2);
        b.add(3);
        MainActivity.workouts = new ArrayList<Workout>();
        MainActivity.workouts.add(new Workout(0, "Mage", 1, b));
        MainActivity.workouts.add(new Workout(1, "Ben", 1, b));
        MainActivity.workouts.add(new Workout(2, "Rygg", 1, b));
        MainActivity.workouts.add(new Workout(3, "Armar", 1, b));
        MainActivity.workouts.add(new Workout(4, "Cross-training", 1, b));
        MainActivity.workouts.add(new Workout(5, "Ultimate situps", 1, b));
        */
        GoogleSpreadsheet gs = new GoogleSpreadsheet();
        try{
            //gs.addToSheet();
            String s = gs.getFeed();
            // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }catch (IOException | ServiceException IOe){
        }

        return true;
    }

    public static void addWorkout(Workout w){
        MainActivity.workouts.add(w);
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
                muscleList.add("Arms"); muscleList.add("Chest");
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

    public static void loadProfile(String pName, String pID){
     /*   Deserializer deserializer = new Deserializer();
        MainActivity.profile = deserializer.deserialzeProfile(pName);
        GoogleSpreadsheet gs = new GoogleSpreadsheet();
        gs.getFacebookPic();
      */
        String usrName = getUserName();
        List<Workout> w = new ArrayList<>();
        Bitmap usrImage = getUserImage(MainActivity.mainActivity);
        MainActivity.profile = new com.example.patirk.vikingworkout.Profile(1337, usrName, "Hej", w);
        //MainActivity.profile.setProfilePictureFromBitmap(usrImage);
      }

    private static String getUserName() {
        final IdentityManager identityManager =
                AWSMobileClient.defaultMobileClient().getIdentityManager();
        final IdentityProvider identityProvider =
                identityManager.getCurrentIdentityProvider();

        if (identityProvider == null) {
            // Not signed in
            return "";
        }

        final String userName =
                identityProvider.getUserName();

       return userName;
    }

    private static Bitmap getUserImage(final Context activity) {

        final IdentityManager identityManager =
                AWSMobileClient.defaultMobileClient().getIdentityManager();
        final IdentityProvider identityProvider =
                identityManager.getCurrentIdentityProvider();


    /*    if (identityProvider == null) {
            // Not signed in
            if (Build.VERSION.SDK_INT < 21) {
                return BitmapFactory.decodeResource(activity.getResources(), R.mipmap.user);
            }
            else {
                return ExternalFunctions.drawableToBitmap(activity.getDrawable(R.mipmap.user));
            }
        }
*/
        Toast.makeText(mainActivity, "got Image", Toast.LENGTH_SHORT).show();
        final Bitmap userImage = identityManager.getUserImage();
        String urlImage = identityProvider.getUserImageUrl();
        GoogleSpreadsheet gs = new GoogleSpreadsheet();
        gs.getFacebookPic(urlImage);
        return userImage;

    }



    public static void saveProfile(Context c) {
        Serializer serializer = new Serializer();
        serializer.serializeProfile();
        ExternalFunctions.saveImage(mainActivity,profile.getPictureAsBitmap(),"profile","JPEG");
        Toast.makeText(mainActivity, "Saving profile: '" + MainActivity.profile.getName() + "'..", Toast.LENGTH_SHORT).show();

    }

    public void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo("com.example.patirk.vikingworkout",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                String sign= Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("KeyHash:", sign);
                //  Toast.makeText(getApplicationContext(),sign,     Toast.LENGTH_LONG).show();
            }
            Log.d("KeyHash:", "****------------***");
            Log.d("KeyHash:", "****------------***");
            Log.d("KeyHash:", "****------------***");
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("KeyHash:", "****------------***");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Log.d("KeyHash:", "****------------***");
            e.printStackTrace();
        }
    }

}
