package com.zz.funshop.ui.fragment;

import com.zz.funshop.R;
import com.zz.funshop.base.BaseFragment;
import com.zz.funshop.widget.TitleBar;

import butterknife.BindView;

/**
 * Author:zhangmiss on 2017/10/31 0031.
 * mail:867596152@qq.com
 * Descripiton:热门的Fragment
 */

public class HotFragment extends BaseFragment
{
    @BindView(R.id.home_title_bar)
    TitleBar homeTitleBar;

    @Override
    public int getLayoutRes()
    {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView()
    {
        homeTitleBar.setTitle(R.string.hot);
        homeTitleBar.setTitleSize(15);
    }
}
