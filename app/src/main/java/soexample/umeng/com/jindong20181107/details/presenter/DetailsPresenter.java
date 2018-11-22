package soexample.umeng.com.jindong20181107.details.presenter;

import soexample.umeng.com.jindong20181107.bane.DetailsBean;
import soexample.umeng.com.jindong20181107.base.BasePresenter;
import soexample.umeng.com.jindong20181107.details.model.DetailsModel;
import soexample.umeng.com.jindong20181107.details.view.DetailsView;
import soexample.umeng.com.jindong20181107.utils.ICallBack;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class DetailsPresenter extends BasePresenter<DetailsView> {

    private DetailsModel model;

    @Override
    protected void initModel() {
        model = new DetailsModel();
    }

    public void getData(String keywords,int page){
        model.getData(keywords, page, new ICallBack() {
            @Override
            public void successful(Object o) {
                DetailsBean data= (DetailsBean) o;
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
