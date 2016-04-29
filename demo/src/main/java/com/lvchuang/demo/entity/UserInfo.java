package com.lvchuang.demo.entity;

import com.lvchuang.library.db.annotate.Id;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class UserInfo {
    @Id
    private int id;

    private String account;

    private String password;

    private long effectiveTime;

    private String token;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
