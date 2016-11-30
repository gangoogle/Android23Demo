package com.google.android23demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment>mFragments;
    private String[]s={"音乐","电影"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewpager=(ViewPager)findViewById(R.id.view_pager);
        mTabLayout=(TabLayout)findViewById(R.id.tl_layout);
        mFragments=new ArrayList<>();
        Fragment blankFragment=new BlankFragment();
        Fragment blankFragment2=new BlankFragment2();
        mFragments.add(blankFragment);
        mFragments.add(blankFragment2);
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(fragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);


    }

    private class FragmentAdapter extends FragmentPagerAdapter{
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return s[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
