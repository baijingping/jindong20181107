package soexample.umeng.com.jindong20181107.addr.getdefaultaddr.view;

import soexample.umeng.com.jindong20181107.bane.GetDefaultAddrBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/20.
 */

public interface GetDegailtAddrView extends BaseView {
    void successful(GetDefaultAddrBean data);

    void failed(Exception e);
}
