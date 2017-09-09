package com.khendec.rathana.kh_en_dectionary.fragment.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.activity.MainActivity;
import com.khendec.rathana.kh_en_dectionary.adapter.FavoriteRecyclerViewAdapter;
import com.khendec.rathana.kh_en_dectionary.base.BaseActivity;
import com.khendec.rathana.kh_en_dectionary.base.BaseFragment;
import com.khendec.rathana.kh_en_dectionary.entity.Word;
import com.khendec.rathana.kh_en_dectionary.event.FavoritePopupMenuEvent;
import com.khendec.rathana.kh_en_dectionary.event.RecentFragmentEvent;
import com.khendec.rathana.kh_en_dectionary.event.message.SnackbarMessage;
import com.khendec.rathana.kh_en_dectionary.repository.MemoryRepositoy;
import com.khendec.rathana.kh_en_dectionary.repository.memory.WordRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 01-Sep-17.
 */

public class FavoriteFragment extends BaseFragment {

    private List<Word> words;
    private FavoriteRecyclerViewAdapter favoriteRecyclerViewAdapter;
    private RecyclerView favoriteRecyclerView;
    private WordRepository wordRepository;
    private ViewGroup viewGroup;
    private String filter;
    public FavoriteFragment(){}
    public static FavoriteFragment getInstance(){
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View childView=inflater.inflate(R.layout.fragment_favorite,container,false);
        // initialize any objects
        this.viewGroup=container;
        injectObjects(container,childView);
        return childView;
    }

    @Override
    protected void setUp(View view, Bundle savedInstanceState) {
        favoriteRecyclerView.setHasFixedSize(true);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseAvtivity()));
        favoriteRecyclerView.setAdapter(favoriteRecyclerViewAdapter);
        updateAdapter();
    }

    @Override
    protected void onInject(BaseActivity baseActivity) {

    }

    public void updateAdapter(){
        for(Word w: wordRepository.getWordByFavorite()){
            words.add(w);
        }
        favoriteRecyclerViewAdapter.notifyDataSetChanged();
    }

    public boolean addToFavorite(Word word ,int position){
        if(null!=word){
            if(word.isFavorite()){
                words.add(0,word);
                favoriteRecyclerViewAdapter.notifyItemInserted(0);
                SnackbarMessage.getMessage(viewGroup,"You saved word : \""+ word.getWord() +"\" to your favorites.",true);
            }else {
                int pos=removeFromList(words,word);
                favoriteRecyclerViewAdapter.notifyItemRemoved(pos);
                SnackbarMessage.getMessage(viewGroup,"You removed word : \""+ word.getWord() +"\" from your favorites.",true);
            }
            return true;
        }else{
            return false;
        }
    }
    private void injectObjects(ViewGroup viewGroup,View view){
        words=new ArrayList<>();
        favoriteRecyclerViewAdapter=new FavoriteRecyclerViewAdapter(getBaseAvtivity(),viewGroup,words);
        favoriteRecyclerView= (RecyclerView) view.findViewById(R.id.rc_favorite);
        wordRepository= MemoryRepositoy.INSTANCE.getInstance();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecentItemButtonFavoriteClick(RecentFragmentEvent event){

        boolean isSuccess =addToFavorite(event.getWord(),event.getItemPosition());
        if(!isSuccess) {
            SnackbarMessage.getMessage(viewGroup,"The word : \"" + event.getWord().getWord() + "\" is unsuccess to save to your favorites.Please try it again!",true);
        }
    }

    private  int removeFromList(List<Word> list, Word word){
        for(int i=0;i<list.size(); i++){
            if(list.get(i).getId()==word.getId()){
                list.remove(word);
                return i;
            }
        }
        return 0;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void FavoritePopupMenuClick(FavoritePopupMenuEvent event){
        Intent intent=new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
