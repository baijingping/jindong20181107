package soexample.umeng.com.jindong20181107.addr.addaddr.presenter;

import soexample.umeng.com.jindong20181107.addr.addaddr.model.AddAddrModel;
import soexample.umeng.com.jindong20181107.addr.addaddr.view.AddAddrView;
import soexample.umeng.com.jindong20181107.bane.AddAddrBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class AddAddrPresenter extends BasePresenter<AddAddrView> {

    private AddAddrModel model;

    @Override
    protected void initModel() {
        model = new AddAddrModel();
    }

    public void getData(int uid,String addr,String mobile,String name){
        model.getData(uid, addr, mobile, name, new ICallBack() {
            @Override
            public void successful(Object o) {
                AddAddrBean data= (AddAddrBean) o;
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
