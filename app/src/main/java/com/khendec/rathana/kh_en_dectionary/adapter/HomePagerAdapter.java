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

public class HomePagerAdapter extends FragmentPagerAdapter {

    private int pageCount;
    private final String[] mTabTitle={"Dictionary","Recent","Favorite"};
    private List<Fragment> fragments;
    public HomePagerAdapter(FragmentManager fm,int pageCount) {
        super(fm);
        this.pageCount=pageCount;
        fragments=new ArrayList<>();
    }

    public void setFragment(Fragment fragment){
        if (null !=fragment) fragments.add(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle[position];
    }
}
