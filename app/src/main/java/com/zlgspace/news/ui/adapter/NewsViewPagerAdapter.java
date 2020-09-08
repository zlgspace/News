package com.zlgspace.news.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2019/5/14.
 */
public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public NewsViewPagerAdapter(FragmentManager fm, List<Fragment> fl) {
        super(fm);
        fragmentList = fl;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Sezione " + position;
    }
}
