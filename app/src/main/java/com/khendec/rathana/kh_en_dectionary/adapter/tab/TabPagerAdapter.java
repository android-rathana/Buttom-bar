package com.khendec.rathana.kh_en_dectionary.adapter.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.khendec.rathana.kh_en_dectionary.MainActivity;

/**
 * Created by ratha on 01-Sep-17.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {


    public final int PAGE_COUNT=3;
    private final String[] mTabTitle={"Dictionary","Recent","Favorite"};

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //View view LayoutInflater.inflate(R)
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
