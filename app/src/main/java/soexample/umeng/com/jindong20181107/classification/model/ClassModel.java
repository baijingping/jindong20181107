package soexample.umeng.com.jindong20181107.classification.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soexample.umeng.com.jindong20181107.api.Api;
import soexample.umeng.com.jindong20181107.bane.ProductBean;
import soexample.umeng.com.jindong20181107.bane.ShopBean;
import soexample.umeng.com.jindong20181107.utils.HttpUtils;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/9.
 */

public class ClassModel {
    public void getData(final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.shop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopBean>() {
                    @Override
                    public void accept(ShopBean shopBean) throws Exception {
                        if (shopBean!=null& "0".equals(shopBean.getCode())){
                            callBack.successful(shopBean);
                        }else {
                            callBack.failed(new Exception("请求错误"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                            callBack.failed(new Exception("网络错误"));
                    }
                });
    }
    public void getProduct(int cid, final ICallBack callBack){
        Api api = HttpUtils.getInstance().create(Api.class);
        api.product(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductBean>() {
                    @Override
                    public void accept(ProductBean productBean) throws Exception {
                        if (productBean!=null& "0".equals(productBean.getCode())){
                            callBack.successful(productBean);
                        }else {
                            callBack.failed(new Exception("请求错误"));
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
