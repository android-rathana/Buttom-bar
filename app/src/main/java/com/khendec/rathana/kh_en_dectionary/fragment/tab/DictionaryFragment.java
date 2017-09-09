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
import com.khendec.rathana.kh_en_dectionary.adapter.DictionaryRecyclerViewAdapter;
import com.khendec.rathana.kh_en_dectionary.base.BaseActivity;
import com.khendec.rathana.kh_en_dectionary.base.BaseFragment;
import com.khendec.rathana.kh_en_dectionary.entity.Word;
import com.khendec.rathana.kh_en_dectionary.event.RecentFragmentEvent;
import com.khendec.rathana.kh_en_dectionary.repository.MemoryRepositoy;
import com.khendec.rathana.kh_en_dectionary.repository.memory.WordRepository;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 01-Sep-17.
 */

public class DictionaryFragment extends BaseFragment{

    private RecyclerView wordRecyclerView;
    private DictionaryRecyclerViewAdapter dictionaryRecyclerViewAdapter;
    private List<Word> words;
    private String filter;
    private WordRepository wordRepo;
    public DictionaryFragment(){}

    public static DictionaryFragment getInstance(String fragmentName){
        DictionaryFragment fragment=new DictionaryFragment();
        Bundle args=new Bundle();
        args.putString("FRAGMENT_NAME",fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    private void injectObjects(ViewGroup viewGroup){
        words=new ArrayList<>();
        wordRepo= MemoryRepositoy.INSTANCE.getInstance();
        dictionaryRecyclerViewAdapter= new DictionaryRecyclerViewAdapter(viewGroup,getBaseAvtivity(),words);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_dictionry,container,false);
        //get References
        wordRecyclerView= (RecyclerView) rootView.findViewById(R.id.dic_recycler_view);
        injectObjects(container);

        return rootView;
    }

    @Override
    protected void setUp(View view, Bundle savedInstanceState) {
        wordRecyclerView.setHasFixedSize(true);
        wordRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseAvtivity(),LinearLayoutManager.VERTICAL,false));
        wordRecyclerView.setAdapter(dictionaryRecyclerViewAdapter);
        updateAdapter();
    }

    @Override
    protected void onInject(BaseActivity baseActivity) {

    }

    public void updateAdapter(){
        for(Word w : wordRepo.getAll()){
            words.add(w);
        }
        dictionaryRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void wordSearch(String filter){
        List<Word> newList= wordSearch(words,filter);
        //old list to empty
        Log.e("uuu-> search",filter);
        words.clear();
        dictionaryRecyclerViewAdapter.notifyDataSetChanged();
        for(Word w: newList){
            words.add(w);
        }
        dictionaryRecyclerViewAdapter.notifyDataSetChanged();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void doFilter(String query){

    }
}
