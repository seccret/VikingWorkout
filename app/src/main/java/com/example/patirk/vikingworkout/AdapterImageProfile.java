package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AdapterImageProfile extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterImageProfile(Context c, List<Workout> workoutList) {
        mInflater = LayoutInflater.from(c);

        for (Workout w : workoutList) {
            int id = w.getId();
            String name = w.getName();
            int picture = w.getPicture();
            mItems.add(new Item(id, name, picture));
            //this.workout = w;
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
        if (v == null) {
            v = mInflater.inflate(R.layout.item_workout, viewGroup, false);
            v.setTag(R.id.tvItemWorkout, v.findViewById(R.id.tvItemWorkout));
        }
        name = (TextView) v.findViewById(R.id.tvItemWorkout);
        final Item item = getItem(i);
        name.setText(item.name);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Workout with items parameters
                Workout wo = MainActivity.workouts.get(item.id);
                //Step 2: Set currentWorkout to clicked workout
                MainActivity.currentWorkout = wo;
                //Step 3: Go to workout fragment
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentWorkout.newInstance())
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
        public final int picture;

        Item(int id, String name, int picture) {
            this.id = id;
            this.name = name;
            this.picture = picture;
        }
    }
}