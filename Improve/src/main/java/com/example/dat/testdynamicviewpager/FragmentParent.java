package com.example.dat.testdynamicviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DAT on 9/1/2015.
 */
public class FragmentParent extends Fragment {
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_parent, container, false);
        mViewPager = (ViewPager) rootview.findViewById(R.id.my_viewpager);
        mAdapter = new ViewPagerAdapter(getFragmentManager(), getActivity());
        mViewPager.setAdapter(mAdapter);
        final AddTabByViewPagerTabLayout tabLayout = (AddTabByViewPagerTabLayout) rootview.findViewById(R.id.my_tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        //TODO : the below not work !!??  maybe support library Version is different ? i use 24.X ~27.X !! so i block below
//        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                super.onTabSelected(tab);
//                mViewPager.setCurrentItem(tab.getPosition());
//                selectedTabPosition = mViewPager.getCurrentItem();
//                Log.d("Selected", "Selected " + tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                super.onTabUnselected(tab);
//                Log.d("Unselected", "Unselected " + tab.getPosition());
//
//
//            }
//        });

        //FIXME new API say use addOnTabSelectedListener not setOnTabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                Log.d("Selected", "Selected " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("Unselected", "Unselected " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });
        return rootview;
    }

    public void addPage(String pagename) {
        mAdapter.addFragment(pagename);
        mAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(mAdapter.getCount() - 1);
    }
}
