package soexample.umeng.com.jindong20181107.addr.getaddrs.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.GetAddrsBean;
import soexample.umeng.com.jindong20181107.bane.SetAddrBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class GetAddrsModel {
    public void getData(int uid, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.getAddrs(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetAddrsBean>() {
                    @Override
                    public void accept(GetAddrsBean getAddrsBean) throws Exception {
                         if (getAddrsBean!=null & "0".equals(getAddrsBean.getCode())){
                             callBack.successful(getAddrsBean);
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
    public void getData2(int uid, int addrid, int status, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.setAddrs(uid,addrid,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SetAddrBean>() {
                    @Override
                    public void accept(SetAddrBean setAddrBean) throws Exception {
                        if (setAddrBean!=null & "0".equals(setAddrBean.getCode())){
                            callBack.successful(setAddrBean);
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
