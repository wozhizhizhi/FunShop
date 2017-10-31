package com.zz.funshop.base;

import android.content.DialogInterface;

/**
 * View层接口基类
 *
 * @author Hunter
 */
public interface IBaseView {

    /**
     * 显示进度条
     *
     * @param message 要显示的信息
     */
    void showProgress(String message);


    /**
     * 隐藏进度条
     */
    void hideProgress();


    /**
     * 根据字符串弹出toast
     *
     * @param msg 提示内容
     */
    void showToast(String msg);
}
