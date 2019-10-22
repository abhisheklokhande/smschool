package com.example.smschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class spanish extends AppCompatActivity implements spa1.OnFragmentInteractionListener,spa2.OnFragmentInteractionListener,spa3.OnFragmentInteractionListener,spa4.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spanish);

        TabLayout tabLayout =findViewById(R.id.tablayout1);
        tabLayout.addTab(tabLayout.newTab().setText("Course Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Resources"));
        tabLayout.addTab(tabLayout.newTab().setText("Professor details"));
        tabLayout.addTab(tabLayout.newTab().setText("Q&A"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager1);
        final pageradapterspa adapter = new pageradapterspa(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
