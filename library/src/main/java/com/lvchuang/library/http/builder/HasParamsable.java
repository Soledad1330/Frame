package com.lvchuang.library.http.builder;

import java.util.Map;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public interface HasParamsable
{
    public abstract OkHttpRequestBuilder params(Map<String, String> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

}

