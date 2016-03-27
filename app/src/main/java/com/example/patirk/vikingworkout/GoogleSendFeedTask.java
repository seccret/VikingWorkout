package com.example.patirk.vikingworkout;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patirk on 25/02/2016.
 */
public class GoogleSendFeedTask extends AsyncTask<String, Void, SpreadsheetFeed> {

    private Exception exception;
    private Workout workout;
    String sheetURL = "https://spreadsheets.google.com/feeds/worksheets/1JWJmqrIVUZjjbOmfesOp3jmnHEXC1dVqP3XBOUfcAV8/private/full";
    int row = 1;

    GoogleSendFeedTask(String sheetURL){
        this.sheetURL = sheetURL;
    }

    protected SpreadsheetFeed doInBackground(String... urls) {

        try {
            Log.d("----PATRIK!!", "1. YOU MADE IT THIS FAR!!----");
            SpreadsheetEntry spreadsheet = null;
            String spreadsheetTitle = "Workout", worksheetTitle="Viking";

            SpreadsheetService service = new SpreadsheetService("Viking");
            service.setProtocolVersion(SpreadsheetService.Versions.V1);

            URL metafeedUrl = new URL(sheetURL);
            Log.d("----PATRIK!!", "2. YOU MADE IT THIS FAR!!----");
            //Log.d("--Untz Untz--", service.get);
            SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();

            int i = 0;
            Log.d("----PATRIK!!", "3. YOU MADE IT THIS FAR!!----");
            //Getting the first worksheet in the first spreadsheet
            for (SpreadsheetEntry entry : spreadsheets) {
                i++;
                Log.d("----PATRIK!!", "1START LOOP!!----");
                WorksheetFeed worksheetFeed = service.getFeed(
                            metafeedUrl, WorksheetFeed.class);
                Log.d("----PATRIK!!", "2START LOOP!!----");
                List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
                WorksheetEntry worksheet = worksheets.get(0);
                Log.d("----PATRIK!!", "4. YOU MADE IT THIS FAR!!----");
                //URL listFeedUrl
                int count = worksheet.getRowCount();
                Log.d("----PATRIK!! 41", String.valueOf(count));
               // ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);
                Log.d("----PATRIK!!", "50. YOU MADE IT THIS FAR!!----");
                ListEntry row = new ListEntry();
                // Fetch the list feed of the worksheet.
                URL listFeedUrl = worksheet.getListFeedUrl();
                ListFeed listFeed = service.getFeed(metafeedUrl, ListFeed.class);
                row = listFeed.getEntries().get(0);
                Log.d("----PATRIK!!", "51. YOU MADE IT THIS FAR!!----");
                row.getCustomElements().setValueLocal("WID", "0");
                row.getCustomElements().setValueLocal("Name", "Upload");
                row.getCustomElements().setValueLocal("Image", "1");
                row.getCustomElements().setValueLocal("Exercises", "1;3;3;1");
                Log.d("----PATRIK!!", "60. YOU MADE IT THIS FAR!!----");
                row.update();
                //row = service.insert(metafeedUrl, row);
                Log.d("----PATRIK!!", "61. YOU MADE IT THIS FAR!!----");
            }
            Log.d("PATRIK!!", "7.YOU MADE IT THIS FAR!! enteries = "+ String.valueOf(i));
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
