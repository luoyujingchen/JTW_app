package com.example.hefeng.jtw.main.nav;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.hefeng.jtw.R;
import com.example.hefeng.jtw.main.base.BaseFragment;
import com.example.hefeng.jtw.main.fragment.NavHotTabFragment;
import com.example.hefeng.jtw.main.fragment.NavNewTabFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hefeng on 2017/8/28.
 */

public class NavFragment extends BaseFragment implements View.OnClickListener {
/*    @BindView(R.id.fag_nav_new)
    NavButton navNew;
    @BindView(R.id.fag_nav_hot)
    NavButton navHot;*/

    NavButton navNew, navHot;

    NavButton currentNavButton = null;
    OnNavButtonReselectedListener mOnNavButtonReselectedListener;
    Context mContext;
    FragmentManager mFragmentManager;
    int mContainerId;


    public NavFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fag_nav_layout;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initWidget(View mRoot) {
        super.initWidget(mRoot);
        navNew = (NavButton)mRoot.findViewById(R.id.fag_nav_new);
        navHot = (NavButton)mRoot.findViewById(R.id.fag_nav_hot);

        navNew.init(R.drawable.ic_action_picture, R.string.new_picture, NavNewTabFragment.class);
        navHot.init(R.drawable.ic_action_picture, R.string.hot_picture, NavHotTabFragment.class);
    }

    @OnClick({R.id.fag_nav_new, R.id.fag_nav_hot})
    @Override
    public void onClick(View v) {
        if (v instanceof NavButton) {
            NavButton nav = (NavButton) v;
            doSelect(nav);
        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavButtonReselectedListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavButtonReselectedListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(navNew);
    }

    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0){
            return;
        }
        boolean doCommit = false;
        for (Fragment fragment : fragments){
            if (fragment != this && fragment != null){
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavButton newNavButton) {
        NavButton oldNavButton = null;
        if (currentNavButton != null) {
            oldNavButton = currentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        currentNavButton = newNavButton;
    }

    private void doTabChanged(NavButton oldNavButton, NavButton newNavButton) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (oldNavButton != null){
            if (oldNavButton.getmFragment() != null){
                transaction.detach(oldNavButton.getmFragment());
            }
        }
        if (newNavButton != null){
            if (newNavButton.getmFragment() == null){
                Fragment fragment = Fragment.instantiate(mContext,newNavButton.getmClx().getName(),null);
                transaction.add(mContainerId,fragment,newNavButton.getmTag());
                newNavButton.setmFragment(fragment);
            }else {
                transaction.attach(newNavButton.getmFragment());
            }
            transaction.commit();
        }

    }

    private void onReselect(NavButton navButton) {
        OnNavButtonReselectedListener lisenter = mOnNavButtonReselectedListener;
        if (lisenter != null) {
            lisenter.OnReselect(navButton);
        }
    }

    public interface OnNavButtonReselectedListener {
        void OnReselect(NavButton navButton);
    }

}
