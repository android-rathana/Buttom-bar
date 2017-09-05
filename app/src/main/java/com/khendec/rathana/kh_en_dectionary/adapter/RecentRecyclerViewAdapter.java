package com.khendec.rathana.kh_en_dectionary.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khendec.rathana.kh_en_dectionary.R;
import com.khendec.rathana.kh_en_dectionary.callback.AdpaterCallBack;
import com.khendec.rathana.kh_en_dectionary.entity.Word;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ratha on 05-Sep-17.
 */

public class RecentRecyclerViewAdapter extends RecyclerView.Adapter<RecentRecyclerViewAdapter.RecentViewHolder>
{

    private List<Word> words;
    private ViewGroup viewGroup;
    private AdpaterCallBack.RecentCallBack recentCallBack;
    private static boolean saveFavoriteStatus=false;

    public RecentRecyclerViewAdapter(ViewGroup viewGroup, Context context, List words)
    {
        this.words=words;
        this.viewGroup=viewGroup;
        recentCallBack= (AdpaterCallBack.RecentCallBack) context;
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_card_view,parent,false);
        RecentViewHolder holder=new RecentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecentViewHolder holder, final int position) {
        Log.e("ooooA",words.size()+"");
        if(words!=null){
            Word word =words.get(position);
            if(word!=null){
                if(word.getWord()!=null)  holder.tvWord.setText(word.getWord());
                if(word.getPartOfSpeed()!=null) holder.tvPartOfSpeed.setText("."+ word.getPartOfSpeed());
                if(!saveFavoriteStatus) {
                    holder.btnFavorite.setImageResource(R.drawable.ic_favorite_border_light_thin_teal_disable_24dp);
                }else{
                    holder.btnFavorite.setImageResource(R.drawable.ic_favorite_border_light_thin_teal_24dp);
                }
            }

        }else{
            Snackbar snackbar = Snackbar.make(viewGroup, "No word found.", Snackbar.LENGTH_LONG);
            snackbar.show();
        }


        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recentCallBack.onClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static boolean isSaveFavoriteStatus() {
        return saveFavoriteStatus;
    }

    public static void setSaveFavoriteStatus(boolean saveFavoriteStatus) {
        RecentRecyclerViewAdapter.saveFavoriteStatus = saveFavoriteStatus;
    }

    static class RecentViewHolder extends RecyclerView.ViewHolder{
        private TextView tvWord;
        private ImageView btnFavorite;
        private TextView tvPartOfSpeed;
        public RecentViewHolder(View itemView) {
            super(itemView);
            tvWord= (TextView) itemView.findViewById(R.id.search_word);
            tvPartOfSpeed= (TextView) itemView.findViewById(R.id.part_of_speed);
            btnFavorite= (ImageView) itemView.findViewById(R.id.btn_recent_favorite);
        }
    }

}
