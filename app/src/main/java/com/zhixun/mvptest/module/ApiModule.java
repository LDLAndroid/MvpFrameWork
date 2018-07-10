package com.zhixun.mvptest.module;


import com.zhixun.mvptest.App;
import com.zhixun.mvptest.api.Api;
import com.zhixun.mvptest.api.interceptor.LoggingInterceptor;
import com.zhixun.mvptest.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/16.
 */
@Module
public class ApiModule {
    @Provides
    public OkHttpClient provideOKHttpClient() {
        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
        logging.setLevel(LoggingInterceptor.Level.BODY);
        File cacheFile = new File(App.getInstance().getCacheDir(), "ShopHttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("accessKey", "accessKey");
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
        //.cache(cache);

        return builder.build();
    }


    @Provides
    protected Api provideService(OkHttpClient okHttpClient) {
        return Api.getInstance(okHttpClient);
    }


    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {

            LogUtils.i("oklog: " + message);
        }
    }


}
