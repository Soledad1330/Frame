package com.lvchuang.library.db.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:检测 字段是否已经被标注为 非数据库字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}