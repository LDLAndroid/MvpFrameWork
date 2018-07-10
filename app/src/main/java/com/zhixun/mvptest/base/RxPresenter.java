package com.zhixun.mvptest.base;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/9.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 * unsubscribe() 这个方法很重要，
 * 因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，
 * 这个引用如果不能及时被释放，将有内存泄露的风险。
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BaesPresenter<T>{

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe(){
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }

    //添加到订阅的队列
    public void addSubscription(Observable observable, Subscriber subscriber){
        if (mCompositeSubscription==null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
