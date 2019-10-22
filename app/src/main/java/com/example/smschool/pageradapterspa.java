package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class pageradapterspa extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public pageradapterspa(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                spa1 spa1 = new spa1();
                return spa1;
            case 1:
                spa2 spa2 = new spa2();
                return spa2;
            case 2:
                spa3 spa3 = new spa3();
                return spa3;
            case 3:
                spa4 spa4 = new spa4();
                return spa4;
            default:
        return null;
    }
    }
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
