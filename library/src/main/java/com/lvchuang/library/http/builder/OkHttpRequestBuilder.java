package com.lvchuang.library.http.builder;

import com.lvchuang.library.http.request.RequestCall;

import java.util.Map;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public abstract class OkHttpRequestBuilder
{
    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;

    public abstract OkHttpRequestBuilder url(String url);

    public abstract OkHttpRequestBuilder tag(Object tag);

    public abstract OkHttpRequestBuilder headers(Map<String, String> headers);

    public abstract OkHttpRequestBuilder addHeader(String key, String val);

    public abstract RequestCall build();


}

