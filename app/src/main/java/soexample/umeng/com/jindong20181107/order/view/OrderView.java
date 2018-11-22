package soexample.umeng.com.jindong20181107.order.view;

import soexample.umeng.com.jindong20181107.bane.CreatOrder;
import soexample.umeng.com.jindong20181107.bane.GetOrder;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/19.
 */

public interface OrderView extends BaseView {
    void creatOrder(CreatOrder data);

    void getOrder(GetOrder data);

    void failed(Exception e);
}
