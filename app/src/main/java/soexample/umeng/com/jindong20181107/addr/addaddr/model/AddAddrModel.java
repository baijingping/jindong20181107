package soexample.umeng.com.jindong20181107.addr.addaddr.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.AddAddrBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class AddAddrModel {
    public void getData(int uid, String addr, String mobile, String name, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.addAddrs(uid,addr,mobile,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddAddrBean>() {
                    @Override
                    public void accept(AddAddrBean addAddrBean) throws Exception {
                        if (addAddrBean!=null & "0".equals(addAddrBean.getCode())){
                            callBack.successful(addAddrBean);
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
