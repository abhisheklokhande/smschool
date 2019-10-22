package com.example.smschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class mathematics extends AppCompatActivity implements mat1.OnFragmentInteractionListener,mat2.OnFragmentInteractionListener,mat3.OnFragmentInteractionListener,mat4.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);

        TabLayout tabLayout =findViewById(R.id.tablayout2);
        tabLayout.addTab(tabLayout.newTab().setText("Course Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Resources"));
        tabLayout.addTab(tabLayout.newTab().setText("Professor details"));
        tabLayout.addTab(tabLayout.newTab().setText("Q&A"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager2);
        final pageradaptermat adapter = new pageradaptermat(getSupportFragmentManager(),tabLayout.getTabCount());
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
