package soexample.umeng.com.jindong20181107.my.regist.view;

import soexample.umeng.com.jindong20181107.bane.Regist;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/14.
 */

public interface RegistView extends BaseView {
    void successful(Regist regist);

    String getUsername();

    String getPassword();

    String getConfirmPassword();

    void check(boolean isChecked,String msg);

    void failed(Exception e);

}
