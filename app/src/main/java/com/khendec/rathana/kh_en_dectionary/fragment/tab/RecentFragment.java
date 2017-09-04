package com.khendec.rathana.kh_en_dectionary.fragment.tab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khendec.rathana.kh_en_dectionary.R;

/**
 * Created by ratha on 01-Sep-17.
 */

public class RecentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_recent,container,false);

        return rootView;
    }
}
