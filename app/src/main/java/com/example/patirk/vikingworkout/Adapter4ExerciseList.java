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


public class Adapter4ExerciseList extends BaseAdapter {
    // Keep all Images in array
    public final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Block block = null;
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

        final TextView tvname = (TextView) v.findViewById(R.id.tvItemExercise);
        final ImageView time = (ImageView) v.findViewById(R.id.ivItemExercise);
        final Item item = getItem(i);
        tvname.setText(item.name);

        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = FragmentAddSevenW.index;
                List<Exercise> eList = FragmentAddSevenW.e;
                eList.set(index, MainActivity.getExerciseByID(item.id));
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentExerciseList.fragment)
                        .commit();
                MainActivity.fragmentManager.beginTransaction()
                        .remove(FragmentAddSevenW.fragment)
                        .commit();
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentAddSevenW.newInstance(eList.get(0), eList.get(1), eList.get(2), eList.get(3)))
                        .commit();
            }
        });

        time.setOnTouchListener(new View.OnTouchListener() {
              @Override
           public boolean onTouch(View v, MotionEvent event) {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   MainActivity.fragmentManager.beginTransaction()
                            .add(R.id.container, FragmentExerciseInfo.newInstance(), "exInfo")
                           .commit();
                   return true;
               }else if (event.getAction() == MotionEvent.ACTION_UP){
                   MainActivity.fragmentManager.beginTransaction()
                           .remove(FragmentExerciseInfo.fragment)
                            .commit();
               }
               return false;
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