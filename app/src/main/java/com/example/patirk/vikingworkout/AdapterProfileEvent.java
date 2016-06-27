package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AdapterProfileEvent extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> nItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterProfileEvent(Context c, List<Integer> eventList) {
        mInflater = LayoutInflater.from(c);

        for (int eventId : eventList) {
            Event e = MainActivity.getEventByID(eventId);
            int id = e.getEventId();
            Workout workout = e.getEventWorkout();
            long time = e.getTime();
            String performed = e.getPerformed();
            nItems.add(new Item(id, workout, time, performed));
        }
    }

    public int getCount() {
        return nItems.size();
    }


    public Item getItem(int position) {
        return nItems.get(position);
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
        TextView performed;
        TextView time;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_event, viewGroup, false);
            v.setTag(R.id.tvItemEvent, v.findViewById(R.id.tvItemEvent));
        }
        name = (TextView) v.findViewById(R.id.tvItemEvent);
        muscleGroup = (TextView) v.findViewById(R.id.tvEventMusclegroup);
        madeBy = (TextView) v.findViewById(R.id.tvEventMadeBy);
        performed = (TextView) v.findViewById(R.id.tvEventPerformed);
        time = (TextView) v.findViewById(R.id.tvEventTime);
        final Item item = getItem(i);
        name.setText(item.workout.getName());
        muscleGroup.setText(item.workout.getMuscleGroup());
        madeBy.setText(item.workout.getMadeBy());
        performed.setText(item.performed);
        time.setText(String.valueOf(item.time));

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Workout with items parameters
                Workout wo = MainActivity.profile.getWorkoutByID(item.workout.getId());
                //Step 2: Set currentWorkout to clicked workout
                MainActivity.currentWorkout = wo;
                //Step 3: Go to block fragment
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
                Event ev = MainActivity.getEventByID(item.id);
                MainActivity.currentEvent = ev;
                Workout wo = MainActivity.getWorkoutByID(item.workout.getId());
                MainActivity.currentWorkout = wo;
                MainActivity.fragmentManager.beginTransaction()
                        .add(R.id.container, FragmentRemoveFrom.newInstance())
                        .commit();

                return true;
            }
        }

        );
        return v;
    }

    private static class Item {
        public final int id;
        public final Workout workout;
        public final long time;
        public final String performed;

        Item(int id, Workout workout, long time, String performed) {
            this.id = id;
            this.workout = workout;
            this.time = time;
            this.performed = performed;
        }
    }

}