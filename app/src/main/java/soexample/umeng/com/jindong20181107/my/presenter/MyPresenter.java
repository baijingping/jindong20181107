package soexample.umeng.com.jindong20181107.my.presenter;

import android.app.DownloadManager;

import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.home.model.HomeModel;
import soexample.umeng.com.jindong20181107.my.view.MyView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/9.
 */

public class MyPresenter extends BasePresenter<MyView> {

    private HomeModel model;

    @Override
    protected void initModel() {
        model = new HomeModel();
    }

    public void getData(){
        model.getData(new ICallBack() {
            @Override
            public void successful(Object o) {
                HomeBean data= (HomeBean) o;
                if (data!=null) {
                    baseView.getTuiJian(data.getData().getTuijian());
                }
            }

            @Override
            public void failed(Exception e) {
                   baseView.failed(e);
            }
        });
    }
}
