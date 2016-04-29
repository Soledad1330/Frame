package com.lvchuang.library.http.request;

import android.text.TextUtils;

import com.lvchuang.library.HttpUtil;
import com.lvchuang.library.http.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public class OtherRequest extends OkHttpRequest {
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private RequestBody requestBody;
    private String method;
    private String content;

    public OtherRequest(RequestBody requestBody, String content, String method, String url, Object tag, Map<String, String> params, Map<String, String> headers) {
        super(url, tag, params, headers);
        this.requestBody = requestBody;
        this.method = method;
        this.content = content;

    }

    @Override
    protected RequestBody buildRequestBody() {
        if (requestBody == null && TextUtils.isEmpty(content) && HttpMethod.requiresRequestBody(method)) {
            Exceptions.illegalArgument("requestBody and content can not be null in method:" + method);
        }

        if (requestBody == null && !TextUtils.isEmpty(content)) {
            requestBody = RequestBody.create(MEDIA_TYPE_PLAIN, content);
        }

        return requestBody;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        if (method.equals(HttpUtil.METHOD.PUT)) {
            builder.put(requestBody);
        } else if (method.equals(HttpUtil.METHOD.DELETE)) {
            if (requestBody == null)
                builder.delete();
            else
                builder.delete(requestBody);
        } else if (method.equals(HttpUtil.METHOD.HEAD)) {
            builder.head();
        } else if (method.equals(HttpUtil.METHOD.PATCH)) {
            builder.patch(requestBody);
        }

        return builder.build();
    }

}
