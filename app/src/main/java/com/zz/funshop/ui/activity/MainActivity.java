package com.zz.funshop.ui.activity;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.zz.funshop.R;
import com.zz.funshop.base.BaseActivity;
import com.zz.funshop.bean.Tab;
import com.zz.funshop.ui.fragment.CartFragment;
import com.zz.funshop.ui.fragment.HomeFragment;
import com.zz.funshop.ui.fragment.HotFragment;
import com.zz.funshop.ui.fragment.UserFragment;
import com.zz.funshop.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * app主窗口类
 */
public class MainActivity extends BaseActivity
{
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private LayoutInflater mInflater;

    private HomeFragment homeFragment;

    private List<Tab> mTabs = new ArrayList<>(4);

    @Override
    protected int getContentResId()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return R.layout.activity_main;
    }

    @Override
    public void initView()
    {
        initTab();
    }

    @Override
    public void initPresenter()
    {

    }

    private void initTab()
    {
        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class, R.string.hot, R.drawable.selector_icon_hot);
        Tab tab_cart = new Tab(CartFragment.class, R.string.cart, R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(UserFragment.class, R.string.mine, R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);


        mInflater = LayoutInflater.from(this);
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs)
        {

            TabHost.TabSpec tabSpec = tabhost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            tabhost.addTab(tabSpec, tab.getFragment(), null);

        }

        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener()
        {
            @Override
            public void onTabChanged(String tabId)
            {

                if (tabId == getString(R.string.cart))
                {

//                    refData();
                }

            }
        });

        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        tabhost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab)
    {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());

        return view;
    }

}
