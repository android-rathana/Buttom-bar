package com.khendec.rathana.kh_en_dectionary.fragment.tab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.adapter.DictionaryRecyclerViewAdapter;
import com.khendec.rathana.kh_en_dectionary.entity.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 01-Sep-17.
 */

public class DictionaryFragment extends Fragment {

    private RecyclerView wordRecyclerView;
    private DictionaryRecyclerViewAdapter dictionaryRecyclerViewAdapter;
    private List<Word> words;
    public DictionaryFragment(){}

    public static DictionaryFragment newInstance(String fragmentName){
        DictionaryFragment fragment=new DictionaryFragment();
        Bundle args=new Bundle();
        args.putString("FRAGMENT_NAME",fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    private void inJetObjects(ViewGroup viewGroup){
        words=new ArrayList<>();
        dictionaryRecyclerViewAdapter= new DictionaryRecyclerViewAdapter(viewGroup,getContext(),words);
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
        inJetObjects(container);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wordRecyclerView.setHasFixedSize(true);
        wordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        wordRecyclerView.setAdapter(dictionaryRecyclerViewAdapter);
        updateAdapter();
    }

    public void updateAdapter(){
        for(int i=0 ;i<10 ;i++){
            words.add(new Word("Book"+i));
        }
        Log.e("ooooF:",words.size()+"");
        dictionaryRecyclerViewAdapter.notifyDataSetChanged();
    }
}
