package com.khendec.rathana.kh_en_dectionary.repository;

import com.khendec.rathana.kh_en_dectionary.repository.memory.WordRepository;

/**
 * Created by ratha on 06-Sep-17.
 */

public enum MemoryRepositoy {
    INSTANCE;
    private  WordRepository wordRepository=new WordRepository();
    public  WordRepository getInstance(){
        return wordRepository;
    }

}
