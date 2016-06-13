package com.example.patirk.vikingworkout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivia on 2016-01-02.
 */
public class Adapter4BlockPager extends PagerAdapter {

    public static Adapter4BlockPager customAdapter = null;
    Context mContext;
    LayoutInflater mLayoutInflater;
    public static AdapterProfileExercises AI;
    List<Integer> blockList;

    public Adapter4BlockPager(Context context, List<Integer> blocks) {
        customAdapter = this;
        mContext = context;
        blockList = blocks;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    @Override
    public int getCount() {
        return blockList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_pager_block, container, false);
        ListView exercises = (ListView) itemView.findViewById(R.id.lvBlock);
        TextView blockname = (TextView) itemView.findViewById(R.id.tvBlock);

        List<Block> blocks = new ArrayList<>();

        for (int blockId : blockList) {
            Block b = MainActivity.getBlockByID(blockId);
            blocks.add(b);
        }

        blockname.setText(blocks.get(position).getName());

        AI = new AdapterProfileExercises(itemView.getContext(), blocks.get(position).getExercises());
        exercises.setAdapter(AI);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
    private static class Item {
        private int id;

        Item(int id) {
            this.id = id;
        }
    }
}
