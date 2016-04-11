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

import org.mortbay.jetty.Main;

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
    LayoutInflater mLayoutInflater;
    List<Week> weekList;
    List<Day> dayList;
    List<Day> dayList2;
    int weekPosition = 0;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dayList = MainActivity.getDays();
        weekList = MainActivity.getWeeks();
        }

    @Override
    public int getCount() {
        return dayList.size();
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
        int dayIndex = position%7;
        Toast.makeText(MainActivity.mainActivity,String.valueOf(dayIndex), Toast.LENGTH_SHORT).show();
        int weekIndex = -1;
        int tempPosition = position;
        while (tempPosition>=0){
            weekIndex++;
            tempPosition -= 7; // == tempPosition = tempPosition - 7;
        }
        Toast.makeText(MainActivity.mainActivity,String.valueOf(weekIndex), Toast.LENGTH_SHORT).show();

        dayList2 = weekList.get(weekIndex).getDays();
        Date date = new Date(((long) dayList2.get(dayIndex).getDateId() *24*60*60*1000));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String text = format.format(date);
        dateAgenda.setText(text);


        List<Integer> thisMonth = new ArrayList<>();
        int epochStartOfMonth = ExternalFunctions.getStartEpochOfMonth(dayList2.get(dayIndex).getDateId());
        int epochEndOfMonth = ExternalFunctions.getEndEpochOfMonth(dayList2.get(dayIndex).getDateId());
        for (int i = epochStartOfMonth; i<epochEndOfMonth ; i++) {
            thisMonth.add(i);
        }

        List<Day> dayList3 = new ArrayList<>();
        for (int dayId : thisMonth) {
            Day d = MainActivity.getDayByID(dayId);
            dayList3.add(d);
        }


        AdapterProfileWorkout AI = new AdapterProfileWorkout(itemView.getContext(), dayList3.get(dayIndex).getWorkouts());
        agenda.setAdapter(AI);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
