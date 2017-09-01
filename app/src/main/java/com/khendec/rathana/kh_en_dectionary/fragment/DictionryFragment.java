package com.khendec.rathana.kh_en_dectionary.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khendec.rathana.kh_en_dectionary.R;

/**
 * Created by ratha on 01-Sep-17.
 */

public class DictionryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_dictionry,container,false);

        return rootView;
    }
}
