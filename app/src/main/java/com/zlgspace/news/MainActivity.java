package com.zlgspace.news;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.zlgspace.news.msgparser.MsgDescription;
import com.zlgspace.news.ui.SimpleNewsFragment;
import com.zlgspace.news.ui.WeiBoTopSearchFragment;
import com.zlgspace.news.ui.adapter.NewsViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends FragmentActivity implements MaterialTabListener {

    @BindView(R.id.tabHost)
    protected MaterialTabHost mMaterialTabHost;

    @BindView(R.id.viewPager)
    protected ViewPager mViewPager;

    @BindArray(R.array.newsTitles)
    protected String tabTitles[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        NewsViewPagerAdapter viewPagerAdapter = new NewsViewPagerAdapter(this.getSupportFragmentManager(),initFragment());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mMaterialTabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < tabTitles.length; i++) {
            mMaterialTabHost.addTab(
                    mMaterialTabHost.newTab()
                            .setText(tabTitles[i])
                            .setTabListener(this)
            );
        }
    }

    private List<Fragment> initFragment(){
        List<Fragment> fragmentList = new ArrayList<>();
        MsgDescription newsTypes[] = new MsgDescription[]{
                MsgDescription.onEventImportantNews,MsgDescription.onEventScientificNews,MsgDescription.onEventPhoneNews,
                MsgDescription.onEventNumericalCodeNews,MsgDescription.onEventGameNews,MsgDescription.onEventEntertainmentNews,
                MsgDescription.onEventSportsNews,MsgDescription.onEventBusinessNews,MsgDescription.onEventMilitaryNews
        };

        fragmentList.add(new WeiBoTopSearchFragment());
        for(MsgDescription type:newsTypes){
            fragmentList.add(new SimpleNewsFragment(type));
        }
        return fragmentList;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}

