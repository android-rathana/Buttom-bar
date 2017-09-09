package com.khendec.rathana.kh_en_dectionary.event.message;

import android.support.design.widget.Snackbar;
import android.view.ViewGroup;

/**
 * Created by ratha on 07-Sep-17.
 */

public class SnackbarMessage {

    public static void getMessage(ViewGroup viewGroup,String message,boolean isShort){
        if(isShort){
            Snackbar snackbar=Snackbar.make(viewGroup,message,Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else{
            Snackbar snackbar=Snackbar.make(viewGroup,message,Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public static void getMessageShowInfinite(ViewGroup viewGroup,String message){
            Snackbar snackbar=Snackbar.make(viewGroup,message,Snackbar.LENGTH_INDEFINITE);
            snackbar.show();

    }

}
