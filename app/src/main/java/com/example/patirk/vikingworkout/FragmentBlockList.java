package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by olivia on 2016-01-30.
 */
public class FragmentBlockList extends android.support.v4.app.Fragment {
    public static FragmentBlockList fragment;
    public static FragmentBlockList newInstance() {
        fragment = new FragmentBlockList();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentBlockList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_block_list, container, false);
        final ListView blocklist = (ListView) rootView.findViewById(R.id.lvBlockList);



        Adapter4BlockList el = new Adapter4BlockList(MainActivity.mainActivity, MainActivity.profile.getMyBlocks());
        blocklist.setAdapter(el);
        return rootView;
    }
}
