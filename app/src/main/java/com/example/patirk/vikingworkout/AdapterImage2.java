package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AdapterImage2 extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout=null;

    // Constructor
    public AdapterImage2(Context c, List<Exercise> workoutList) {
        mInflater = LayoutInflater.from(c);

        for (Exercise w : workoutList) {
            String name = w.getName();
            int picture = w.getPicture();
            mItems.add(new Item(name, picture));
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
        ImageButton image;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_workout, viewGroup, false);
            v.setTag(R.id.tvItemWorkout, v.findViewById(R.id.tvItemWorkout));
        }
        name = (TextView) v.findViewById(R.id.tvItemWorkout);
        image = (ImageButton) v.findViewById(R.id.ibItemWorkout);
        final Item item = getItem(i);
        name.setText(item.name);

        if(item.picture==1){
            image.setBackgroundResource(R.mipmap.image1);
        }else if(item.picture==2){
           image.setBackgroundResource(R.mipmap.image2);
        }else if(item.picture==3){
            image.setBackgroundResource(R.mipmap.image3);
        }

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Exercise with items parameters
                Exercise wo = new Exercise(0,item.name,item.picture);
                //Step 2: Set currentWorkout to clicked exercise
                MainActivity.currentExercise = wo;
                //Step 3: Go to exercise fragment
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentExercise.newInstance())
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