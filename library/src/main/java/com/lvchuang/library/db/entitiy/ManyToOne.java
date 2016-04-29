package com.lvchuang.library.db.entitiy;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description: 多对一的字段
 */
public class ManyToOne extends Property {

    private Class<?> manyClass;

    public Class<?> getManyClass() {
        return manyClass;
    }

    public void setManyClass(Class<?> manyClass) {
        this.manyClass = manyClass;
    }

}

