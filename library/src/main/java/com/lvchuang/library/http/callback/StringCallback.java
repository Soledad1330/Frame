package com.lvchuang.library.http.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }


}
