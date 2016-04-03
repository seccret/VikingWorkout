package com.example.patirk.vikingworkout;

/**
 * Created by Patirk on 03/01/2016.
 */
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {

    public Profile deserialzeProfile(String pName) {

        Profile prof;

        try {

            FileInputStream fin = MainActivity.mainActivity.openFileInput("viking_profile");
            ObjectInputStream ois = new ObjectInputStream(fin);
            prof = (Profile) ois.readObject();
            ois.close();
            Toast.makeText(MainActivity.mainActivity, "created profile: '" + pName + "'..", Toast.LENGTH_SHORT).show();
            return prof;

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(MainActivity.mainActivity, "Profile created: "+pName, Toast.LENGTH_SHORT).show();
            prof = new Profile(1337, pName, "No description available",null);
            return prof;
        }
    }
}
