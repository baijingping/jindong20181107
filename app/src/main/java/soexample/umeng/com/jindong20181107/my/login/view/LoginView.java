package soexample.umeng.com.jindong20181107.my.login.view;

import android.content.Context;

import soexample.umeng.com.jindong20181107.bane.LoginBane;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/13.
 */

public interface LoginView extends BaseView {
    void success(LoginBane login);

    String getUsername();

    String getPassword();

    void check(boolean isChecked,String msg);

    Context getContext();

    void failed(Exception e);
}
