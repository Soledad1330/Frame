package com.lvchuang.library.db.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:可以设置默认值的属性列
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
    public String column() default "";

    public String defaultValue() default "";
}
