package com.example.sharpcj.lvjitourtest;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SharpCJ on 2017/7/14.
 */

public class MyScrollingViewBehavior extends android.support.design.widget.AppBarLayout.ScrollingViewBehavior {
    private String TAG = "sharpcj_MyBehavior";
    private int mTargetTop;
    private int mScrolled;
    private Toolbar mToolBar;
    private ImageView mIvSearch;
    private TextView mTvTitle;

    public MyScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        mTargetTop = child.getTop();

        Log.i(TAG, "----layoutDependsOn---mTargetTop--" + mTargetTop);
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        mToolBar = (Toolbar) dependency.findViewById(R.id.toolBar);
        mIvSearch = (ImageView) dependency.findViewById(R.id.iv_search);
        mTvTitle = (TextView) dependency.findViewById(R.id.tv_title);

        int offset = child.getTop();
        float temp = (1 - 1.0f * (offset - 147) / (525 - 147));
        Log.i(TAG, "----onDependentViewChanged----" + offset);

//        ViewCompat.setAlpha(mToolBar, temp);

        if (temp <= 0) {
            mIvSearch.setVisibility(View.INVISIBLE);
            mTvTitle.setVisibility(View.INVISIBLE);
        } else if (temp <= 1) {
            mIvSearch.setVisibility(View.VISIBLE);
            mTvTitle.setVisibility(View.VISIBLE);
            ViewCompat.setAlpha(mToolBar, temp);
        } else {
            mIvSearch.setVisibility(View.VISIBLE);
            mTvTitle.setVisibility(View.VISIBLE);
            ViewCompat.setAlpha(mToolBar, 1);
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i(TAG, "----onStartNestedScroll--child--" + child.getTop());
        Log.i(TAG, "----onStartNestedScroll--view--" + target.getTop());
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        Log.i(TAG, "----onNestedPreScroll--child--" + child.getTop());
        Log.i(TAG, "----onNestedPreScroll--view--" + target.getTop());
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.i(TAG, "----onNestedPreFling--child--" + child.getTop());
        Log.i(TAG, "----onNestedPreFling--view--" + target.getTop());
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
}
