package com.khendec.rathana.kh_en_dectionary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.khendec.rathana.kh_en_dectionary.entity.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 06-Sep-17.
 */

public abstract class BaseFragment  extends Fragment
{

    private BaseActivity mAvtivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof BaseActivity){
            this.mAvtivity= (BaseActivity) activity;
            onInject(mAvtivity);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view,savedInstanceState);
    }

    public BaseActivity getBaseAvtivity(){
        return mAvtivity;
    }

    protected abstract void setUp(View view, Bundle savedInstanceState);

    protected abstract void onInject(BaseActivity baseActivity);

    protected List<Word> wordSearch(List<Word> list,String query){
        List<Word> wordsClone=new ArrayList<>();
        for(Word w : list){
            if(w.getWord().matches("(?i)("+query+ ").*" )){
                wordsClone.add(w);
            }
        }
        return wordsClone;
    }


}
