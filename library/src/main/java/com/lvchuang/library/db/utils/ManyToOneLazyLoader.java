package com.lvchuang.library.db.utils;


import com.lvchuang.library.DBUtil;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:多对一延迟加载类
 */
public class ManyToOneLazyLoader<M, O> {
    M manyEntity;
    Class<M> manyClazz;
    Class<O> oneClazz;
    DBUtil db;

    public ManyToOneLazyLoader(M manyEntity, Class<M> manyClazz,
                               Class<O> oneClazz, DBUtil db) {
        this.manyEntity = manyEntity;
        this.manyClazz = manyClazz;
        this.oneClazz = oneClazz;
        this.db = db;
    }

    O oneEntity;
    boolean hasLoaded = false;

    /**
     * 如果数据未加载，则调用loadManyToOne填充数据
     *
     * @return
     */
    public O get() {
        if (oneEntity == null && !hasLoaded) {
            this.db.loadManyToOne(this.manyEntity, this.manyClazz,
                    this.oneClazz);
            hasLoaded = true;
        }
        return oneEntity;
    }

    public void set(O value) {
        oneEntity = value;
    }

}
