package soexample.umeng.com.jindong20181107.joincart.view;

import soexample.umeng.com.jindong20181107.bane.JoinCart;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/15.
 */

public interface JoinCartView extends BaseView {
    void succcessful(JoinCart cart);

    void failed(Exception e);
}
