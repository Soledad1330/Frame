package com.lvchuang.library.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.lvchuang.library.ui.base.LCActivityManager;


/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:Dialog帮助类
 */
public class DialogUtil {

    private DialogUtil() {
    }

    private static class ClassHolder {
        private static final DialogUtil instance = new DialogUtil();
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static DialogUtil create() {
        return ClassHolder.instance;
    }

    /**
     * 显示一个toast
     *
     * @param msg
     */
    public static void toast(String msg) {
        try {
            toast(LCActivityManager.create().topActivity(), msg);
        } catch (Exception e) {
        }
    }

    /**
     * 显示一个long toast
     *
     * @param msg
     */
    public static void longToast(String msg) {
        try {
            longToast(LCActivityManager.create().topActivity(), msg);
        } catch (Exception e) {
        }
    }


    /**
     * 显示一个Toast
     *
     * @param context
     * @param msg
     */
    private static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个long Toast
     *
     * @param context
     * @param msg
     */
    private static void longToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 返回一个退出确认对话框
     */
    public void getExitDialog(final Context context, String title, DialogInterface.OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title);
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", l);
        builder.create();
        builder.show();
        builder = null;
    }
    /**
     * 返回一个自定义View对话框
     */
    public AlertDialog getDialogView(Context cxt, String title, View view) {
        AlertDialog dialog = new AlertDialog.Builder(cxt).create();
        dialog.setMessage(title);
        dialog.setView(view);
        dialog.show();
        return dialog;
    }

    /**
     * 返回一个日期对话框
     */
    public void getDateDialog(String title, final TextView textView) {
        final String[] time = DateUtil.getFormatDate("yyyy-MM-dd").split("-");
        final int year = StringUtil.toInt(time[0], 0);
        final int month = StringUtil.toInt(time[1], 1);
        final int day = StringUtil.toInt(time[2], 0);
        DatePickerDialog dialog = new DatePickerDialog(textView.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, year, month - 1, day);
        dialog.setTitle(title);
        dialog.show();
    }

    /**
     * 返回一个等待信息弹窗
     *
     * @param aty
     *            要显示弹出窗的Activity
     * @param msg
     *            弹出窗上要显示的文字
     * @param cancel
     *            dialog是否可以被取消
     */
    public static ProgressDialog getprogress(Activity aty, String msg, boolean cancel) {
        // 实例化一个ProgressBarDialog
        ProgressDialog progressDialog = new ProgressDialog(aty);
        progressDialog.setMessage(msg);
        progressDialog.getWindow().setLayout(DensityUtil.getScreenW(aty), DensityUtil.getScreenH(aty));
        progressDialog.setCancelable(cancel);
        // 设置ProgressBarDialog的显示样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        return progressDialog;
    }
}
