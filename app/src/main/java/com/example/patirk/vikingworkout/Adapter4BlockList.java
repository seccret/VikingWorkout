package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Adapter4BlockList extends BaseAdapter {
    // Keep all Images in array
    public final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;
    public static List<Block> blockList = null;

    // Constructor
    public Adapter4BlockList(Context c, List<Block> blockList) {
        mInflater = LayoutInflater.from(c);
        Adapter4BlockList.blockList = new ArrayList<Block>();
        for (Block b : blockList) {
                int id = b.getId();
                String name = b.getName();
                mItems.add(new Item(id, name));

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

        v = mInflater.inflate(R.layout.item_block, viewGroup, false);

        final TextView tvname = (TextView) v.findViewById(R.id.tvItemBlock);
        final Item item = getItem(i);
        tvname.setText(item.name);

        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = FragmentAddWorkout.index;
                List<Block> bList = FragmentAddWorkout.b;
                bList.set(index, MainActivity.getBlockByID(item.id));
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentBlockList.fragment)
                        .commit();
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentAddWorkout.fragment)
                        .commit();
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentAddWorkout.newInstance(bList.get(0), bList.get(1), bList.get(2), bList.get(3)))
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