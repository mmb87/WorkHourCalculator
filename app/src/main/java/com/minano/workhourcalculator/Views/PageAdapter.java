package com.minano.workhourcalculator.Views;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.minano.workhourcalculator.Views.Fragments.ListTabFragment;
import com.minano.workhourcalculator.Views.Fragments.StartTabFragment;

/**
 * Created by Admin on 10/02/2017.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StartTabFragment();
            case 1:
                return new ListTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
