package soexample.umeng.com.jindong20181107.joincart.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.JoinCart;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/15.
 */

public class JoinCartModel {
    public void getData(int uid, int pid, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.cart(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JoinCart>() {
                    @Override
                    public void accept(JoinCart cart) throws Exception {
                         if (cart!=null&"0".equals(cart.getCode())){
                             callBack.successful(cart);
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
