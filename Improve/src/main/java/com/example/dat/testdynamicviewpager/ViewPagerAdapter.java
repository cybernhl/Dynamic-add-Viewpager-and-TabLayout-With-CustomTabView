package com.example.dat.testdynamicviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DAT on 8/16/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter implements AddTabByViewPagerTabLayout.ConsumingIconPagerAdapter {
    private final SimpleArrayMap<Integer, String> mDatas = new SimpleArrayMap<Integer, String>();
    private Context mContext;

    public ViewPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        final Bundle bundle = new Bundle();
        bundle.putString("data", mDatas.get(mDatas.keyAt(position)));
        final FragmentChild fragmentChild = new FragmentChild();
        fragmentChild.setArguments(bundle);
        return fragmentChild;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    public void addFragment(String title) {
        mDatas.put(mDatas.size(), title);
    }

    public View getTabItemCustomView(int position) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.custom_tab_item, null);
        final TextView tabItemName = (TextView) view.findViewById(R.id.textViewTabItemName);
        final CircleImageView tabItemAvatar = (CircleImageView) view.findViewById(R.id.imageViewTabItemAvatar);
        tabItemName.setText(mDatas.get(mDatas.keyAt(position)));
        tabItemName.setTextColor(mContext.getResources().getColor(android.R.color.background_light));
        switch (position) {
            case 0:
                tabItemAvatar.setImageResource(R.drawable.gaiduk);
                break;
            case 2:
                tabItemAvatar.setImageResource(R.drawable.avatar);
                break;
            case 4:
                tabItemAvatar.setImageResource(R.drawable.balakin);
                break;
            case 6:
                tabItemAvatar.setImageResource(R.drawable.golovin);
                break;
            case 8:
                tabItemAvatar.setImageResource(R.drawable.ovcharov);
                break;
            case 10:
                tabItemAvatar.setImageResource(R.drawable.solovei);
                break;
            default:
                tabItemAvatar.setImageResource(R.drawable.boy);
                break;
        }
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(mDatas.keyAt(position));
    }

    @Override
    public void bindTab(@NonNull TabLayout.Tab tab, int position, @NonNull ViewGroup parent) {
        //FIXME if support library Version over 25.0.1 will run two times bindTab !!?? so need check CustomView
        final View view = tab.getCustomView();
        if (view == null) {
            tab.setCustomView(getTabItemCustomView(position));
        }
    }
}
