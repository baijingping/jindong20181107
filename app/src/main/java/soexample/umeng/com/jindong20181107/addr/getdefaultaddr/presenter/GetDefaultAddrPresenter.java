package soexample.umeng.com.jindong20181107.addr.getdefaultaddr.presenter;

import soexample.umeng.com.jindong20181107.addr.getdefaultaddr.model.GetDefaultAddrModel;
import soexample.umeng.com.jindong20181107.addr.getdefaultaddr.view.GetDegailtAddrView;
import soexample.umeng.com.jindong20181107.bane.GetDefaultAddrBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class GetDefaultAddrPresenter extends BasePresenter<GetDegailtAddrView>{

    private GetDefaultAddrModel model;

    @Override
    protected void initModel() {
        model = new GetDefaultAddrModel();
    }

    public void getData(int uid){
        model.getData(uid, new ICallBack() {
            @Override
            public void successful(Object o) {
                GetDefaultAddrBean data= (GetDefaultAddrBean) o;
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
