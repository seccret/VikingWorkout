package com.example.patirk.vikingworkout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Adapter4AddSevenWorkout extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public Adapter4AddSevenWorkout(Context c, List<Exercise> exerciseList) {
        mInflater = LayoutInflater.from(c);

        for (Exercise w : exerciseList) {
            if (w.getId()==-1) {
                int id = -1;
                String name ="Add Exercise";
                Drawable image = null;
                mItems.add(new Item(id, name, image));
            } else {
                int id = w.getId();
                String name = w.getName();
                Drawable picture = w.getPicture();
                mItems.add(new Item(id, name, picture));
                //this.workout = w;
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

            v = mInflater.inflate(R.layout.item_workout_big, viewGroup, false);
            v.setTag(R.id.tvItemWorkout, v.findViewById(R.id.tvItemWorkout));

        name = (TextView) v.findViewById(R.id.tvItemWorkout);
        image = (ImageView) v.findViewById(R.id.ibItemWorkout);
        final Item item = getItem(i);
        name.setText(item.name);

        //Fix size of Mipmap
        if (item.id != -1) {
            image.setBackground(item.picture);
        } else{
            image.setBackgroundResource(R.drawable.abc);
        }

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentExerciseList.newInstance(),"exList")
                        .commit();
            }
        });

        name.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick (View v){
                if (MainActivity.profile.contains(item.id)) {
                    MainActivity.lastLongClick = item.id;
                } else {
                    MainActivity.profile.addWorkout(item.id);
                    Toast.makeText(MainActivity.mainActivity, item.name + " workout added to profile", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        }

        );
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