package soexample.umeng.com.jindong20181107.addr.addaddr.view;

import soexample.umeng.com.jindong20181107.bane.AddAddrBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/20.
 */

public interface AddAddrView extends BaseView {
    void successful(AddAddrBean data);

    void failed(Exception e);
}
