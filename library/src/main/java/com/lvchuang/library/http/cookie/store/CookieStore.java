package com.lvchuang.library.http.cookie.store;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public interface CookieStore
{

    public void add(HttpUrl uri, List<Cookie> cookie);

    public List<Cookie> get(HttpUrl uri);

    public List<Cookie> getCookies();

    public boolean remove(HttpUrl uri, Cookie cookie);

    public boolean removeAll();


}
