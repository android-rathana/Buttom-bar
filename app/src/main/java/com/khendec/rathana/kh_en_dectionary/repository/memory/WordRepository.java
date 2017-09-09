package com.khendec.rathana.kh_en_dectionary.repository.memory;

import com.khendec.rathana.kh_en_dectionary.entity.Word;
import com.khendec.rathana.kh_en_dectionary.repository.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 06-Sep-17.
 */

public class WordRepository implements Repository<Word>{

    List<Word> words =new ArrayList<>();

    public WordRepository(){
        for(int i=0;i<10;i++){
            if(i%2==0){
                words.add(new Word(i,"word" +i,"v",true));
            }else {
                words.add(new Word(i,"word" +i,"v"));
            }
        }
    }
    @Override
    public List<Word> getAll() {
        return words;
    }

    @Override
    public Word getOne(Word word) {
        for(Word w : words){
            if (w.getId()==word.getId()){
                return w;
            }
        }
        return null;
    }

    @Override
    public void add(Word word) {
        if(null!=word){
            words.add(word);
        }
    }

    @Override
    public boolean update(Word oldT, Word newT) {
        for(int i=0;i< words.size();i++){
            if (words.get(i).getId()==oldT.getId()){
                words.set(i,newT);
                return  true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Word word) {
        for(int i=0;i< words.size();i++){
            if (words.get(i).getId()==word.getId()){
                words.remove(i);
                return  true;
            }
        }
        return false;
    }
    public List<Word> getWordByFavorite(){
        List<Word> favWords=new ArrayList<>();
        for (Word w : words){
            if(w.isFavorite()) favWords.add(w);
        }
        return favWords;
    }
}
