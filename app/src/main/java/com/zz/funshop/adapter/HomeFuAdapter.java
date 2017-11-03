package com.zz.funshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.funshop.R;
import com.zz.funshop.mvp.modle.bean.HomeBean;
import com.zz.funshop.utils.ImageLoadUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author:zhangmiss on 2017/11/02 0002.
 * mail:867596152@qq.com
 * Descripiton:
 */

public class HomeFuAdapter extends FuBaseAdapter<HomeBean>
{

    public HomeFuAdapter(AbsListView listView, List<HomeBean> mDatas)
    {
        super(listView, mDatas, R.layout.home_item);
    }

    @Override
    public void convert(FuHodlerView helper, HomeBean item, boolean isScrolling)
    {
        Context context = helper.getConvertView().getContext();
        if(isScrolling)
        {
            ImageView imageView = helper.getView(R.id.home_item_icon);
            ImageLoadUtils.loadImage(context , item.getImageUrl().getUrl() ,imageView);
        }
        else
        {
            helper.setImageByUrl(context , R.id.home_item_icon , item.getImageUrl().getUrl());
        }

        helper.setText(R.id.home_item_text , item.getTitle());
        helper.setText(R.id.home_item_price , item.getPrice());
    }
}
