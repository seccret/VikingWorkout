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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AdapterGridViewKeypad extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    final FragmentChooseTimeRep views;

    // Constructor
    public AdapterGridViewKeypad(Context c, FragmentChooseTimeRep v) {
        mInflater = LayoutInflater.from(c);
        views = v;
        for (int i=0; i<12; i++) {
            mItems.add(new Item(i));
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
        TextView key;
        final Item item = getItem(i);
        final int keyValue = item.key;
        v = mInflater.inflate(R.layout.item_keypad, viewGroup, false);
        key = (TextView) v.findViewById(R.id.tvItemKey);
        if(keyValue!=99){
            key.setText(String.valueOf(item.key));
            key.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    views.keyDown(keyValue);
                }
            });
        }


        return v;
    }

    private static class Item {
        public final int key;

        Item(int key) {
            if(key<9){
                this.key = key+1;
            }else if(key==10){
                this.key = 0;
            }else {
                this.key = 99;
            }
        }
    }
}