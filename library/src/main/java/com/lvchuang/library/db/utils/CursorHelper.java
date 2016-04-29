package com.lvchuang.library.db.utils;


import android.database.Cursor;

import com.lvchuang.library.DBUtil;
import com.lvchuang.library.db.entitiy.DBModel;
import com.lvchuang.library.db.entitiy.ManyToOne;
import com.lvchuang.library.db.entitiy.OneToMany;
import com.lvchuang.library.db.entitiy.Property;
import com.lvchuang.library.db.entitiy.TableInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:游标操作的帮助类
 */
public class CursorHelper {
    /**
     * 获取一个已保存的JavaBean对象
     *
     * @param cursor
     *            游标
     * @param clazz
     *            JavaBean.class
     * @param db
     *            DBUtil对象引用
     * @return
     */
    public static <T> T getEntity(Cursor cursor, Class<T> clazz, DBUtil db) {
        try {
            if (cursor != null) {
                // 读取表信息
                TableInfo table = TableInfo.get(clazz);
                // 读取列数
                int columnCount = cursor.getColumnCount();
                if (columnCount > 0) {
                    // 创建JavaBean对象
                    T entity = clazz.newInstance();
                    // 设置JavaBean的每一个属性
                    for (int i = 0; i < columnCount; i++) {
                        String column = cursor.getColumnName(i);
                        Property property = table.propertyMap.get(column);
                        if (property != null) {
                            property.setValue(entity, cursor.getString(i));
                        } else {
                            if (table.getId().getColumn().equals(column)) {
                                table.getId().setValue(entity,
                                        cursor.getString(i));
                            }
                        }

                    }
                    /**
                     * 处理OneToMany的lazyLoad形式
                     */
                    for (OneToMany oneToManyProp : table.oneToManyMap.values()) {
                        if (oneToManyProp.getDataType() == OneToManyLazyLoader.class) {
                            OneToManyLazyLoader oneToManyLazyLoader = new OneToManyLazyLoader(
                                    entity, clazz, oneToManyProp.getOneClass(),
                                    db);
                            oneToManyProp.setValue(entity, oneToManyLazyLoader);
                        }
                    }

                    /**
                     * 处理ManyToOne的lazyLoad形式
                     */
                    for (ManyToOne manyToOneProp : table.manyToOneMap.values()) {
                        if (manyToOneProp.getDataType() == ManyToOneLazyLoader.class) {
                            ManyToOneLazyLoader manyToOneLazyLoader = new ManyToOneLazyLoader(
                                    entity, clazz,
                                    manyToOneProp.getManyClass(), db);
                            manyToOneProp.setValue(entity, manyToOneLazyLoader);
                        }
                    }
                    return entity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据库的模型（将数据库转换为map集合）
     *
     * @param cursor
     * @return
     */
    public static DBModel getDBModel(Cursor cursor) {
        if (cursor != null && cursor.getColumnCount() > 0) {
            DBModel model = new DBModel();
            int columnCount = cursor.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                model.set(cursor.getColumnName(i), cursor.getString(i));
            }
            return model;
        }
        return null;
    }

    /**
     * 将数据库模型转换为 JavaBean对象
     *
     * @param DBModel
     * @param clazz
     *            待生成的JavaBean对象
     */
    public static <T> T DBModel2Entity(DBModel DBModel, Class<?> clazz) {
        if (DBModel != null) {
            HashMap<String, Object> dataMap = DBModel.getDataMap();
            try {
                @SuppressWarnings("unchecked")
                T entity = (T) clazz.newInstance();
                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    String column = entry.getKey();
                    TableInfo table = TableInfo.get(clazz);
                    Property property = table.propertyMap.get(column);
                    if (property != null) {
                        property.setValue(entity,
                                entry.getValue() == null ? null : entry
                                        .getValue().toString());
                    } else {
                        if (table.getId().getColumn().equals(column)) {
                            table.getId().setValue(
                                    entity,
                                    entry.getValue() == null ? null : entry
                                            .getValue().toString());
                        }
                    }

                }
                return entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
