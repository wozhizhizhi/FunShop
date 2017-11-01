package com.zz.funshop.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 首页顶部轮播的viewpager.
 */
public class CustomViewpager extends ViewPager
{
    public CustomViewpager(Context context)
    {
        super(context);
    }

    public CustomViewpager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    private int startX;
    private int startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);

                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int dx = endX - startX;
                int dy = endY - startY;

                if (Math.abs(dx) > Math.abs(dy))
                {
                    //左右滑动 不拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                else
                {
                    //上下滑动 拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;

        }
        return super.dispatchTouchEvent(ev);
    }
}