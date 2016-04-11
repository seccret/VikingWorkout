package com.example.patirk.vikingworkout;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Patirk on 23/10/2015.
 */
public class ExternalFunctions {

    public static String findMuscleGroup(List<Integer> exerciseIntList){
        List<String> muscleList = new ArrayList<>();
        List<Exercise> exerciseList = new ArrayList<>();
        for(int i=0; i<exerciseIntList.size(); i++){
            muscleList.addAll(MainActivity.getExerciseByID(exerciseIntList.get(i)).getMuscle());
        }

   /*     for(int i=0; i<exerciseList.size(); i++){
            List<String> exerciseMuscleList = exerciseList.get(i).getMuscle();
            muscleList.addAll(exerciseMuscleList);
        }
        Toast.makeText(MainActivity.mainActivity, "checklist2 = "+muscleList.size(), Toast.LENGTH_SHORT).show();
     */   List<String> checkSingle = new ArrayList<>();;
        for(String s : muscleList){
            checkSingle.add(s);
        }
        checkSingle.removeAll(Collections.singleton(checkSingle.get(0)));
        Toast.makeText(MainActivity.mainActivity, "checklist = "+checkSingle, Toast.LENGTH_SHORT).show();
        if(checkSingle.size() == 0){
            return muscleList.get(0);
        }else if(muscleList.contains("Arms") && muscleList.contains("Butt") ||
                muscleList.contains("Arms") && muscleList.contains("Legs") ||
                muscleList.contains("Shoulders") && muscleList.contains("Butt") ||
                muscleList.contains("Shoulders") && muscleList.contains("Legs") ||
                muscleList.contains("Chest") && muscleList.contains("Butt") ||
                muscleList.contains("Chest") && muscleList.contains("Legs") ||
                muscleList.contains("Back") && muscleList.contains("Butt") ||
                muscleList.contains("Back") && muscleList.contains("Legs") ||
                muscleList.contains("Abs") && muscleList.contains("Butt") ||
                muscleList.contains("Abs") && muscleList.contains("Legs")
                ){
            return "Cross";
        }else if(muscleList.contains("Butt") && muscleList.contains("Legs")){
            return "Lower body";
        }else{
            return "Upper body";
        }
    }

    public static String findMuscleGroupWo(List<Integer> blockIntList){
        List<String> muscleGroupList = new ArrayList<>();
        List<Block> blockList = new ArrayList<>();
        for(int i=0; i<blockIntList.size(); i++){
            muscleGroupList.add(MainActivity.getBlockByID(blockIntList.get(i)).getMuscleGroup());
        }

   /*     for(int i=0; i<exerciseList.size(); i++){
            List<String> exerciseMuscleList = exerciseList.get(i).getMuscle();
            muscleList.addAll(exerciseMuscleList);
        }
        Toast.makeText(MainActivity.mainActivity, "checklist2 = "+muscleList.size(), Toast.LENGTH_SHORT).show();
     */   List<String> checkSingle = new ArrayList<>();;
        for(String s : muscleGroupList){
            checkSingle.add(s);
        }
        checkSingle.removeAll(Collections.singleton(checkSingle.get(0)));
        Toast.makeText(MainActivity.mainActivity, "checklist = "+checkSingle, Toast.LENGTH_SHORT).show();
        if(checkSingle.size() == 0){
            return muscleGroupList.get(0);
        }else if(muscleGroupList.contains("Arms") && muscleGroupList.contains("Butt") ||
                muscleGroupList.contains("Arms") && muscleGroupList.contains("Legs") ||
                muscleGroupList.contains("Shoulders") && muscleGroupList.contains("Butt") ||
                muscleGroupList.contains("Shoulders") && muscleGroupList.contains("Legs") ||
                muscleGroupList.contains("Chest") && muscleGroupList.contains("Butt") ||
                muscleGroupList.contains("Chest") && muscleGroupList.contains("Legs") ||
                muscleGroupList.contains("Back") && muscleGroupList.contains("Butt") ||
                muscleGroupList.contains("Back") && muscleGroupList.contains("Legs") ||
                muscleGroupList.contains("Abs") && muscleGroupList.contains("Butt") ||
                muscleGroupList.contains("Abs") && muscleGroupList.contains("Legs")||
                muscleGroupList.contains("Lower body") && muscleGroupList.contains("Upper body")
                ){
            return "Cross";
        }else if(muscleGroupList.contains("Butt") && muscleGroupList.contains("Legs")){
            return "Lower body";
        }else{
            return "Upper body";
        }
    }

    public static int getStartEpochOfMonth (int checkEpoch){
        Date date = new Date(((long) checkEpoch *24*60*60*1000));
        SimpleDateFormat format = new SimpleDateFormat("MM", Locale.US);
        int month = Integer.valueOf(format.format(date));
        int newMonth = month;
        while (newMonth == month){
            checkEpoch--;
            date = new Date(((long) checkEpoch *24*60*60*1000));
            format = new SimpleDateFormat("MM", Locale.US);
            newMonth = Integer.valueOf(format.format(date));
        }
        return checkEpoch+1;
    }

    public static int getEndEpochOfMonth (int checkEpoch){
        Date date = new Date(((long) checkEpoch *24*60*60*1000));
        SimpleDateFormat format = new SimpleDateFormat("MM", Locale.US);
        int month = Integer.valueOf(format.format(date));
        int newMonth = month;
        while (newMonth == month){
            checkEpoch++;
            date = new Date(((long) checkEpoch *24*60*60*1000));
            format = new SimpleDateFormat("MM", Locale.US);
            newMonth = Integer.valueOf(format.format(date));
        }
        return checkEpoch-1;
    }

    public static Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / ((float) 2), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void saveImage(Context context, Bitmap b, String name, String extension) {
        name = name + "." + extension;
        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getImageBitmap(Context context, String name, String extension) {
        name = name + "." + extension;
        try {
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        } catch (Exception e) {
        }
        return null;
    }
}
