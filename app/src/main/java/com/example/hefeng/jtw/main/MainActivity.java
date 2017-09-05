package com.example.hefeng.jtw.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.example.hefeng.jtw.R;
import com.example.hefeng.jtw.main.base.BaseActivity;
import com.example.hefeng.jtw.main.nav.NavButton;
import com.example.hefeng.jtw.main.nav.NavFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavFragment.OnNavButtonReselectedListener {
    @BindView(R.id.main_container)
    ViewPager mainContainer;
    @BindView(R.id.fag_nav)
    NavFragment navBar;
//    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerlayout;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();

        FragmentManager manager = getSupportFragmentManager();
        navBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        navBar.setup(this, manager, R.id.main_container, this);

        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerlayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }


    @Override
    public void OnReselect(NavButton navButton) {

    }
}
