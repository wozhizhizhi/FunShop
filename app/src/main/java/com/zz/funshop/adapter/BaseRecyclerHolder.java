package com.zz.funshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.funshop.utils.ImageLoadUtils;

/**
 * Author:zhangmiss on 2017/11/02 0002.
 * mail:867596152@qq.com
 * Descripiton:适配一切RecyclerView.ViewHolder
 */

public class BaseRecyclerHolder extends RecyclerView.ViewHolder
{
    private final SparseArray<View> mViews;
    public BaseRecyclerHolder(View itemView)
    {
        super(itemView);
        // 不填数字时是10,一般8个就已经很够用了
        this.mViews = new SparseArray<>();
    }

    public SparseArray<View> getAllViews()
    {
        return mViews;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if(view == null)
        {
            view = itemView.findViewById(viewId);
            mViews.put(viewId , view);
        }
        return (T)view;
    }

    /**
     * 为ImageView设置本地图片
     * @param viewId
     * @param drawableId
     * @return
     */
    public BaseRecyclerHolder setImageResource(int viewId , int drawableId)
    {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    /**
     * 为TextView设置字符串
     * @param viewId
     * @param text
     * @return
     */
    public BaseRecyclerHolder setText(int viewId , String text)
    {

        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 为ImageView设置网络的图片
     * @param context
     * @param viewId
     * @param url
     * @return
     */
    public BaseRecyclerHolder setImageByUrl(Context context , int viewId , String url)
    {
        ImageView imageView = getView(viewId);
        ImageLoadUtils.loadImage(context , url , imageView);
        return this;
    }

}
