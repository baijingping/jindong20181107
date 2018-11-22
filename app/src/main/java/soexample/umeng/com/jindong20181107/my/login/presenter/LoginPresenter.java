package soexample.umeng.com.jindong20181107.my.login.presenter;

import android.text.TextUtils;

import soexample.umeng.com.jindong20181107.bane.LoginBane;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.my.login.model.LoginModel;
import soexample.umeng.com.jindong20181107.my.login.view.LoginView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/13.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel model;

    @Override
    protected void initModel() {
        model = new LoginModel();
    }
    public void check(){
        if (TextUtils.isEmpty(baseView.getUsername())){
            baseView.check(false,"用户名不能为空");
        }else if (TextUtils.isEmpty(baseView.getPassword())){
            baseView.check(false,"密码不能为空");
        }else {
            baseView.check(true,"");
        }
    }
    public void getData(String mobile,String password){
        model.getLogin(mobile, password, new ICallBack() {
            @Override
            public void successful(Object o) {
                LoginBane data= (LoginBane) o;
                if (data!=null){
                    baseView.success(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }
}
