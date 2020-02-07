package com.example.carbuddy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Display;

public class SharedPreferencess {

    private static final String PREF_NAME = "PrefSharedName";
    private static final String SET_USER = "setUserPref";
    private static final String RATING_BAR_SHARED_PREF = "ratingBarPref";
    private SharedPreferencess sharedPR;
    private long userId;
    public Context context;
    private SharedPreferences sharedPreferences;


    public SharedPreferencess(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SET_USER,context.MODE_PRIVATE);

    }

    public void setUser(long id){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(SET_USER,id);
        edit.apply();
    }

    public long getUser(){
        long id = sharedPreferences.getLong(SET_USER,0);
        return id;
    }

}
