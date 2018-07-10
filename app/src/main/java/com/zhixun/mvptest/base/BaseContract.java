package com.zhixun.mvptest.base;

/**
 * Created by Administrator on 2017/5/3.
 */

public interface BaseContract {
  interface  BaesPresenter<T>{
      void attachView(T view);

      void detachView();
  }
    interface BaseView{
        void showError(String err);

        void complete();
        void twoUser();
    }
}
