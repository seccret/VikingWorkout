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


public class Adapter4AddSevenWorkout extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Block block = null;
    private static FragmentAddSevenW parent;

    // Constructor
    public Adapter4AddSevenWorkout(Context c, FragmentAddSevenW parentFragment, List<Exercise> exerciseList) {
        mInflater = LayoutInflater.from(c);
        parent = parentFragment;

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
        final TextView rep = (TextView) v.findViewById(R.id.tvItemGExerciseRep);
        final LinearLayout changeRep = (LinearLayout) v.findViewById(R.id.llChangeRep);
        final ImageView reduseRep = (ImageView) v.findViewById(R.id.ivReduseRep);
        final ImageView increaseRep = (ImageView) v.findViewById(R.id.ivIncreaseRep);
        final TextView currentRep = (TextView) v.findViewById(R.id.tvCurrentRep);
        final Item item = getItem(i);
        name.setText(item.name);

        //Fix size of Mipmap
        if (item.id != -1) {
            image.setBackground(item.picture);
        } else{
            image.setBackgroundResource(R.drawable.abc);
        }

        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changeRep.getVisibility() == View.GONE){
                    changeRep.setVisibility(View.VISIBLE);
                }else{
                    changeRep.setVisibility(View.GONE);
                }
            }
        });

        increaseRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int increase = Integer.valueOf(String.valueOf(currentRep.getText()))+1;
                currentRep.setText(String.valueOf(increase));
                parent.newRepetitions.set(clicked, increase);
            }
        });

        reduseRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int reduse = Integer.valueOf(String.valueOf(currentRep.getText()))-1;
                currentRep.setText(String.valueOf(reduse));
                parent.newRepetitions.set(clicked, reduse);
            }
        });

        currentRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeRep.setVisibility(View.GONE);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAddSevenW.index = clicked;
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentExerciseList.newInstance(),"exList")
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