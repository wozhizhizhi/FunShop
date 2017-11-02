package com.zz.funshop.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.zz.funshop.R;
import com.zz.funshop.adapter.HomePagerAdapter;
import com.zz.funshop.base.BaseFragment;
import com.zz.funshop.mvp.modle.bean.BannerBean;
import com.zz.funshop.utils.PixelUtils;
import com.zz.funshop.widget.CustomViewpager;
import com.zz.funshop.widget.TitleBar;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:zhangmiss on 2017/10/31 0031.
 * mail:867596152@qq.com
 * Descripiton:主页的Fragment
 */

public class HomeFragment extends BaseFragment
{
    @BindView(R.id.home_title_bar)
    TitleBar homeTitleBar;

    @BindView(R.id.home_head_vpager)
    CustomViewpager homeHeadVpager;

    @BindView(R.id.home_ll_porint)
    LinearLayout homeLlPorint;

    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;

    private List<BannerBean> bannerBeanList;
    private HomePagerAdapter homePagerAdapter;
    private Subscription subscription;
    private Subscriber<Long> subscriber;

    // 处理 VIEWPAGER 的点击，触摸，轮播效果
    private int downX = 0;
    private long downTime = 0;

    @Override
    public int getLayoutRes()
    {
        return R.layout.fragment_home;
    }

    @Override
    public void initView()
    {
        homeTitleBar.setTitle(R.string.home);
        homeTitleBar.setTitleSize(15);
        getBannerData();
    }

    private void initViewpager()
    {
        homePagerAdapter = new HomePagerAdapter(getActivity(), bannerBeanList);
        homeHeadVpager.setAdapter(homePagerAdapter);
        initPagerPoint(bannerBeanList);
        initViewPagerListener();
        startBinnerCircel();
    }

    // 初始化指示点
    private void initPagerPoint(List list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            View view = new View(getContext());
            int width = PixelUtils.dp2px(6, getContext());
            int height = PixelUtils.dp2px(6, getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.setMargins(width, 0, 0, 0);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.shape_point_normal);
            homeLlPorint.addView(view);
        }
    }

    // 指示点选中与否
    private void setCurrentPoint(int position)
    {
        for (int i = 0; i < homeLlPorint.getChildCount(); i++)
        {
            if (i == position % homeLlPorint.getChildCount())
            {
                homeLlPorint.getChildAt(i).setBackgroundResource(R.drawable.shape_point_selected);
            } else
            {
                homeLlPorint.getChildAt(i).setBackgroundResource(R.drawable.shape_point_normal);
            }
        }
    }

    private void initViewPagerListener()
    {
        homeHeadVpager.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:

                        downX = (int) event.getX();
                        downTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:

                        long intervalTime = System.currentTimeMillis() - downTime;
                        int intervalX = Math.abs((int) (event.getX() - downX));

                        //如果点击时间小于1秒且移动距离小于10个像素就认为是点击事件
                        if (intervalTime < 1000 && intervalX < 10)
                        {
                            //点击事件
                        }
                        break;
                }
                return false;
            }
        });
        homeHeadVpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                setCurrentPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private void startBinnerCircel()
    {
        if (subscriber == null)
        {
            subscriber = new Subscriber<Long>()
            {

                @Override
                public void onSubscribe(Subscription s)
                {
                    subscription = s;
                }

                @Override
                public void onNext(Long o)
                {
                    homeHeadVpager.setCurrentItem(homeHeadVpager.getCurrentItem() + 1);
                }

                @Override
                public void onError(Throwable t)
                {

                }

                @Override
                public void onComplete()
                {
                }
            };
        }

        Flowable.interval(3, TimeUnit.SECONDS)
                .onBackpressureLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        subscription.request(10000);
    }

    private void getBannerData()
    {
        showProgress("Q仔，正在努力拉货,请稍后!");
        BmobQuery<BannerBean> query = new BmobQuery<BannerBean>();
        // 执行查询方法
        query.findObjects(new FindListener<BannerBean>()
        {
            @Override
            public void done(List<BannerBean> object, BmobException e)
            {
                if (e == null)
                {
                    hideProgress();
                    bannerBeanList = object;
                    initViewpager();
                }
                else
                {
                    hideProgress();
                    Log.d("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


}
