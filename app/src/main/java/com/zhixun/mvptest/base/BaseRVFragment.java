package com.zhixun.mvptest.base;


import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class BaseRVFragment<T1 extends BaseContract.BaesPresenter> extends BaseFragment {

    @Inject
    protected T1 mPresenter;

    /**
     * [此方法不可再重写]
     */
    @Override
    public void attachView() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
