package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class pageradaptersoc extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public pageradaptersoc(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                soc1 soc1 = new soc1();
                return soc1;
            case 1:
                soc2 soc2 = new soc2();
                return soc2;
            case 2:
                soc3 soc3 = new soc3();
                return soc3;
            case 3:
                soc4 soc4 = new soc4();
                return soc4;
            default:
        return null;
    }
    }
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
