package soexample.umeng.com.jindong20181107.home.presenter;

import java.util.List;

import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.home.model.HomeModel;
import soexample.umeng.com.jindong20181107.home.view.HomeView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/8.
 */

public class HomePresenter extends BasePresenter<HomeView> {

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
                    baseView.getBanner(data.getData().getBanner());
                    baseView.getFenlei(data.getData().getFenlei());
                    baseView.getMiaoshas(data.getData().getMiaosha());
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }
}
