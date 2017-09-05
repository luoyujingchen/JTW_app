package com.example.hefeng.jtw.main.nav;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hefeng.jtw.R;

import butterknife.BindView;

/**
 * Created by hefeng on 2017/8/27.
 */

public class NavButton extends FrameLayout {
    private Fragment mFragment = null;
/*    @BindView(R.id.nav_button_text)
    TextView navText;
    @BindView(R.id.notice_dot)
    TextView dotText;
    @BindView(R.id.nav_button_img)
    ImageView navImage;*/

    TextView navText, dotText;
    ImageView navImage;
    String mTag;
    Class<?> mClx;

    public NavButton(@NonNull Context context) {
        super(context);
        init();
    }

    public NavButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NavButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.nav_button, this, true);

        dotText = (TextView) findViewById(R.id.notice_dot);
        navText = (TextView) findViewById(R.id.nav_button_text);
        navImage = (ImageView) findViewById(R.id.nav_button_img);
    }

    public void init(@DrawableRes int resId, @StringRes int strId, Class<?> clx) {
        navImage.setImageResource(resId);
        navText.setText(strId);
        mClx = clx;
        mTag = mClx.getName();

    }

    public void showDot(int count) {
        dotText.setVisibility(count > 0 ? VISIBLE : GONE);
        dotText.setText(count);
    }

    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getmTag() {
        return mTag;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
    }

    public Class<?> getmClx() {
        return mClx;
    }

    public void setmClx(Class<?> mClx) {
        this.mClx = mClx;
    }
}
