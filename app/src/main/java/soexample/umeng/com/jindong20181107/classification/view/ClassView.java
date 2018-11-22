package soexample.umeng.com.jindong20181107.classification.view;

import java.util.List;

import soexample.umeng.com.jindong20181107.bane.ProductBean;
import soexample.umeng.com.jindong20181107.bane.ShopBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/9.
 */

public interface ClassView extends BaseView {
    void getShop(List<ShopBean.DataBean> shopBean);

    void getProduct(List<ProductBean.DataBean> productBean);

    void failed(Exception e);
}
