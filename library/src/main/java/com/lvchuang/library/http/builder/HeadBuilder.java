package com.lvchuang.library.http.builder;

import com.lvchuang.library.HttpUtil;
import com.lvchuang.library.http.request.OtherRequest;
import com.lvchuang.library.http.request.RequestCall;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, HttpUtil.METHOD.HEAD, url, tag, params, headers).build();
    }
}

