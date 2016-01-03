package com.example.patirk.vikingworkout;

import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Patirk on 03/01/2016.
 */
public class Serializer {

    public void serializeProfile(){

        Profile prof = MainActivity.profile;

        try{
            FileOutputStream fout = MainActivity.mainActivity.openFileOutput("viking_profile", MainActivity.mainActivity.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(prof);
            oos.close();

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
