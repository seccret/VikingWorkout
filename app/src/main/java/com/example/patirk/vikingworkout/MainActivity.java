package com.example.patirk.vikingworkout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static Profile profile = null;
    public static Activity mainActivity = null;
    public static FileInputStream fis=null;
    public static FileOutputStream fos=null;

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
        profile = new Profile(0,"Test Name");

        loadProfile();
        mainActivity = this;

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentProfile.newInstance())
                        .commit();

                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentBrowse.newInstance())
                        .commit();

                break;
            case 4:
                mTitle = "Settings";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentSettings.newInstance())
                        .commit();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
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

    public boolean loadProfile(){
        try {
            fis = openFileInput("viking_profile_name");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[50];
            // Fill the Buffer with data from the file
            isr.read(inputBuffer);
            // Transform the chars to a String
            String readString = new String(inputBuffer);
            profile.setName(readString);
            Bitmap bitbit= ExernalFunctions.getImageBitmap(this, "profile", "JPEG" );
            Bitmap loadedImage = ExernalFunctions.getCroppedBitmap(bitbit);
            profile.setProfilePicture(loadedImage);
            Toast.makeText(this, "Loading profile: '"+readString+"'..", Toast.LENGTH_SHORT).show();
            return true;
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(this, "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return false;
    }

    public static void saveProfile(Context c){
        try{
            Drawable draw = profile.getPicture();
            Bitmap bit = ExernalFunctions.drawableToBitmap(draw);
            ExernalFunctions.saveImage(MainActivity.mainActivity, bit, "profile","JPEG");
            String profileName = profile.getName();
            fos = c.openFileOutput("viking_profile_name",c.MODE_PRIVATE);
            fos.write(profileName.getBytes());
            fos.close();
            Toast.makeText(MainActivity.mainActivity, "Profile saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(MainActivity.mainActivity, "File not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(MainActivity.mainActivity, "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
