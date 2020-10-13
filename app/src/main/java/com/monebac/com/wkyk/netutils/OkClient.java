package com.monebac.com.wkyk.netutils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.monebac.com.R;
import com.monebac.com.utils.LogsUtils;
import com.monebac.com.utils.ViewUtils;
import com.monebac.com.wkyk.Constant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/27
 */
public class OkClient {
    private static final String TAG = "OkClient";
    public volatile static OkClient okClient;
    //请求超时的重试次数 默认超时不重连
    private int retryCount = 0;

    private OkClient() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static OkClient getInstance() {
        if (okClient == null) {
            synchronized (OkClient.class) {
                if (okClient == null) {
                    okClient = new OkClient();
                }
            }
        }
        return okClient;
    }

    /**
     * 获取文件HttpParams对象
     *
     * @return
     */
    public static Params getParamsInstance() {
        return Params.getInstance();
    }

    /**
     * 取消所有请求
     */
    public static void CancelAll() {
        OkGo.getInstance().cancelAll();
    }

    private static void Sign(HttpParams params) {
        params.put("0", "0700");
        params.put("59", Constant.Companion.getVERSION());
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : params.urlParamsMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get(0));
        }
        Map<String, Object> resultMap = sortMapByKey(map);
        String sign = "";
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            if ("".equals(sign)) {
                sign = entry.getValue() + "";
            } else {
                sign += entry.getValue();
            }

        }
        if (!sign.equals("")) {
            sign += Constant.Companion.getMainKey();
        }
        params.put("64", Constant.Companion.Md5(sign));
        LogsUtils.e("sign=" + sign);
        LogsUtils.e("params=" + params.toString());
    }

    /***
     * 进行排序
     * @param map
     * @return
     */
    private static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                // 这里改为小写进行比较
                Integer l = Integer.parseInt(lhs);
                Integer r = Integer.parseInt(rhs);
                return l.compareTo(r);
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 设置超时重连次数
     *
     * @param retryCount
     * @return
     */
    public OkClient setRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    /**
     * get请求
     *
     * @param url            get请求地址
     * @param entityCallback 实体类回调
     */
    public void get(String url, AbsCallback entityCallback) {
        OkGo.get(url)
                .tag(this)
                .retryCount(retryCount)
                .execute(entityCallback);
    }

    /**
     * get请求
     *
     * @param url            get请求地址
     * @param params         可以使用OkMap，就不用考虑转化为String的麻烦，int boolean float都可以
     * @param entityCallback 实体类的接口回调，解析的时候要注意，这里使用的是FastJson
     */
    public void get(String url, HttpParams params, AbsCallback entityCallback) {
        LogsUtils.e(url + "\n" + params.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        OkGo.get(url)
                .tag(this)
                .retryCount(retryCount)
                .params(params)
                .execute(entityCallback);
    }

    /**
     * post请求
     *
     * @param url            post请求地址
     * @param params         可以使用OkMap，就不用考虑转化为String的麻烦，int boolean float都可以
     * @param entityCallback 实体类的接口回调，解析的时候要注意，这里使用的是FastJson
     */
    public void post(String url, HttpParams params, AbsCallback entityCallback) {
        LogsUtils.e(url + "\n" + params.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        OkGo.post(url)
                .tag(this)
                .retryCount(retryCount)
                .params(params)
                .execute(entityCallback);
    }


    /**
     * @param params
     * @param entityCallback
     */
    public void post(HttpParams params, AbsCallback entityCallback) {
        LogsUtils.e(Constant.Companion.getREQUEST_API() + "\n" + params.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        OkGo.<String>post(Constant.Companion.getREQUEST_API())
                .tag(this)
                .retryCount(retryCount)
                .params(params)
                .execute(entityCallback);

    }


    /**
     * okgo的回调，这里进行实体类的解析
     */
    public abstract static class EntityCallBack<T> extends AbsCallback<T> {

        private Context mContext;
        private Class<T> clazz;
        private BaseEntity baseEntity;

        public EntityCallBack(Context context, Class<T> clazz) {
            this.mContext = context;
            this.clazz = clazz;
        }

        @Override
        public T convertResponse(okhttp3.Response response) throws Throwable {
            synchronized (this) {
                T entity;
                String jsonStr = response.body().string();
                LogsUtils.e("json=" + jsonStr);
                try {
                    entity = null;
                    if (clazz != null) {
                        entity = JSON.parseObject(jsonStr, clazz);
                        baseEntity = (BaseEntity) entity;
                    }
                } catch (Exception e) {
                    try {
                        baseEntity = JSON.parseObject(jsonStr, BaseEntity.class);
                    } catch (Exception e1) {
                        LogsUtils.e("BaseEntity解析错误:" + e.getMessage());
                    }
                    LogsUtils.e(clazz.toString() + "解析错误:" + e.getMessage());
                    return null;
                } finally {
                    response.close();
                }

                return entity;
            }
        }

        @Override
        public void onStart(Request<T, ? extends Request> request) {
            super.onStart(request);
            Sign(request.getParams());
        }


        @Override
        public void onCacheSuccess(Response<T> response) {
            LogsUtils.e("onCacheSuccess");
        }

        @Override
        public void onError(Response<T> response) {
            if (response == null) {
                return;
            }
            if (response.getException() != null && response.getException() instanceof ConnectException) {
                ViewUtils.MakeToast(mContext, mContext.getString(R.string.nonetwork), 500);
                LogsUtils.e("response=" + response.getException());
            } else {
                LogsUtils.e("response=" + response.body());
                ViewUtils.MakeToast(mContext, mContext.getString(R.string.server_error), 500);
            }


        }

        @Override
        public void onSuccess(Response<T> response) {
            LogsUtils.e("response=" + response.body());
            if (baseEntity == null) {
                ViewUtils.MakeToast(mContext, mContext.getString(R.string.server_error), 500);
                return;
            }
            String code = baseEntity.getStr39();
            switch (code) {
                case "00":
                    break;
                default:
                    ViewUtils.MakeToast(mContext, code, 1000);
                    break;
            }

        }
    }

    /**
     * 这里设置参数 HttpParams
     */
    public static class Params {

        private static HttpParams params;

        public static Params getInstance() {
            params = new HttpParams();
            return new Params();
        }

        public HttpParams getParams() {
            return params;
        }

        public Params put(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, int value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, long value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, Float value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, double value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, char value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, File file) {
            params.put(key, file);
            return this;
        }

        public Params put(String key, List<String> filePathList) {
            if (filePathList == null || filePathList.size() == 0) {
                return this;
            }
            for (String filePath : filePathList) {
                if (!TextUtils.isEmpty(filePath)) {
                    File file = new File(filePath);
                    params.put(key, file);
                }
            }
            return this;
        }

        public Params put(String key, ArrayList<File> fileList) {
            if (fileList == null || fileList.size() == 0) {
                return this;
            }
            for (File file : fileList) {
                if (file != null) {
                    params.put(key, file);
                }
            }
            return this;
        }
    }
}
