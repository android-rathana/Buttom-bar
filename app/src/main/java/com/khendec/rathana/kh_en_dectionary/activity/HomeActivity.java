package com.khendec.rathana.kh_en_dectionary.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.adapter.HomePagerAdapter;
import com.khendec.rathana.kh_en_dectionary.base.BaseActivity;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.DictionaryFragment;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.FavoriteFragment;
import com.khendec.rathana.kh_en_dectionary.fragment.tab.RecentFragment;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static android.R.attr.fragment;
import static android.R.attr.fragmentEnterTransition;
import static android.util.Log.INFO;
import static it.sephiroth.android.library.bottomnavigation.MiscUtils.log;

public class HomeActivity extends BaseActivity implements BottomNavigation.OnMenuItemSelectionListener
{

    static final String TAG = MainActivity.class.getSimpleName();
    static final int DICTIONARY_FRAGMGNET = 0;
    static final int RECENT_FRAGMGNET = 1;
    static final int FAVORITE_FRAGMGNET = 2;

    private HomePagerAdapter homePagerAdapter;
    private static int  fragmentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get View Reference object
        initializeBottomNavigation(savedInstanceState);
        initializeUI(savedInstanceState);
        handleIntent(getIntent());
    }

    private void initializeBottomNavigation(Bundle savedInstanceState) {
        if(null != savedInstanceState){
            getBottomNavigation().setDefaultSelectedIndex(0);
        }
    }
    private void initializeUI(Bundle savedInstanceState){
        ViewPager viewPager = getViewPager();
        if(null!=viewPager){
            getBottomNavigation().setOnMenuChangedListener(new BottomNavigation.OnMenuChangedListener() {
                @Override
                public void onMenuChanged(BottomNavigation bottomNavigation) {
                    homePagerAdapter=new HomePagerAdapter(getSupportFragmentManager(),bottomNavigation.getMenuItemCount());
                    homePagerAdapter.setFragment(DictionaryFragment.getInstance("Dictionary"));
                    homePagerAdapter.setFragment(RecentFragment.getInstance());
                    homePagerAdapter.setFragment(FavoriteFragment.getInstance());
                    viewPager.setAdapter(homePagerAdapter);
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                        @Override
                        public void onPageSelected(int position) {
                            Log.e("uuu->page change" ,position+"");
                            fragmentPosition=position;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager= (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem=menu.findItem(R.id.tv_dic_search);
        SearchView searchView=null;
        if(null!=searchItem){
            searchView= (SearchView) MenuItemCompat.getActionView(searchItem);
        }
        if(null!=searchView){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            searchView.setIconifiedByDefault(true);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doActiveFragment(getViewPager(),newText);
                return true;
            }
        });

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(intent.ACTION_SEARCH.equals(intent.getAction())){
            String query=intent.getStringExtra(SearchManager.QUERY);
            doActiveFragment(getViewPager(),query);
        }
    }

    private void doActiveFragment(ViewPager viewPager ,String searchQuery){
        if(viewPager.getAdapter() instanceof HomePagerAdapter){

            HomePagerAdapter adapter= (HomePagerAdapter) viewPager.getAdapter();
            if(adapter.getItem(viewPager.getCurrentItem()) instanceof DictionaryFragment){
                DictionaryFragment fragment= (DictionaryFragment) adapter.getItem(viewPager.getCurrentItem());
                fragment.setFilter(searchQuery);
                fragment.wordSearch(searchQuery);
            }else if(adapter.getItem(viewPager.getCurrentItem()) instanceof RecentFragment){
                RecentFragment fragment= (RecentFragment) adapter.getItem(viewPager.getCurrentItem());
                fragment.setFilter(searchQuery);
            }else if(adapter.getItem(viewPager.getCurrentItem()) instanceof FavoriteFragment){
                FavoriteFragment fragment= (FavoriteFragment) adapter.getItem(viewPager.getCurrentItem());
                fragment.setFilter(searchQuery);
            }
        }

    }
}
