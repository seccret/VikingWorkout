package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Adapter4AddWorkout extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;
    private static FragmentAddWorkout parent;

    // Constructor
    public Adapter4AddWorkout(Context c, FragmentAddWorkout parentFragment, List<Block> blockList) {
        mInflater = LayoutInflater.from(c);
        parent = parentFragment;

        for (Block b : blockList) {
            if (b.getId()==-1) {
                int id = -1;
                String name ="Add Block";
                mItems.add(new Item(id, name));
            } else {
                int id = b.getId();
                String name = b.getName();
                mItems.add(new Item(id, name));
                //this.block = w;
            }
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
        final int clicked = i;

            v = mInflater.inflate(R.layout.item_exercise_gridview, viewGroup, false);
            v.setTag(R.id.tvItemGExercise, v.findViewById(R.id.tvItemGExercise));

        name = (TextView) v.findViewById(R.id.tvItemGExercise);
        image = (ImageButton) v.findViewById(R.id.ibItemGExercise);
        final Item item = getItem(i);
        name.setText(item.name);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAddWorkout.index = clicked;
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentBlockList.newInstance(),"bList")
                        .commit();
            }
        });
        return v;
    }

    private static class Item {
        public final int id;
        public final String name;

        Item(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}