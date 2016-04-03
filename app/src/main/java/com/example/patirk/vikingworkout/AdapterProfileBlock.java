package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AdapterProfileBlock extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterProfileBlock(Context c, List<Block> blockList) {
        mInflater = LayoutInflater.from(c);

        for (Block b : blockList) {
            int id = b.getId();
            String name = b.getName();
            String musclegroup = b.getMuscleGroup();
            mItems.add(new Item(id, name, musclegroup));
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
        TextView muscleGroup;
        LinearLayout entireRow;
        ImageView image;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_workout, viewGroup, false);
            v.setTag(R.id.tvItemWorkout, v.findViewById(R.id.tvItemWorkout));
        }
        entireRow = (LinearLayout) v.findViewById(R.id.llItemWorkout);
        name = (TextView) v.findViewById(R.id.tvItemWorkout);
        muscleGroup = (TextView) v.findViewById(R.id.tvWorkoutMusclegroup);
        final Item item = getItem(i);
        name.setText(item.name);
        muscleGroup.setText(item.musclegroup);

        entireRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Find block from profile
                Block bo = MainActivity.profile.getBlockByID(item.id);
                //Step 2: Set currentWorkout to clicked workout
                MainActivity.currentBlock = bo;
                //Step 3: Go to workout fragment
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentBlock.newInstance())
                        .commit();
            }
        });

        entireRow.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick (View v){
                if (MainActivity.profile.containBlock(item.id)) {
                    MainActivity.lastLongClick = item.id;
                    MainActivity.profile.removeBlock(item.id);
                } else {
                    Toast.makeText(MainActivity.mainActivity, item.name + " block not found in profile", Toast.LENGTH_SHORT).show();
                    }
                return false;
            }
        });
        return v;
    }

    private static class Item {
        public final int id;
        public final String name;
        public final String musclegroup;

        Item(int id, String name, String musclegroup) {
            this.id = id;
            this.name = name;
            this.musclegroup = musclegroup;
        }
    }
}