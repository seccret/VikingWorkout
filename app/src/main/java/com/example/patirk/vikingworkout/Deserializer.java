package com.example.patirk.vikingworkout;

/**
 * Created by Patirk on 03/01/2016.
 */
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {

    public Profile deserialzeProfile() {

        Profile prof;

        try {

            FileInputStream fin = MainActivity.mainActivity.openFileInput("viking_profile");
            ObjectInputStream ois = new ObjectInputStream(fin);
            prof = (Profile) ois.readObject();
            ois.close();

            return prof;

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(MainActivity.mainActivity, "No user found", Toast.LENGTH_SHORT).show();
            prof = new Profile(0, "Unknown", null);
            return prof;
        }
    }
}
