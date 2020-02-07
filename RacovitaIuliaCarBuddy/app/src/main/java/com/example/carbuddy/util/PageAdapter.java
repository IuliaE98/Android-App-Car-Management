package com.example.carbuddy.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.carbuddy.tabAbout;
import com.example.carbuddy.tabFeedback;

public class PageAdapter extends FragmentPagerAdapter {
    private int numTabs;

    public PageAdapter(FragmentManager fm,int numTabs) {
        super(fm);
        this.numTabs=numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new tabAbout();
            case 1 : return new tabFeedback();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
