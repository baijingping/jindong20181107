package soexample.umeng.com.jindong20181107.order.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.CreatOrder;
import soexample.umeng.com.jindong20181107.bane.GetOrder;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/19.
 */

public class OrderModel {
    public void CreatOrder(int uid, String price, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.creatOrder(uid,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreatOrder>() {
                    @Override
                    public void accept(CreatOrder creatOrder) throws Exception {
                          if ("0".equals(creatOrder.getCode())){
                              callBack.successful(creatOrder);
                          }else if ("1".equals(creatOrder.getCode())){
                              callBack.failed(new Exception(creatOrder.getMsg()));
                          }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                          callBack.failed((Exception) throwable);
                    }
                });
    }
    public void getOrder(int uid,int page, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.getOrder(uid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetOrder>() {
                    @Override
                    public void accept(GetOrder getOrder) throws Exception {
                          if (getOrder!=null & "0".equals(getOrder.getCode())){
                              callBack.successful(getOrder);
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
