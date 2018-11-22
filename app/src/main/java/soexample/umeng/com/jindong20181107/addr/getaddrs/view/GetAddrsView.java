package soexample.umeng.com.jindong20181107.addr.getaddrs.view;

import soexample.umeng.com.jindong20181107.bane.GetAddrsBean;
import soexample.umeng.com.jindong20181107.bane.SetAddrBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/20.
 */

public interface GetAddrsView extends BaseView{
    void successful(GetAddrsBean data);

    void setAddr(SetAddrBean data);

    void failed(Exception e);
}
