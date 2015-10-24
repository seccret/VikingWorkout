package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    // Constructor
    public AdapterImage(Context c, List<Workout> workoutList) {
        mInflater = LayoutInflater.from(c);

        for (Workout w : workoutList) {
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
            image.setBackgroundResource(R.drawable.image1);
        }else if(item.picture==2){
            image.setBackgroundResource(R.drawable.image2);
        }else if(item.picture==3){
            image.setBackgroundResource(R.drawable.image3);
        }

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.mainActivity, item.name, Toast.LENGTH_SHORT).show();
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