package com.example.patirk.vikingworkout;

import android.util.Log;

import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;

/**
 * Created by Patirk on 15/02/2016.
 */
public class GoogleSpreadsheet {
    //choose Spreadsheet
    String p1 = "https://spreadsheets.google.com/tq?key=";      //Standard
    String p2 = "1JWJmqrIVUZjjbOmfesOp3jmnHEXC1dVqP3XBOUfcAV8"; //Workout sheet
    // 13ir5hArRUVmUrjcje6OaJMbHa5t93z_eVI_1KqbNAgI
    String sheetURL =   "https://spreadsheets.google.com/feeds/cells/1JWJmqrIVUZjjbOmfesOp3jmnHEXC1dVqP3XBOUfcAV8/1/public/values";

    int row = 1;
    public String getFeed() throws MalformedURLException, AuthenticationException, ServiceException, IOException{

        new GoogleRetrieveFeedTask(sheetURL).execute();

        return "hej";
    }

    public void getFacebookPic(String url){
        new FacebookGetImage(url).execute();
    }

    public void addToSheet(){
        new GoogleSendFeedTask(sheetURL).execute();
    }
}
