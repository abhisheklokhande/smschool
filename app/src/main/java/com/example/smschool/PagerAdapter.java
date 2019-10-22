package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                eng1 eng1 = new eng1();
                return eng1;
            case 1:
                eng2 eng2 = new eng2();
                return eng2;
            case 2:
                eng3 eng3 = new eng3();
                return eng3;
            case 3:
                eng4 eng4 = new eng4();
                return eng4;
default:
            return null;
    }

    }
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
