package com.example.smschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class science extends AppCompatActivity implements sci1.OnFragmentInteractionListener,sci2.OnFragmentInteractionListener,sci3.OnFragmentInteractionListener,sci4.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);

        TabLayout tabLayout =findViewById(R.id.tablayout3);
        tabLayout.addTab(tabLayout.newTab().setText("Course Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Resources"));
        tabLayout.addTab(tabLayout.newTab().setText("Professor details"));
        tabLayout.addTab(tabLayout.newTab().setText("Q&A"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager3);
        final pageradaptersci adapter = new pageradaptersci(getSupportFragmentManager(),tabLayout.getTabCount());
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

