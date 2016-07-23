package com.example.patirk.vikingworkout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;

import org.mortbay.jetty.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 25/02/2016.
 */
public class FacebookGetImage extends AsyncTask<String, Void, SpreadsheetFeed> {

    private Exception exception;
    String sheetURL =  "";
    int row = 1;

    FacebookGetImage(String sheetURL){
        this.sheetURL = sheetURL;
    }

    protected SpreadsheetFeed doInBackground(String... urls) {

        try {
            SpreadsheetService service = new SpreadsheetService("Viking");
            service.setProtocolVersion(SpreadsheetService.Versions.V3);

            URL metafeedUrl = new URL(sheetURL);
            //Log.d("--Untz Untz--", service.get);
            SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);

            //URL image_value = new URL("https://graph.facebook.com/"+MainActivity.fbID+"/picture" );
            URL image_value = new URL(sheetURL);
            Bitmap profPict = BitmapFactory.decodeStream(image_value.openConnection().getInputStream());
            MainActivity.facebookPicture = profPict;
            MainActivity.profile.setProfilePictureFromBitmap(profPict);
            return feed;
        } catch (Exception e) {
            this.exception = e;

            return null;
        }
    }

    protected void onPostExecute(SpreadsheetFeed feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
           /*
            */
        Toast.makeText(MainActivity.mainActivity, "Picture done", Toast.LENGTH_SHORT).show();
    }
}
