package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AdapterProfileExercises extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterProfileExercises(Context c, List<Integer> exerciseList) {
        mInflater = LayoutInflater.from(c);

        for (int exerciseId : exerciseList) {
            Exercise e = MainActivity.getExerciseByID(exerciseId);
            int id = e.getId();
            String name = e.getName();
            mItems.add(new Item(id, name, 1));
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
        if (v == null) {
            v = mInflater.inflate(R.layout.item_exercise, viewGroup, false);
            v.setTag(R.id.tvItemExercise, v.findViewById(R.id.tvItemExercise));
        }
        name = (TextView) v.findViewById(R.id.tvItemExercise);
        final Item item = getItem(i);
        name.setText(item.name);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Workout with items parameters
                Exercise ex = MainActivity.getExerciseByID(item.id);
                //Step 2: Set currentWorkout to clicked workout
                MainActivity.currentExercise = ex;
                //Step 3: Go to block fragment
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentExercise.newInstance())
                        .commit();
            }
        });



        name.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick (View v){
                Exercise ex = MainActivity.getExerciseByID(item.id);
                MainActivity.currentExercise = ex;
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentExerciseInfo.newInstance())
                        .commit();

                return false;
            }
        }

        );
        return v;
    }

    private static class Item {
        public final int id;
        public final String name;
        public final int picture;

        Item(int id, String name, int picture) {
            this.id = id;
            this.name = name;
            this.picture = picture;
        }
    }
}