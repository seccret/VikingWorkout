package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Adapter4WorkoutSeven extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Block block = null;
    private static List<Integer> repetitions = new ArrayList<>();

    // Constructor
    public Adapter4WorkoutSeven(Context c, Block b) {
        mInflater = LayoutInflater.from(c);
        this.block = b;
        this.repetitions= block.getRepetitions();

        for (int exerciseID : block.getExercises()) {
            Exercise w = MainActivity.exerciseList.get(exerciseID);
            int id = w.getId();
            String name = w.getName();
            Drawable picture = w.getPicture();
            Movie gif = w.getGif();
            mItems.add(new Item(id, name, picture, gif));
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
        TextView rep;
        TextView time;
        ImageView ivtime;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_exercise, viewGroup, false);
            v.setTag(R.id.tvItemExercise, v.findViewById(R.id.tvItemExercise));
        }
        name = (TextView) v.findViewById(R.id.tvItemExercise);
        rep = (TextView) v.findViewById(R.id.tvItemExerciseRep);
        time = (TextView) v.findViewById(R.id.tvItemTime);
        ivtime = (ImageView) v.findViewById(R.id.ivItemExercise);
        final Item item = getItem(i);
        name.setText(item.name);
        rep.setText("Repetitions " +String.valueOf(this.repetitions.get(i)));
        time.setVisibility(View.GONE);
        ivtime.setVisibility(View.GONE);


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Exercise with items parameters
                Exercise ex = MainActivity.exerciseList.get(item.id);
                //Step 2: Set currentExercise to clicked exercise
                MainActivity.currentExercise = ex;
                //Step 3: Go to exercise fragment
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentExerciseInfo.newInstance())
                        .commit();
            }
        });

        return v;
    }

    private static class Item {
        private int id;
        private String name;
        private Drawable picture;
        private Movie gif;

        Item(int id, String name, Drawable picture, Movie gif) {
            this.id = id;
            this.name = name;
            this.picture = picture;
            this.gif = gif;
        }
    }
}