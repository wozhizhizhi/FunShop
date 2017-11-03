package com.zz.funshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.funshop.R;
import com.zz.funshop.mvp.modle.bean.HomeBean;
import com.zz.funshop.utils.ImageLoadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:zhangmiss on 2017/11/02 0002.
 * mail:867596152@qq.com
 * Descripiton:
 */

public class HomeAdapter extends BaseAdapter
{
    private Context mContext;
    private List<HomeBean> homeBeanList;
    private LayoutInflater layoutInflater;

    public HomeAdapter(Context mContext, List<HomeBean> homeBeanList)
    {
        this.layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.homeBeanList = homeBeanList;
    }

    @Override
    public int getCount()
    {
        if(homeBeanList == null || homeBeanList.size() <= 0)
        {
            return 0;
        }
        return homeBeanList.size();
    }

    @Override
    public Object getItem(int position)
    {

        return homeBeanList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public void refresh(List<HomeBean> datas)
    {
        if (datas == null)
        {
            datas = new ArrayList<>(0);
        }
        this.homeBeanList = datas;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHodler viewHodler = null;
        HomeBean homeBean = homeBeanList.get(position);
        if (convertView == null)
        {
            viewHodler = new ViewHodler();
            convertView = layoutInflater.inflate(R.layout.home_item , parent , false);
            viewHodler.home_item_icon = convertView.findViewById(R.id.home_item_icon);
            viewHodler.home_item_text = convertView.findViewById(R.id.home_item_text);
            viewHodler.home_item_price = convertView.findViewById(R.id.home_item_price);
            convertView.setTag(viewHodler);
        }
        else
        {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        ImageLoadUtils.loadImage(mContext , homeBean.getImageUrl().getUrl() , viewHodler.home_item_icon);
        viewHodler.home_item_text.setText(homeBean.getTitle());
        viewHodler.home_item_price.setText("价格: " + homeBean.getPrice());
        return convertView;
    }

    public static class ViewHodler
    {
        private TextView home_item_text;
        private TextView home_item_price;
        private ImageView home_item_icon;

    }

}
