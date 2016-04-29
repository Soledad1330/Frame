package com.lvchuang.library.http.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public abstract class BitmapCallback extends Callback<Bitmap> {
    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }
    public void getMainColor(){
        String sos="you have a new  message";
    }
}

