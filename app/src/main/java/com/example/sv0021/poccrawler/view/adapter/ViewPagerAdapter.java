package com.example.sv0021.poccrawler.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<CharSequence> titulosFragments;
    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<CharSequence> titulosFragments, List<Fragment> fragments) {
        super(fm);
        this.titulosFragments = titulosFragments;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulosFragments.get(position);
    }
}
