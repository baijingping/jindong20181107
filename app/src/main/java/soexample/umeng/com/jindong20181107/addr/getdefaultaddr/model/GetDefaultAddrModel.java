package soexample.umeng.com.jindong20181107.addr.getdefaultaddr.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.GetDefaultAddrBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class GetDefaultAddrModel {
    public void getData(int uid, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.getDefaultAddr(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetDefaultAddrBean>() {
                    @Override
                    public void accept(GetDefaultAddrBean getDefaultAddrBean) throws Exception {
                        if (getDefaultAddrBean!=null & "0".equals(getDefaultAddrBean.getCode())){
                            callBack.successful(getDefaultAddrBean);
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
