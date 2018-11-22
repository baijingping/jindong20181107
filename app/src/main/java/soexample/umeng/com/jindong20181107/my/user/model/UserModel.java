package soexample.umeng.com.jindong20181107.my.user.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.UserBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class UserModel {
    public void getData(final int uid, String token, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.user(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                         if (userBean!=null & "0".equals(userBean.getCode())){
                             callBack.successful(userBean);
                         }else {
                             callBack.failed(new Exception("数据请求错误"));
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
