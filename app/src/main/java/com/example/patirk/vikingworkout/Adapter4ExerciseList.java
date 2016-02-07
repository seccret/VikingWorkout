package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Adapter4ExerciseList extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;
    public static List<Exercise> exerciseList = null;

    // Constructor
    public Adapter4ExerciseList(Context c, List<Exercise> exerciseList) {
        mInflater = LayoutInflater.from(c);
        Adapter4ExerciseList.exerciseList = new ArrayList<Exercise>();
        for (Exercise w : exerciseList) {
                int id = w.getId();
                String name = w.getName();
                Drawable image = w.getPicture();
                mItems.add(new Item(id, name, image));

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

        v = mInflater.inflate(R.layout.item_exercise, viewGroup, false);
        v.setTag(R.id.tvItemExercise, v.findViewById(R.id.tvItemExercise));

        name = (TextView) v.findViewById(R.id.tvItemExercise);
        image = (ImageButton) v.findViewById(R.id.ibItemExercise);
        final Item item = getItem(i);
        name.setText(item.name);

        image.setBackground(item.picture);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentExerciseList.fragment)
                        .commit();
            }
        });

        return v;
    }

    private static class Item {
        public final int id;
        public final String name;
        public final Drawable picture;

        Item(int id, String name, Drawable picture) {
            this.id = id;
            this.name = name;
            this.picture = picture;
        }
    }
}