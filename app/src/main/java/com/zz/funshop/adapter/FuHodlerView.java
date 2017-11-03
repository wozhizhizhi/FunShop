package com.zz.funshop.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.funshop.R;
import com.zz.funshop.utils.ImageLoadUtils;

/**
 * Author:zhangmiss on 2017/11/03 0003.
 * mail:867596152@qq.com
 * Descripiton: 通用的adapter
 */

public class FuHodlerView
{
    private final SparseArray<View> mViews;
    private final int mPositiom;
    private final View mConvertView;

    public FuHodlerView(ViewGroup parent , int layoutId , int position)
    {
        this.mPositiom = position;
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId , parent , false);
        // setTag()
        mConvertView.setTag(this);
    }

    /**
     * 拿到全部的views
     * @return
     */
    public SparseArray<View> getAllViews()
    {
        return mViews;
    }

    /**
     *  拿到一个viewHodler对象
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static FuHodlerView get(View convertView , ViewGroup parent , int layoutId , int position)
    {
        if(convertView == null)
        {
            return new FuHodlerView(parent , layoutId ,position);
        }
        else
        {
            return (FuHodlerView) convertView.getTag();
        }
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
       View view = mViews.get(viewId);
       if(view == null)
       {
           view = mConvertView.findViewById(viewId);
       }
       mViews.put(viewId , view);
       return (T) view;
    }

    /**
     * 为TextView设置字符串
     * @param viewId
     * @param text
     * @return
     */
    public FuHodlerView setText(int viewId , CharSequence text)
    {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 为Imageview设置本地图片
     * @param viewId
     * @param drawableId
     * @return
     */
    public FuHodlerView setImageResource(int viewId , int drawableId)
    {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(drawableId);
        return this;
    }


    /**
     * 为Imageview设置网络图片
     * @param viewId
     * @param url
     * @return
     */
    public FuHodlerView setImageByUrl(Context context ,int viewId , String url)
    {
        ImageView imageView = getView(viewId);
        ImageLoadUtils.loadImage(context , url , imageView);
        return this;
    }

    public int getPositiom()
    {
        return mPositiom;
    }

}
