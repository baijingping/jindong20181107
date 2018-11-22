package soexample.umeng.com.jindong20181107.my.regist.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.Regist;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class RegistModel {
    public void getData(String mobile, String password, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.getRegisterData(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Regist>() {
                    @Override
                    public void accept(Regist regist) throws Exception {
                       if (regist!=null){
                           callBack.successful(regist);
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
