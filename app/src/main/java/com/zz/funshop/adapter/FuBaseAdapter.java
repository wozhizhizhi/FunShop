package com.zz.funshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Author:zhangmiss on 2017/11/03 0003.
 * mail:867596152@qq.com
 * Descripiton:万能的Adapter
 */

public abstract class FuBaseAdapter<T> extends BaseAdapter implements AbsListView.OnScrollListener
{
    protected Collection<T> mDatas;
    private final int mItemLayoutId;
    private AbsListView mListView;
    // 是否在滑动
    private boolean isScrolling;
    private Context mContext;
    private LayoutInflater inflater;
    private AbsListView.OnScrollListener listener;

    public FuBaseAdapter(AbsListView listView , Collection<T> mDatas , int mItemLayoutId)
    {
        if(mDatas == null)
        {
            mDatas = new ArrayList<>(0);
        }
        this.mDatas = mDatas;
        this.mListView = listView;
        this.mItemLayoutId = mItemLayoutId;
        mContext = listView.getContext();
        inflater = LayoutInflater.from(mContext);
        mListView.setOnScrollListener(this);
    }

    // 刷新数据
    public void refresh(Collection<T> mDatas)
    {
        if(mDatas == null)
        {
            mDatas = new ArrayList<>(0);
        }
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void addOnScrollListener(AbsListView.OnScrollListener l)
    {
        this.listener = l;
    }

    @Override
    public int getCount()
    {
        if (mDatas == null || mDatas.size() <= 0)
        {
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public T getItem(int position)
    {
        if (mDatas instanceof List)
        {
            return ((List<T>) mDatas).get(position);
        }
        else if (mDatas instanceof Set)
        {
            return new ArrayList<T>(mDatas).get(position);
        }
        else
        {
            return null;
        }
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final FuHodlerView fuHodlerView = getViewHodler(position , convertView ,parent);
        convert(fuHodlerView , getItem(position) , isScrolling ,position);
        return fuHodlerView.getConvertView();
    }

    public FuHodlerView getViewHodler(int position , View convertView , ViewGroup parent)
    {
        return FuHodlerView.get(convertView , parent , mItemLayoutId , position);
    }

    public void convert(FuHodlerView helper , T item , boolean isScrolling)
    {
    }

    public void convert(FuHodlerView helper , T item , boolean isScrolling , int position)
    {
        convert(helper, getItem(position), isScrolling);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        // 当前没有滚动的时候
        if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
        {
            isScrolling = false;
            this.notifyDataSetChanged();
        }
        else
        {
            isScrolling = true;
        }

        if(listener != null)
        {
            listener.onScrollStateChanged(view , scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if(listener != null)
        {
            listener.onScroll(view , firstVisibleItem , visibleItemCount , totalItemCount);
        }
    }
}
