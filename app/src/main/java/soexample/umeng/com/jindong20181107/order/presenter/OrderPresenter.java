package soexample.umeng.com.jindong20181107.order.presenter;

import soexample.umeng.com.jindong20181107.bane.CreatOrder;
import soexample.umeng.com.jindong20181107.bane.GetOrder;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.order.model.OrderModel;
import soexample.umeng.com.jindong20181107.order.view.OrderView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;


/**
 * Created by Shinelon on 2018/11/19.
 */

public class OrderPresenter extends BasePresenter<OrderView>{

    private OrderModel model;

    @Override
    protected void initModel() {
        model = new OrderModel();
    }

    public void CreatOrder(int uid,String price){
        model.CreatOrder(uid, price, new ICallBack() {
            @Override
            public void successful(Object o) {
                CreatOrder data= (CreatOrder) o;
                if (data!=null){
                    baseView.creatOrder(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }

    public void getOrder(int uid,int page){
        model.getOrder(uid,page, new ICallBack() {
            @Override
            public void successful(Object o) {
                GetOrder data= (GetOrder) o;
                if (data!=null){
                    baseView.getOrder(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }

}
