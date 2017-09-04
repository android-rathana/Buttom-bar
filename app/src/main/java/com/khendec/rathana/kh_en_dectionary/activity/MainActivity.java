package com.khendec.rathana.kh_en_dectionary.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.adapter.tab.TabPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;


    private TabPagerAdapter tabPagerAdapter;

    public MainActivity()
    {
        this.tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager(),MainActivity.this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpBottomNavigation();
    }



    /*
       **@ setup tabLayout with ViewPager
     */
    private void setUpBottomNavigation()
    {
        ViewPager viewPager= (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(tabPagerAdapter);

        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0 ;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab=tabLayout.getTabAt(i);
            tab.setCustomView(tabPagerAdapter.getTabView(i));
        }

        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

}
