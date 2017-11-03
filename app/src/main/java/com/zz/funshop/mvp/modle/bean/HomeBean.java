package com.zz.funshop.mvp.modle.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Author:zhangmiss on 2017/11/02 0002.
 * mail:867596152@qq.com
 * Descripiton:
 */

public class HomeBean extends BmobObject
{
    private String title;
    private BmobFile imageUrl;
    private String price;
    private String url;
    private String descripiton;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public BmobFile getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(BmobFile imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDescripiton()
    {
        return descripiton;
    }

    public void setDescripiton(String descripiton)
    {
        this.descripiton = descripiton;
    }
}
