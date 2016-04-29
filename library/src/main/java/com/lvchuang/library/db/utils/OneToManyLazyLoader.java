package com.lvchuang.library.db.utils;


import com.lvchuang.library.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:一对多延迟加载类

 */
public class OneToManyLazyLoader<O, M> {
    O ownerEntity;
    Class<O> ownerClazz;
    Class<M> listItemClazz;
    DBUtil db;

    public OneToManyLazyLoader(O ownerEntity, Class<O> ownerClazz,
                               Class<M> listItemclazz, DBUtil db) {
        this.ownerEntity = ownerEntity;
        this.ownerClazz = ownerClazz;
        this.listItemClazz = listItemclazz;
        this.db = db;
    }

    List<M> entities;

    /**
     * 如果数据未加载，则调用loadOneToMany填充数据
     *
     * @return
     */
    public List<M> getList() {
        if (entities == null) {
            this.db.loadOneToMany(this.ownerEntity, this.ownerClazz,
                    this.listItemClazz);
        }
        if (entities == null) {
            entities = new ArrayList<M>();
        }
        return entities;
    }

    public void setList(List<M> value) {
        entities = value;
    }

}
