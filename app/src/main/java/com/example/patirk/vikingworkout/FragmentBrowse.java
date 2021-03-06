package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by olivia on 2015-09-07.
 */
public class FragmentBrowse extends android.support.v4.app.Fragment {

    public static FragmentBrowse newInstance() {
        FragmentBrowse fragment = new FragmentBrowse();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentBrowse() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        TextView cat = (TextView) rootView.findViewById(R.id.tvBrowseCat);
        GridView category = (GridView) rootView.findViewById(R.id.gvBrowseCategory);
        Block block = MainActivity.currentBlock;

        AdapterImage AI = new AdapterImage(rootView.getContext(), MainActivity.workouts);
        category.setAdapter(AI);


        return rootView;
    }
}
