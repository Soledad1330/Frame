package com.lvchuang.widget.menu.sidemenu.model;

import com.lvchuang.widget.menu.sidemenu.interfaces.Resourceble;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class SlideMenuItem implements Resourceble {
    private String name;
    private int imageRes;

    public SlideMenuItem(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
