package com.khendec.rathana.kh_en_dectionary.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.entity.Word;
import com.khendec.rathana.kh_en_dectionary.event.FavoritePopupMenuEvent;
import com.khendec.rathana.kh_en_dectionary.event.RecentFragmentEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ratha on 05-Sep-17.
 */

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteRecyclerViewAdapter.RecentViewHolder>
{
    private List<Word> words;
    private ViewGroup viewGroup;
    private Context context;

    public FavoriteRecyclerViewAdapter(Context context,ViewGroup viewGroup , List words)
    {
        this.words=words;
        this.viewGroup=viewGroup;
        this.context=context;
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_card_view,parent,false);
        RecentViewHolder holder=new RecentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecentViewHolder holder, final int position) {
        Log.e("ooooA",words.size()+"");
        if(words!=null){
            Word word=words.get(position);
            if(null!=word.getWord()){
                holder.tvWord.setText(word.getWord());
            }

            holder.btnMenu.setOnClickListener(view-> EventBus.getDefault().post(new FavoritePopupMenuEvent(word)));
        }else{
            Snackbar snackbar = Snackbar.make(viewGroup, "No word found.", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    @Override
    public int getItemCount() {
        return words.size();
    }


    static class RecentViewHolder extends RecyclerView.ViewHolder{
        private TextView tvWord;
        private ImageButton btnMenu;
        public RecentViewHolder(View itemView) {
            super(itemView);
            tvWord= (TextView) itemView.findViewById(R.id.word_favorite);
            btnMenu= (ImageButton) itemView.findViewById(R.id.btn_favorite_menu);
        }
    }

}
