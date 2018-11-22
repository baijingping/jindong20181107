package soexample.umeng.com.jindong20181107.shopping.presenter;

import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.shopping.model.ShoppingModel;
import soexample.umeng.com.jindong20181107.shopping.view.ShoppingView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/12.
 */

public class ShoppingPresenter extends BasePresenter<ShoppingView> {

    private ShoppingModel model;

    @Override
    protected void initModel() {
        model = new ShoppingModel();
    }

    public void getData(int uid){
        model.getData(uid, new ICallBack() {
            @Override
            public void successful(Object o) {
                ShoppingBean data= (ShoppingBean) o;
                if (data!=null){
                    baseView.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }
}
