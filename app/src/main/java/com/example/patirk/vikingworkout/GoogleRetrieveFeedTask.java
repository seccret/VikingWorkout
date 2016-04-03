package com.example.patirk.vikingworkout;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 25/02/2016.
 */
public class GoogleRetrieveFeedTask extends AsyncTask<String, Void, SpreadsheetFeed> {

    private Exception exception;
    String sheetURL =  "https://spreadsheets.google.com/feeds/cells/1JWJmqrIVUZjjbOmfesOp3jmnHEXC1dVqP3XBOUfcAV8/1/public/values";
    int row = 1;

    GoogleRetrieveFeedTask(String sheetURL){
        this.sheetURL = sheetURL;
    }

    protected SpreadsheetFeed doInBackground(String... urls) {

        try {
            SpreadsheetEntry spreadsheet = null;
            String spreadsheetTitle = "Workout", worksheetTitle="Viking";

            SpreadsheetService service = new SpreadsheetService("Viking");
            service.setProtocolVersion(SpreadsheetService.Versions.V3);

            URL metafeedUrl = new URL(sheetURL);
            //Log.d("--Untz Untz--", service.get);
            SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);

            List<SpreadsheetEntry> spreadsheets = feed.getEntries();
            int i=0;
            Workout newWorkout;
            int wId=-1;
            String wName="";
            int wPic=-1;
            List<Integer> wExercises = new ArrayList<>();
            for (SpreadsheetEntry entry : spreadsheets) {

                String count = String.valueOf(entry.getTitle().getPlainText().charAt(1));
                int CurrentRow = Integer.valueOf(count);
                if (CurrentRow != row) {
                    wExercises = new ArrayList<>();
                    row=CurrentRow;
                }
                if(row>1) {
                    if (entry.getTitle().getPlainText().contains("A")) {
                        wId = Integer.valueOf(entry.getPlainTextContent());
                    } else if (entry.getTitle().getPlainText().contains("B")) {
                        wName = entry.getPlainTextContent();
                    } else if (entry.getTitle().getPlainText().contains("C")) {
                        wPic = 1; //Integer.valueOf(entry.getPlainTextContent());
                    } else if (entry.getTitle().getPlainText().contains("D")) {
                        for (int k=0; k<4; k++) {
                            wExercises.add(Integer.valueOf(k));
                        }
                        /*String[] sExercises = entry.getPlainTextContent().split(";");
                        for (String exercise : sExercises) {
                            wExercises.add(Integer.valueOf(exercise));
                        }
                        */
                        newWorkout = new Workout(wId, wName, wPic, wExercises);
                        MainActivity.addWorkout(newWorkout);
                    }
                }
            }

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
    }
}
