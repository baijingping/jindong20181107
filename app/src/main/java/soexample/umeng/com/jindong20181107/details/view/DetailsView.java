package soexample.umeng.com.jindong20181107.details.view;

import soexample.umeng.com.jindong20181107.bane.DetailsBean;
import soexample.umeng.com.jindong20181107.base.BaseView;

/**
 * Created by Shinelon on 2018/11/14.
 */

public interface DetailsView extends BaseView {
    void successful(DetailsBean detailsBean);

    void failed(Exception e);
}
