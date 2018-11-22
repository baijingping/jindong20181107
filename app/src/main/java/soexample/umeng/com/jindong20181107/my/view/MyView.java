package soexample.umeng.com.jindong20181107.my.view;

import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/9.
 */

public interface MyView extends BaseView {
    void getTuiJian(HomeBean.DataBean.TuijianBean tuijianBean);

    void failed(Exception e);
}
