package com.example.patirk.vikingworkout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivia on 2016-01-02.
 */
public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    List<Integer> mAgendaDates;
    LayoutInflater mLayoutInflater;
    List<Week> weekList;
    int weekPosition = 0;

    public CustomPagerAdapter(Context context, List<Integer> agendaDates) {
        mContext = context;
        mAgendaDates = agendaDates;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        weekList = MainActivity.getWeeks();
        }

    @Override
    public int getCount() {
        return mAgendaDates.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_pager, container, false);
        ListView agenda = (ListView) itemView.findViewById(R.id.lvAgenda);
        TextView dateAgenda = (TextView) itemView.findViewById(R.id.tvAgenda);



        List<Day> dayList = new ArrayList<>();

        for (int dayId : mAgendaDates) {
            Day d = MainActivity.profile.getDayByID(dayId);
            dayList.add(d);
        }


        Date date = new Date(((long) dayList.get(position).getDateId() *24*60*60*1000));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String text = format.format(date);
        dateAgenda.setText(text);

        AdapterProfileWorkout AI = new AdapterProfileWorkout(itemView.getContext(), dayList.get(position).getWorkouts());
        agenda.setAdapter(AI);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
