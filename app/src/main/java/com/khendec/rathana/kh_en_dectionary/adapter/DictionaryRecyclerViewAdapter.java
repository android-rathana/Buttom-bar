package com.khendec.rathana.kh_en_dectionary.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.entity.Word;

import java.util.List;

/**
 * Created by ratha on 05-Sep-17.
 */

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.DictionaryViewHolder>
{

    private  List<Word> words;
    private ViewGroup viewGroup;
    public DictionaryRecyclerViewAdapter(ViewGroup viewGroup,Context context,List words)
    {
        this.words=words;
        this.viewGroup=viewGroup;
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_card_view,parent,false);
        DictionaryViewHolder holder=new DictionaryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DictionaryViewHolder holder, int position) {
        Log.e("ooooA",words.size()+"");
        if(words!=null){
            Word word =words.get(position);
            if(word!=null){
                holder.tvWord.setText(word.getWord());
            }
        }else{
            Snackbar snackbar = Snackbar.make(viewGroup, "No word found.", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public int getItemCount() {
        return words.size();
    }


    static class DictionaryViewHolder extends RecyclerView.ViewHolder{
        TextView tvWord;
        public DictionaryViewHolder(View itemView) {
            super(itemView);
            tvWord= (TextView) itemView.findViewById(R.id.search_word);
        }
    }

}
