package com.khendec.rathana.kh_en_dectionary.event;

import com.khendec.rathana.kh_en_dectionary.entity.Word;

/**
 * Created by ratha on 06-Sep-17.
 */

public class RecentFragmentEvent {
    private Word word;
    private int itemPosition;

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public RecentFragmentEvent(Word word , int position){
        this.word=word;
        this.itemPosition=position;
    }
    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
