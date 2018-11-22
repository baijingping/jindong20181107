package soexample.umeng.com.jindong20181107.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2018/11/8.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this,view);
        initView(view);
        return view;
    }

    protected void initView(View view) {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=providePresenter();
        attachView();
        initData();
        initLinstener();
    }

    protected void initLinstener() {

    }


    protected void attachView() {
        presenter.attach(this);
    }

    protected abstract P providePresenter();
    protected abstract int provideLayoutId();
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
