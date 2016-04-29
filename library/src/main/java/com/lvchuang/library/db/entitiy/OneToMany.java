package com.lvchuang.library.db.entitiy;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:一对多的字段
 */
public class OneToMany extends Property {

    private Class<?> oneClass;

    public Class<?> getOneClass() {
        return oneClass;
    }

    public void setOneClass(Class<?> oneClass) {
        this.oneClass = oneClass;
    }

}

