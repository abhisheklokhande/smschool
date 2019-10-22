package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class pageradaptersci extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    public pageradaptersci(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                sci1 sci1 = new sci1();
                return sci1;
            case 1:
                sci2 sci2 = new sci2();
                return sci2;
            case 2:
                sci3 sci3 = new sci3();
                return sci3;
            case 3:
                sci4 sci4 = new sci4();
                return sci4;
            default:
        return null;
    }
    }
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
