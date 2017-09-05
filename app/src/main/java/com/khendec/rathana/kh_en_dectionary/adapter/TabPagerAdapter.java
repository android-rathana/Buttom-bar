package com.khendec.rathana.kh_en_dectionary.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.DictionaryFragment;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.FavoriteFragment;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.RecentFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ratha on 01-Sep-17.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    List<? extends Object> framgentList;


    public final int PAGE_COUNT=3;
    private final String[] mTabTitle={"Dictionary","Recent","Favorite"};
    private int[] mTabsIcon={
            R.drawable.ic_dictionary,
            R.drawable.ic_recent,
            R.drawable.ic_favorite};

    private Context context;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
        this.framgentList=new ArrayList<>();
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab,null);
        TextView tvTittle= (TextView) view.findViewById(R.id.tab_title);
        tvTittle.setText(mTabTitle[position]);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_icon);
        tabIcon.setImageResource(mTabsIcon[position]);
        return view;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DictionaryFragment();
            case 1: return new RecentFragment();
            case 2: return new FavoriteFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle[position];
    }
}
