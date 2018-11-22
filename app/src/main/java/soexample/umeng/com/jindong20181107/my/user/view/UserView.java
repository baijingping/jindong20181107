package soexample.umeng.com.jindong20181107.my.user.view;

import soexample.umeng.com.jindong20181107.bane.UserBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/14.
 */

public interface UserView extends BaseView {
    void successful(UserBean user);

    void failed(Exception e);
}
