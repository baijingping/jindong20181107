package soexample.umeng.com.jindong20181107.home.view;

import java.util.List;

import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/8.
 */

public interface HomeView extends BaseView {
    void getBanner(List<HomeBean.DataBean.BannerBean> bannerBean);

    void getFenlei(List<HomeBean.DataBean.FenleiBean> fenleiBean);

    void getMiaoshas(HomeBean.DataBean.MiaoshaBean miaoshaBean);

    void failed(Exception e);
}
