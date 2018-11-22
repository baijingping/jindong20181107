package soexample.umeng.com.jindong20181107.my.user.presenter;

import soexample.umeng.com.jindong20181107.bane.UserBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.my.user.model.UserModel;
import soexample.umeng.com.jindong20181107.my.user.view.UserView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class UserPresenter extends BasePresenter<UserView> {

    private UserModel model;

    @Override
    protected void initModel() {
        model = new UserModel();
    }

    public void getData(int uid,String token){
        model.getData(uid, token, new ICallBack() {
            @Override
            public void successful(Object o) {
                UserBean data= (UserBean) o;
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
