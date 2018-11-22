package soexample.umeng.com.jindong20181107.addr.getaddrs.presenter;

import soexample.umeng.com.jindong20181107.addr.getaddrs.model.GetAddrsModel;
import soexample.umeng.com.jindong20181107.addr.getaddrs.view.GetAddrsView;
import soexample.umeng.com.jindong20181107.bane.GetAddrsBean;
import soexample.umeng.com.jindong20181107.bane.SetAddrBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class GetAddrsPresenter extends BasePresenter<GetAddrsView> {

    private GetAddrsModel model;

    @Override
    protected void initModel() {
        model = new GetAddrsModel();
    }

    public void getData(int uid){
        model.getData(uid, new ICallBack() {
            @Override
            public void successful(Object o) {
                GetAddrsBean data= (GetAddrsBean) o;
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

    public void getSetAddr(int uid,int addrid,int status){
        model.getData2(uid, addrid, status, new ICallBack() {
            @Override
            public void successful(Object o) {
                SetAddrBean data= (SetAddrBean) o;
                if (data!=null){
                    baseView.setAddr(data);
                }
            }

            @Override
            public void failed(Exception e) {
                baseView.failed(e);
            }
        });
    }
}
