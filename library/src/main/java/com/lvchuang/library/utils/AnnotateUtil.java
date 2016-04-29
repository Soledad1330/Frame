package com.lvchuang.library.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

import com.lvchuang.library.ui.port.BindView;

import java.lang.reflect.Field;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:注解工具类
 */
public class AnnotateUtil {
    /**
     * 必须在setContentView之后调用
     *
     * @param aty
     */
    public static void initBindView(Activity aty) {
        initBindView(aty, aty.getWindow().getDecorView());
    }

    /**
     * 必须在setContentView之后调用
     *
     * @param frag
     */
    public static void initBindView(Fragment frag) {
        initBindView(frag, frag.getActivity().getWindow().getDecorView());
    }

    /**
     * 必须在setContentView之后调用
     *
     * @param view
     */
    public static void initBindView(View view) {
        Context context = view.getContext();
        if (context instanceof Activity) {
            initBindView((Activity) context);
        } else {
            throw new RuntimeException("view must into Activity");
        }

    }

    public static void initBindView(Object currectClass, View sourceView) {
        //通过反射获取到全部属性,反射的字段可能是一个类字段或者实例字段
        Field[] fields = currectClass.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.id();
                    boolean clickLis = bindView.click();
                    field.setAccessible(true);
                    if (clickLis) {
                        sourceView.findViewById(viewId).setOnClickListener((View.OnClickListener) currectClass);
                    }
                    //将currectClass的field赋值为sourceView.findViewById(viewId)
                    try {
                        field.set(currectClass, sourceView.findViewById(viewId));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}
