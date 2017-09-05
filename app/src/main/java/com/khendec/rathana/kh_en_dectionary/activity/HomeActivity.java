package com.khendec.rathana.kh_en_dectionary.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.adapter.HomePagerAdapter;
import com.khendec.rathana.kh_en_dectionary.base.BaseActivity;
import com.khendec.rathana.kh_en_dectionary.callback.AdpaterCallBack;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.DictionaryFragment;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static android.util.Log.INFO;
import static it.sephiroth.android.library.bottomnavigation.MiscUtils.log;

public class HomeActivity extends BaseActivity implements BottomNavigation.OnMenuItemSelectionListener,
        AdpaterCallBack.RecentCallBack
{

    static final String TAG = MainActivity.class.getSimpleName();

    private HomePagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get View Reference object
        initializeBottomNavigation(savedInstanceState);
        initializeUI(savedInstanceState);
    }

    private void initializeBottomNavigation(Bundle savedInstanceState) {
        if(null != savedInstanceState){
            getBottomNavigation().setDefaultSelectedIndex(0);
        }
    }
    private void initializeUI(Bundle savedInstanceState){
        final ViewPager viewPager=getViewPager();
        if(null!=viewPager){
            getBottomNavigation().setOnMenuChangedListener(new BottomNavigation.OnMenuChangedListener() {
                @Override
                public void onMenuChanged(BottomNavigation bottomNavigation) {

                    homePagerAdapter=new HomePagerAdapter(getSupportFragmentManager(),bottomNavigation.getMenuItemCount());
                    viewPager.setAdapter(homePagerAdapter);
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                        @Override
                        public void onPageSelected(int position) {
                            if(getBottomNavigation().getSelectedIndex()!=position){
                                getBottomNavigation().setSelectedIndex(position,false);
                            }
                        }
                        @Override
                        public void onPageScrollStateChanged(int state) { }
                    });
                }
            });
        }
    }

    @Override
    public void onMenuItemSelect(@IdRes int item, int position, boolean b) {
        if(b){
            getBottomNavigation().getBadgeProvider().remove(item);
            if(null!=getViewPager()){
                getViewPager().setCurrentItem(position);
            }
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {
        log(TAG, INFO, "onMenuItemSelect(" + i + ", " + i1 + ", " + b + ")");
        if(b){
            FragmentManager manager=getSupportFragmentManager();
            DictionaryFragment fragment= (DictionaryFragment) manager.findFragmentById(R.id.dictionary_fragment);
            if(null!=fragment){
                getViewPager().setCurrentItem(0);
            }
        }
    }

    @Override
    public void onClickListener(int position) {
        Log.e("ooo_click",position+"");
    }
}
