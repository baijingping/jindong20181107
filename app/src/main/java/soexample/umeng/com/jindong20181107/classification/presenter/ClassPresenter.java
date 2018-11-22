package soexample.umeng.com.jindong20181107.classification.presenter;

import soexample.umeng.com.jindong20181107.bane.ProductBean;
import soexample.umeng.com.jindong20181107.bane.ShopBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.classification.model.ClassModel;
import soexample.umeng.com.jindong20181107.classification.view.ClassView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/9.
 */

public class ClassPresenter extends BasePresenter<ClassView> {

    private ClassModel model;

    @Override
    protected void initModel() {
        model = new ClassModel();
    }

    public void getData(){
          model.getData(new ICallBack() {
              @Override
              public void successful(Object o) {
                  ShopBean shopBean= (ShopBean) o;
                  if (shopBean!=null) {
                      baseView.getShop(shopBean.getData());
                  }
              }

              @Override
              public void failed(Exception e) {
                    baseView.failed(e);
              }
          });
    }

    public void getProduct(int cid){
        model.getProduct(cid, new ICallBack() {
            @Override
            public void successful(Object o) {
                ProductBean productBean= (ProductBean) o;
                if (productBean!=null){
                    baseView.getProduct(productBean.getData());
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }

}
