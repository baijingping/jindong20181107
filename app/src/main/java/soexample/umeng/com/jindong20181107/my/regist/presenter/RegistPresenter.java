package soexample.umeng.com.jindong20181107.my.regist.presenter;

import android.text.TextUtils;

import soexample.umeng.com.jindong20181107.bane.Regist;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.my.regist.model.RegistModel;
import soexample.umeng.com.jindong20181107.my.regist.view.RegistView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class RegistPresenter extends BasePresenter<RegistView> {

    private RegistModel model;

    @Override
    protected void initModel() {
        model = new RegistModel();
    }

    public void check(){
        String username = baseView.getUsername();
        String password = baseView.getPassword();
        String confirmPassword = baseView.getConfirmPassword();
        if (TextUtils.isEmpty(username)){
            baseView.check(false,"用户名不能为空");
        }else if (TextUtils.isEmpty(password)){
            baseView.check(false,"密码不能为空");
        }else if (TextUtils.isEmpty(confirmPassword)){
            baseView.check(false,"请再次输入密码");
        }else if (!confirmPassword.equals(password)){
            baseView.check(false,"俩次密码不一致");
        }else {
            baseView.check(true,"");
        }
    }

    public void getData(String mobile,String password){
        model.getData(mobile, password, new ICallBack() {
            @Override
            public void successful(Object o) {
                Regist data= (Regist) o;
                if (data!=null){
                    baseView.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }
}
