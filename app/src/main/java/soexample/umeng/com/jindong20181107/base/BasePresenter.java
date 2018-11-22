package soexample.umeng.com.jindong20181107.base;

/**
 * Created by Shinelon on 2018/11/8.
 */

public abstract class BasePresenter<V extends BaseView> {
    protected V baseView;
    public BasePresenter(){
        initModel();
    }

    public void attach(V baseView){
        this.baseView=baseView;
    }

    public void detach() {
        if (baseView!=null){
            baseView=null;
        }
    }


    protected abstract void initModel();
}
