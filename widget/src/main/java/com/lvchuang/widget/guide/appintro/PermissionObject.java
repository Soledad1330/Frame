package com.lvchuang.widget.guide.appintro;
/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class PermissionObject {

    String[] permission;
    int position;

    public PermissionObject(String[] permission, int position){
        this.permission = permission;
        this.position = position;
    }

    public String[] getPermission(){
        return permission;
    }

    public int getPosition(){
        return position;
    }
}
