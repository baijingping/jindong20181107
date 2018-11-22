package soexample.umeng.com.jindong20181107.details.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.DetailsBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class DetailsModel {
    public void getData(String keywords, int page, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.details(keywords,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailsBean>() {
                    @Override
                    public void accept(DetailsBean detailsBean) throws Exception {
                       if (detailsBean!=null & "0".equals(detailsBean.getCode())){
                           callBack.successful(detailsBean);
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
