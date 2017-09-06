package com.khendec.rathana.kh_en_dectionary.fragment.tab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.adapter.RecentRecyclerViewAdapter;
import com.khendec.rathana.kh_en_dectionary.base.BaseActivity;
import com.khendec.rathana.kh_en_dectionary.base.BaseFragment;
import com.khendec.rathana.kh_en_dectionary.entity.Word;
import com.khendec.rathana.kh_en_dectionary.event.RecentFragmentEvent;
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

public class RecentFragment extends BaseFragment
{

    private RecyclerView recentRecyclerView;
    private RecentRecyclerViewAdapter recentRecyclerViewAdapter;
    private List<Word> words;
    private WordRepository wordRepo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_recent,container,false);
        recentRecyclerView= (RecyclerView) rootView.findViewById(R.id.recent_recycler_view);
        inJetObjects(container);
        return rootView;
    }

    @Override
    protected void setUp(View view, Bundle savedInstanceState) {
        recentRecyclerView.setHasFixedSize(true);
        recentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentRecyclerView.setAdapter(recentRecyclerViewAdapter);
        updateAdapter();
    }

    @Override
    protected void onInject(BaseActivity baseActivity) {

    }

    public void updateAdapter(){
        //words=wordRepo.getAll();
        //Log.e("uuuu",words.size()+"");
        for(Word w: wordRepo.getAll()){
            words.add(w);
        }
        recentRecyclerViewAdapter.notifyDataSetChanged();
    }
    private void inJetObjects(ViewGroup viewGroup){
        words=new ArrayList<>();
        wordRepo=MemoryRepositoy.INSTANCE.getInstance();
        recentRecyclerViewAdapter=new RecentRecyclerViewAdapter(viewGroup,getBaseAvtivity(),words);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecentItemButtonFavoriteClick(RecentFragmentEvent event){
        Word word= event.getWord();
        for(int i=0;i<words.size();i++){
            if(words.get(i).getId()== word.getId()){
                Word w = words.get(i);
                if(word.isFavorite()){
                    w.setFavorite(false);
                }else{
                    w.setFavorite(true);
                }
                recentRecyclerViewAdapter.notifyItemChanged(event.getItemPosition());
            }
        }
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
}
