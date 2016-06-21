/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.main.ui.MainActivity;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.broadcast.ui.BroadcastListFragment;
import me.jinqiang.android.material.ui.AppBarManager;
import me.jinqiang.android.material.ui.AppBarWrapperLayout;
import me.jinqiang.android.material.ui.TabFragmentPagerAdapter;
import me.jinqiang.android.material.zhihu.oneFragment;
import me.jinqiang.android.material.zhihu.zhihuNewsFragment;

/**
 * 首页的fragment
 */
public class HomeFragment extends Fragment implements AppBarManager {

    @Bind(R.id.appBarWrapper)
    AppBarWrapperLayout mAppBarWrapperLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private TabFragmentPagerAdapter mTabAdapter;

    public static HomeFragment newInstance() {
        //noinspection deprecation
        return new HomeFragment();
    }

    /**
     * @deprecated Use {@link #newInstance()} instead.
     */
    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        activity.setToolbar(mToolbar);

        mTabAdapter = new TabFragmentPagerAdapter(getChildFragmentManager());
        mTabAdapter.addTab(BroadcastListFragment.newInstance(), getString(R.string.home_broadcast));//只有这个有数据
        mTabAdapter.addTab(zhihuNewsFragment.newInstance(), getString(R.string.home_nine_and_quater));
        mTabAdapter.addTab(new oneFragment(), getString(R.string.home_discover));
        mTabAdapter.addTab(new Fragment(), getString(R.string.home_online));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void hideAppBar() {
        mAppBarWrapperLayout.hide();
    }

    @Override
    public void showAppBar() {
        mAppBarWrapperLayout.show();
    }
}
