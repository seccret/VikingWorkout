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


public class AdapterImage3 extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterImage3(Context c, List<Weekday> weekdayList) {
        mInflater = LayoutInflater.from(c);

        for (Weekday w : weekdayList) {
            String nameOfDay = w.getName();
            String nameOfWorkout = w.getWorkout().getName();
            int picture = w.getPicture();
            mItems.add(new Item(nameOfDay, picture, nameOfWorkout));
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
        ImageButton image;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_day, viewGroup, false);
            v.setTag(R.id.tvItemDay, v.findViewById(R.id.tvItemDay));
        }

        TextView weekday = (TextView) v.findViewById(R.id.tvItemDay);
        TextView workoutName = (TextView) v.findViewById(R.id.tvItemWorkout);
        image = (ImageButton) v.findViewById(R.id.ibItemChange);
        final Item item = getItem(i);
        weekday.setText(item.name);
        workoutName.setText(item.name2);

        if (item.picture == 1) {
            image.setBackgroundResource(R.drawable.workout1);
        } else if (item.picture == 2) {
            image.setBackgroundResource(R.drawable.workout2);
        } else if (item.picture == 3) {
            image.setBackgroundResource(R.drawable.workout3);
        }

        return v;
    }

    private static class Item {
        public final String name, name2;
        public final int picture;

        Item(String name, int picture, String name2) {
            this.name = name;
            this.name2 = name2;
            this.picture = picture;
        }
    }
}