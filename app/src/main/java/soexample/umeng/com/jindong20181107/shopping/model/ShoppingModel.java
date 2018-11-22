package soexample.umeng.com.jindong20181107.shopping.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/12.
 */

public class ShoppingModel {
    public void getData(int uid, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.shopping(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingBean>() {
                    @Override
                    public void accept(ShoppingBean shoppingBean) throws Exception {
                         if (shoppingBean!=null & "0".equals(shoppingBean.getCode())){
                             callBack.successful(shoppingBean);
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
