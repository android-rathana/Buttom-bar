package com.khendec.rathana.kh_en_dectionary.base;

import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.khendec.rathana.kh_en_dectionary.R;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by ratha on 04-Sep-17.
 */

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener{

    private BottomNavigation bottomNavigation;
    private ViewPager viewPager;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        bottomNavigation= (BottomNavigation) findViewById(R.id.home_bottom_nav_view);
        viewPager= (ViewPager) findViewById(R.id.home_view_pager);
        if (null != bottomNavigation) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
            bottomNavigation.setOnMenuItemClickListener(this);
            bottomNavigation.setDefaultTypeface(typeface);
        }

    }

    public ViewPager getViewPager(){
         return viewPager;
    }
    public BottomNavigation getBottomNavigation(){
        if(null == bottomNavigation){
            bottomNavigation= (BottomNavigation) findViewById(R.id.home_bottom_nav_view);
        }
        return bottomNavigation;
    }

}
