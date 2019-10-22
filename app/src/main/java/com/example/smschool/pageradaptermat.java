package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class pageradaptermat extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public pageradaptermat(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                mat1 mat1 = new mat1();
                return mat1;
            case 1:
                mat2 mat2 = new mat2();
                return mat2;
            case 2:
                mat3 mat3 = new mat3();
                return mat3;
            case 3:
                mat4 mat4 = new mat4();
                return mat4;
            default:
        return null;
    }
    }
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
