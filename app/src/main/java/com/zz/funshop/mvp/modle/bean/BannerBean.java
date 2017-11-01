package com.zz.funshop.mvp.modle.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Author:zhangmiss on 2017/11/01 0001.
 * mail:867596152@qq.com
 * Descripiton:
 */

public class BannerBean extends BmobObject
{
    private BmobFile path;
    private String url;
    private String title;
    private String jumpType;

    public BmobFile getPath()
    {
        return path;
    }

    public void setPath(BmobFile path)
    {
        this.path = path;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getJumpType()
    {
        return jumpType;
    }

    public void setJumpType(String jumpType)
    {
        this.jumpType = jumpType;
    }
}
