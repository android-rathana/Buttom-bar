package com.khendec.rathana.kh_en_dectionary;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private int[] mTabsIcon={
            R.drawable.ic_dictionary,
            R.drawable.ic_recent,
            R.drawable.ic_favorite};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewPager();
    }

    private void setUpViewPager(){
        ViewPager viewPager= (ViewPager) findViewById(R.id.view_pager);

    }

}
