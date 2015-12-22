package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.internal.widget.FitWindowsLinearLayout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AdapterImage extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterImage(Context c, List<Workout> workoutList) {
        mInflater = LayoutInflater.from(c);

        for (Workout w : workoutList) {
            String name = w.getName();
            int picture = w.getPicture();
            mItems.add(new Item(name, picture));
            this.workout = w;
        }
    }

    public int getCount() {
        return mItems.size();
    }

    public Item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        TextView name;
        ImageView image;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_workout_big, viewGroup, false);
            v.setTag(R.id.tvItemWorkout, v.findViewById(R.id.tvItemWorkout));
        }
        name = (TextView) v.findViewById(R.id.tvItemWorkout);
        image = (ImageView) v.findViewById(R.id.ibItemWorkout);
        final Item item = getItem(i);
        name.setText(item.name);

        //Fix size of Mipmap
        if (item.picture == 1) {
            image.setBackgroundResource(R.mipmap.image1);
        } else if (item.picture == 2) {
            image.setBackgroundResource(R.mipmap.image2);
        } else if (item.picture == 3) {
            image.setBackgroundResource(R.mipmap.image3);
        }



        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Workout with items parameters
                Workout wo = new Workout(0, item.name, item.picture);
                //Step 2: Set currentWorkout to clicked workout
                MainActivity.currentWorkout = wo;
                //Step 3: Go to workout fragment
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentWorkout.newInstance())
                        .commit();
            }
        });

        return v;
    }

    private static class Item {
        public final String name;
        public final int picture;

        Item(String name, int picture) {
            this.name = name;
            this.picture = picture;
        }
    }
}