package com.lvchuang.library.db.annotate;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description: Id主键配置,不配置的时候默认找类的id或_id字段作为主键，column不配置的是默认为字段名
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    /**
     * 设置为主键
     *
     * @return
     */
    public String column() default "";
}