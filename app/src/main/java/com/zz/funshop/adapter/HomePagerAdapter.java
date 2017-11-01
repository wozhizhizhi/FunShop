package com.zz.funshop.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zz.funshop.R;
import com.zz.funshop.mvp.modle.bean.BannerBean;
import com.zz.funshop.utils.ImageLoadUtils;

import java.util.List;

/**
 * 首页轮播图的adapter
 */
public class HomePagerAdapter extends PagerAdapter
{
    private Context context;
    private List<BannerBean> data;

    public HomePagerAdapter(Context context, List<BannerBean> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        final BannerBean bannerBean = data.get(position % data.size());
        ImageView img = (ImageView) View.inflate(context, R.layout.home_page_binner, null);
        ImageLoadUtils.loadImage(context, bannerBean.getPath().getUrl(), img);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }
}