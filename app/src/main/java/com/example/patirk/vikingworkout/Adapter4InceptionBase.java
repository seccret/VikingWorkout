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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.mortbay.jetty.Main;

import java.util.ArrayList;
import java.util.List;


public class Adapter4InceptionBase extends BaseAdapter {
    // Keep all Images in array

    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Block block = null;

    // Constructor
    public Adapter4InceptionBase(Context c, List<Integer> blockList) {
        mInflater = LayoutInflater.from(c);

        for (int blockID : blockList) {
            Block block = MainActivity.blocksList.get(blockID);
            int id = block.getId();
            mItems.add(new Item(id));
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
        if (v == null) {
            v = mInflater.inflate(R.layout.item_listview, viewGroup, false);
        }
        final ImageView arrow = (ImageView) v.findViewById(R.id.ivListViewInception);
        final LinearLayout llBlock = (LinearLayout) v.findViewById(R.id.llListViewInception);
        final ListView lvInception;
        final TextView tvBlockBanner = (TextView) v.findViewById(R.id.tvListViewInception);
        final Item item = getItem(i);
        lvInception = (ListView) v.findViewById(R.id.lvListViewInception);
        Adapter4WorkoutSeven ai = new Adapter4WorkoutSeven(MainActivity.mainActivity, MainActivity.blocksList.get(item.id));
        lvInception.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 300*(MainActivity.blocksList.get(item.id).getExercises().size())));

        lvInception.setAdapter(ai);
        tvBlockBanner.setText(MainActivity.blocksList.get(0).getName());

        llBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvInception.isShown()) {
                    lvInception.setVisibility(View.GONE);
                    arrow.setBackground(MainActivity.mainActivity.getResources().getDrawable(R.drawable.arrow_right));
                } else {
                    lvInception.setVisibility(View.VISIBLE);
                    arrow.setBackground(MainActivity.mainActivity.getResources().getDrawable(R.drawable.arrow_down));
                }

            }
        });

        return v;
    }

    private static class Item {
        private int id;

        Item(int id) {
            this.id = id;
        }
    }
}