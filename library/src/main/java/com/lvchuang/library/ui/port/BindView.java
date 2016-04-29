package com.lvchuang.library.ui.port;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:注解式绑定控件
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {

    int id();

    boolean click() default false;
}
