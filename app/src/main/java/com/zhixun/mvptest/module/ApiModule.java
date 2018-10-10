package com.zhixun.mvptest.module;


import com.zhixun.mvptest.App;
import com.zhixun.mvptest.api.Api;
import com.zhixun.mvptest.api.intecepter.JsonUtil;
import com.zhixun.mvptest.api.intecepter.LogUtil;

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
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/1/16.
 */
@Module
public class ApiModule {
    @Provides
    public OkHttpClient provideOKHttpClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new MyLog());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File cacheFile = new File(App.getInstance().getCacheDir(), "ShopHttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(logInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("accessKey", "accessKey");
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .cache(cache)
                .build();


        return build;
    }


    @Provides
    protected Api provideService(OkHttpClient okHttpClient) {
        return Api.getInstance(okHttpClient);
    }


    public static class MyLog implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                LogUtil.d(mMessage.toString());
            }
        }
    }


}
