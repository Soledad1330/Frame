package com.lvchuang.demo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lvchuang.demo.R;
import com.lvchuang.demo.config.AppConst;
import com.lvchuang.demo.entity.UserInfo;
import com.lvchuang.library.DBUtil;
import com.lvchuang.library.HttpUtil;
import com.lvchuang.library.LCActivity;
import com.lvchuang.library.http.callback.StringCallback;
import com.lvchuang.library.ui.port.BindView;
import com.lvchuang.library.utils.DialogUtil;
import com.lvchuang.library.utils.LogUtil;
import com.lvchuang.library.utils.SharePreferUtil;
import com.lvchuang.library.utils.StringUtil;

import java.util.List;

import okhttp3.Call;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:登陆界面
 */
public class LoginActivity extends LCActivity {
    @BindView(id = R.id.login_et_name)
    private EditText login_et_name;
    @BindView(id = R.id.login_et_psw)
    private EditText login_et_psw;
    @BindView(id = R.id.login_cb_rember)
    private CheckBox login_cb_rember;
    @BindView(id = R.id.login_btn_login, click = true)
    private Button login_btn_login;

    private String mAccountStr;
    private String mPswStr;

    private boolean isRemberChecked = false;

    private DBUtil dbUtil;

    private UserInfo userInfo;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_login);
    }

    @Override
    public void initData() {
        super.initData();

        dbUtil = DBUtil.create(mContext);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        isRemberChecked = SharePreferUtil.readBoolean(mContext, AppConst.LOGIN_SP_NAME, AppConst.LOGIN_REMBER_CHECKED, false);
        if (isRemberChecked) {
            List<UserInfo> info = dbUtil.findAll(UserInfo.class);
            if (info != null && info.size() > 0) {
                userInfo = info.get(info.size() - 1);
            }
            if (userInfo != null) {
                if (!StringUtil.isEmpty(userInfo.getAccount()) && !StringUtil.isEmpty(userInfo.getPassword())) {
                    mAccountStr = userInfo.getAccount();
                    mPswStr = userInfo.getPassword();
                    login_et_name.setText(mAccountStr);
                    login_et_psw.setText(mPswStr);
                }
            }
            login_cb_rember.setChecked(true);
        } else {
            login_cb_rember.setChecked(false);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        if (v.getId() == R.id.login_btn_login) {
            mAccountStr = login_et_name.getText().toString().trim();
            mPswStr = login_et_psw.getText().toString().trim();
            if (StringUtil.isEmpty(mAccountStr) || StringUtil.isEmpty(mPswStr)) {
                DialogUtil.toast("Please edit full~");
            } else {
                if (login_cb_rember.isChecked()) {
                    SharePreferUtil.write(mContext, AppConst.LOGIN_SP_NAME, AppConst.LOGIN_REMBER_CHECKED, true);
                    UserInfo user = new UserInfo();
                    user.setAccount(mAccountStr);
                    user.setPassword(mPswStr);
                    dbUtil.save(user);
                } else {
                    SharePreferUtil.write(mContext, AppConst.LOGIN_SP_NAME, AppConst.LOGIN_REMBER_CHECKED, false);
                }
                doLogin();
            }
        }
    }


    public void doLogin() {

        HttpUtil.post().url("http://tempuri.org/?wsdl")//
                .tag(this).
                addHeader("Method", "Login").
                addParams("UserID", mAccountStr).
                addParams("Password", mPswStr).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                DialogUtil.toast(e.toString());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.debug(response);
                System.out.print("---"+response);
                skipActivity(LoginActivity.this,HomeActivity.class);
            }
        });
    }
}
