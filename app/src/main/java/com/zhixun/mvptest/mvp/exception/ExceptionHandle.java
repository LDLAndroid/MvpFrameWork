package com.zhixun.mvptest.mvp.exception;

import android.nfc.FormatException;
import android.util.Log;
import android.util.MalformedJsonException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int UNKNOWN = 400;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int HTTP_405 = 405;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        Log.i("tag", "e.toString = " + e.toString());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message="登录已失效请重新登录";
                   break;
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case UNKNOWN:
                case HTTP_405:
                case SERVICE_UNAVAILABLE:
                    ex.message="服务器异常"+httpException.code();
                    break;
//                default:
//                    //ex.code = httpException.code();
//                    ex.message = "网络错误";
//                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException||e instanceof MalformedJsonException||e instanceof FormatException
                /*|| e instanceof ParseException*/) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException|| e instanceof UnknownHostException||e instanceof SocketException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "网络异常,请检查网络连接";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        }else if(e instanceof EOFException) {
            ex=new ResponeThrowable(e, ERROR.SERVER_ERROR);
            ex.message="服务器异常";
            return ex;
        }else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "网络错误";
            return ex;
        }
    }


    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
        public static final int SERVER_ERROR = 1006;
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    class ServerException extends RuntimeException {
        int code;
        String message;
    }


}
