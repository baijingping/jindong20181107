package soexample.umeng.com.jindong20181107.my.login.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.LoginBane;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/13.
 */

public class LoginModel {
    public void getLogin(String mobile, String password, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.login(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBane>() {
                    @Override
                    public void accept(LoginBane loginBane) throws Exception {
                        if (loginBane!=null & "0".equals(loginBane.getCode())){
                            callBack.successful(loginBane);
                        }else {
                            callBack.failed(new Exception("请求数据错误"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                           callBack.failed((Exception) throwable);
                    }
                });
    }
}
