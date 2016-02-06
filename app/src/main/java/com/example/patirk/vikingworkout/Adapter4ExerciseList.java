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

    // Constructor
    public Adapter4ExerciseList(Context c, List<Exercise> exerciseList) {
        mInflater = LayoutInflater.from(c);
        InputStream gifInputStream;
        for (Exercise w : exerciseList) {
            if (w.getId()==0) {
                int id = 0;
                String name ="Commandoes";
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.commandos);
                Drawable image = (Drawable) Drawable.createFromStream(gifInputStream, "image");
                mItems.add(new Item(id, name, image));
            } else if (w.getId() == 1) {
                int id = 1;
                String name ="Push up";
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.pushups);
                Drawable image = (Drawable) Drawable.createFromStream(gifInputStream, "image");
                mItems.add(new Item(id, name, image));
            } else if (w.getId()== 2) {
                int id = 2;
                String name ="Sit up";
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.situps);
                Drawable image = (Drawable) Drawable.createFromStream(gifInputStream, "image");
                mItems.add(new Item(id, name, image));
            } else if (w.getId()== 3) {
                int id = 3;
                String name ="Runner";
                gifInputStream = MainActivity.mainActivity.getResources().openRawResource(R.raw.running);
                Drawable image = (Drawable) Drawable.createFromStream(gifInputStream, "image");
                mItems.add(new Item(id, name, image));
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

        v = mInflater.inflate(R.layout.item_exercise, viewGroup, false);
        v.setTag(R.id.tvItemExercise, v.findViewById(R.id.tvItemExercise));

        name = (TextView) v.findViewById(R.id.tvItemExercise);
        image = (ImageButton) v.findViewById(R.id.ibItemExercise);
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
                        .replace(R.id.container, FragmentAddSevenW.newInstance())
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