package soexample.umeng.com.jindong20181107.shopping.view;

import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/12.
 */

public interface ShoppingView extends BaseView{
  void successful(ShoppingBean data);

  void failed(Exception e);
}
