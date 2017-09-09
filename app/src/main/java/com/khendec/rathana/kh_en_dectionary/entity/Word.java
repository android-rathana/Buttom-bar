package com.khendec.rathana.kh_en_dectionary.entity;

/**
 * Created by ratha on 05-Sep-17.
 */

public class Word {
    private int id;
    private String word;
    private String partOfSpeed;
    private boolean isFavorite;

    public Word() {}
    public Word(int id, String word, String partOfSpeed,boolean isFavorite){
        this(id,word,partOfSpeed);
        this.setFavorite(isFavorite);
    }
    public Word(int id, String word, String partOfSpeed) {
        this.id = id;
        this.word = word;
        this.partOfSpeed = partOfSpeed;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, String partOfSpeed) {
        this.word = word;
        this.partOfSpeed = partOfSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeed() {
        return partOfSpeed;
    }

    public void setPartOfSpeed(String partOfSpeed) {
        this.partOfSpeed = partOfSpeed;
    }
}
