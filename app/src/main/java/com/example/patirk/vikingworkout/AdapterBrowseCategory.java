package com.example.patirk.vikingworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AdapterBrowseCategory extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private static Workout workout = null;

    // Constructor
    public AdapterBrowseCategory(Context c, List<Category> categoryList) {
        mInflater = LayoutInflater.from(c);

        for (Category cat : categoryList) {
            int id = cat.getId();
            String name = cat.getName();
            mItems.add(new Item(id, name,2));
            //this.block = w;
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
        final ImageView image;
        final RelativeLayout rv;
        final int categoryID = i;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_category, viewGroup, false);
        }
        name = (TextView) v.findViewById(R.id.tvItemCategory);
        image = (ImageView) v.findViewById(R.id.ibItemCategory);
        rv = (RelativeLayout) v.findViewById(R.id.rvItemCategory);
        final Item item = getItem(i);
        name.setText(item.name);

        //Fix size of Mipmap
        if (item.picture == 1) {
            image.setBackgroundResource(R.drawable.workout1);
        } else if (item.picture == 2) {
            image.setBackgroundResource(R.drawable.workout2);
        } else if (item.picture == 3) {
            image.setBackgroundResource(R.drawable.workout3);
        }

        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1: Create new Workout with items parameters
                Category ca = MainActivity.getCategoryByID(categoryID);
                //Step 2: Set currentWorkout to clicked workout

                MainActivity.currentCategory = ca;
                //Step 3: Go to block fragment
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.container, FragmentCategory.newInstance())
                        .commit();
            }
        });

        return v;
    }

    private static class Item {
        public final int id;
        public final String name;
        public final int picture;

        Item(int id, String name, int picture) {
            this.id = id;
            this.name = name;
            this.picture = picture;
        }
    }
}