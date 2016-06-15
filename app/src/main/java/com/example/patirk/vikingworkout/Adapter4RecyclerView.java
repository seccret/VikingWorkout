package com.example.patirk.vikingworkout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter4RecyclerView extends RecyclerView.Adapter<Adapter4RecyclerView.MyViewHolder> {

    private List<Workout> workoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.tvItemWorkout);
        }
    }


    public Adapter4RecyclerView(List<Workout> workouts) {
        this.workoutList = workouts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout_big, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.title.setText(workout.getName());
        holder.genre.setText(workout.getMuscleGroup());
        holder.year.setText(workout.getMadeBy());
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}