package soexample.umeng.com.jindong20181107.home.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/8.
 */

public class HomeModel {
    public void getData(final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        if (homeBean!=null & "0".equals(homeBean.getCode())){
                            callBack.successful(homeBean);
                        }else {
                            callBack.failed(new Exception("请求失败"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.failed(new Exception("网络错误"));
                    }
                });
    }
}
