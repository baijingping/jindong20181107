package soexample.umeng.com.jindong20181107.joincart.presenter;

import soexample.umeng.com.jindong20181107.bane.JoinCart;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.joincart.model.JoinCartModel;
import soexample.umeng.com.jindong20181107.joincart.view.JoinCartView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/15.
 */

public class JoinCartPresenter extends BasePresenter<JoinCartView> {

    private JoinCartModel model;

    @Override
    protected void initModel() {
        model = new JoinCartModel();
    }

    public void getData(int uid,int pid){
        model.getData(uid, pid, new ICallBack() {
            @Override
            public void successful(Object o) {
                JoinCart data= (JoinCart) o;
                if (data!=null){
                    baseView.succcessful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }
}
