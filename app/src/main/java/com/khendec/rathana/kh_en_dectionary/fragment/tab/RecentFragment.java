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
import com.khendec.rathana.kh_en_dectionary.adapter.RecentRecyclerViewAdapter;
import com.khendec.rathana.kh_en_dectionary.callback.AdpaterCallBack;
import com.khendec.rathana.kh_en_dectionary.entity.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 01-Sep-17.
 */

public class RecentFragment extends Fragment
{



    private RecyclerView recentRecyclerView;
    private RecentRecyclerViewAdapter recentRecyclerViewAdapter;
    List<Word> words;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recentRecyclerView.setHasFixedSize(true);
        recentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentRecyclerView.setAdapter(recentRecyclerViewAdapter);
        updateAdapter();
    }

    public void updateAdapter(){
        for(int i=0;i<10;i++){
            words.add(new Word("word" +i,"v"));
        }
        recentRecyclerViewAdapter.notifyDataSetChanged();
    }
    private void inJetObjects(ViewGroup viewGroup){
        words=new ArrayList<>();
        recentRecyclerViewAdapter=new RecentRecyclerViewAdapter(viewGroup,getContext(),words);
    }

}
