package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AdapterProfileWorkout extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;
    private static Workout wo = null;

    // Constructor
    public AdapterProfileWorkout(Context c, List<Integer> workoutList) {
        mInflater = LayoutInflater.from(c);
        for (int workoutId : workoutList) {
            Workout w = MainActivity.getWorkoutByID(workoutId);
                String name = w.getName();
                String musclegroup = w.getMuscleGroup();
                String madeby = w.getMadeBy();
                mItems.add(new Item(workoutId, name, 1, musclegroup, madeby));
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
        TextView madeBy;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_workout, viewGroup, false);
            v.setTag(R.id.tvItemWorkout, v.findViewById(R.id.tvItemWorkout));
        }
        name = (TextView) v.findViewById(R.id.tvItemWorkout);
        muscleGroup = (TextView) v.findViewById(R.id.tvWorkoutMusclegroup);
        madeBy = (TextView) v.findViewById(R.id.tvWorkoutMadeBy);
        final Item item = getItem(i);
        name.setText(item.name);
        muscleGroup.setText(item.musclegroup);
        madeBy.setText(item.madeby);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.profile.containWorkout(item.id)) {
                    wo = MainActivity.profile.getWorkoutByID(item.id);
                    MainActivity.currentWorkout = wo;
                }else{
                    wo = MainActivity.getWorkoutByID(item.id);
                    MainActivity.currentWorkout = wo;
                }
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentWorkoutSeven.newInstance())
                        .commit();

                //Step 3: Go to workout fragment
         //       String tagtemplate = b.getTemplateTag();
         //       if (tagtemplate.equals("Seven Block")) {
          //          MainActivity.fragmentManager.beginTransaction()
          //                  .replace(R.id.container, FragmentWorkoutSeven.newInstance())
          //                  .commit();
          //      } else if (tagtemplate.equals("List Workout")) {
          //          MainActivity.fragmentManager.beginTransaction()
          //                  .replace(R.id.container, FragmentWorkout.newInstance())
           //                 .commit();
           //     }
            }
        });



        name.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick (View v){
                wo = MainActivity.getWorkoutByID(item.id);
                MainActivity.currentWorkout = wo;
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentAddTo.newInstance())
                        .commit();

                return true;
            }
        }

        );
        return v;
    }

    private static class Item {
        public final int id;
        public final String name;
        public final int picture;
        public final String musclegroup;
        public final String madeby;

        Item(int id, String name, int picture, String musclegroup, String madeby) {
            this.id = id;
            this.name = name;
            this.picture = picture;
            this.musclegroup = musclegroup;
            this.madeby = madeby;
        }
    }
}