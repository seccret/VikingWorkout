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
public class FragmentCategory extends android.support.v4.app.Fragment {

    public static FragmentCategory newInstance() {
        FragmentCategory fragment = new FragmentCategory();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentCategory() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        TextView cat = (TextView) rootView.findViewById(R.id.tvCategoryHead);
        GridView recommended = (GridView) rootView.findViewById(R.id.gvCategoryTop);
        cat.setText(MainActivity.currentCategory.getName());

       // LinearLayoutManager layoutManager= new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false);
       // RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.gvProgressBrowse);
        //mRecyclerView.setLayoutManager(layoutManager);


        AdapterBrowse AI = new AdapterBrowse(rootView.getContext(), MainActivity.getWorkouts());
        recommended.setAdapter(AI);


        return rootView;
    }
}
