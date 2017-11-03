package com.zz.funshop.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author:zhangmiss on 2017/11/02 0002.
 * mail:867596152@qq.com
 * Descripiton:
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration
{
    private int spanCount;

    public HomeItemDecoration(int spanCount)
    {
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = spanCount;
        outRect.bottom = spanCount;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) %3==0) {
            outRect.left = 0;
        }
    }
}
