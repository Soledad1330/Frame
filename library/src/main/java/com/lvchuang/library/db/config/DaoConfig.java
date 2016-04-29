package com.lvchuang.library.db.config;

import android.content.Context;

import com.lvchuang.library.DBUtil;
import com.lvchuang.library.utils.LogUtil;


/**
 * Created by: Liu.ZhiYun on 2016/4/20.
 * Description:数据库配置器
 */
final public class DaoConfig {
    private Context mContext = null; // android上下文
    private String mDbName = "LCLibrary.db"; // 数据库名字
    private int dbVersion = 1; // 数据库版本
    private boolean debug = LogUtil.DEBUG_LOG; // 是否是调试模式（调试模式 增删改查的时候显示SQL语句）
    private DBUtil.DbUpdateListener dbUpdateListener;
    // private boolean saveOnSDCard = false;//是否保存到SD卡
    private String targetDirectory;// 数据库文件在sd卡中的目录

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    /**
     * 数据库名
     */
    public String getDbName() {
        return mDbName;
    }

    /**
     * 数据库名
     */
    public void setDbName(String dbName) {
        this.mDbName = dbName;
    }

    /**
     * 数据库版本
     */
    public int getDbVersion() {
        return dbVersion;
    }

    /**
     * 数据库版本
     */
    public void setDbVersion(int dbVersion) {
        this.dbVersion = dbVersion;
    }

    /**
     * 是否调试模式
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * 是否调试模式
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * 数据库升级时监听器
     *
     * @return
     */
    public DBUtil.DbUpdateListener getDbUpdateListener() {
        return dbUpdateListener;
    }

    /**
     * 数据库升级时监听器
     */
    public void setDbUpdateListener(DBUtil.DbUpdateListener dbUpdateListener) {
        this.dbUpdateListener = dbUpdateListener;
    }

    // public boolean isSaveOnSDCard() {
    // return saveOnSDCard;
    // }
    //
    // public void setSaveOnSDCard(boolean saveOnSDCard) {
    // this.saveOnSDCard = saveOnSDCard;
    // }

    /**
     * 数据库文件在sd卡中的目录
     */
    public String getTargetDirectory() {
        return targetDirectory;
    }

    /**
     * 数据库文件在sd卡中的目录
     */
    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }
}
