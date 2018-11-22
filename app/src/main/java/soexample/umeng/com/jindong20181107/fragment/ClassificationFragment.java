package soexample.umeng.com.jindong20181107.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.adapter.ProductAdapter;
import soexample.umeng.com.jindong20181107.adapter.ShopAdapter;
import soexample.umeng.com.jindong20181107.bane.ProductBean;
import soexample.umeng.com.jindong20181107.bane.ShopBean;
import soexample.umeng.com.jindong20181107.base.BaseFragment;
import soexample.umeng.com.jindong20181107.classification.presenter.ClassPresenter;
import soexample.umeng.com.jindong20181107.classification.view.ClassView;

/**
 * Created by Shinelon on 2018/11/7.
 */

public class ClassificationFragment extends BaseFragment<ClassPresenter> implements ClassView {

    @BindView(R.id.left_shop)
    RecyclerView leftShop;
    @BindView(R.id.right_product)
    RecyclerView rightProduct;
    Unbinder unbinder;

    private List<ShopBean.DataBean> shopList;
    private List<ProductBean.DataBean> productList;
    private ShopAdapter shopAdapter;
    private ProductAdapter productAdapter;

    @Override
    public void getShop(List<ShopBean.DataBean> shopBean) {
        if (shopBean!=null){
            shopList.clear();
            shopList.addAll(shopBean);
            shopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getProduct(List<ProductBean.DataBean> productBean) {
        if (productBean!=null){
                    productList.clear();
                    productList.addAll(productBean);
                    productAdapter.notifyDataSetChanged();
            }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(),""+e,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected ClassPresenter providePresenter() {
        return new ClassPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.classification_item;
    }

    @Override
    protected void initData() {
        shopList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        leftShop.setLayoutManager(layoutManager);
        shopAdapter=new ShopAdapter(getActivity(),shopList);
        leftShop.setAdapter(shopAdapter);

        productList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(getActivity());
        rightProduct.setLayoutManager(layoutManager1);
        productAdapter=new ProductAdapter(getActivity(),productList);
        rightProduct.setAdapter(productAdapter);
        shopAdapter.setOnShopClickListener(new ShopAdapter.OnShopClickListener() {
            @Override
            public void onProductClick(int id) {
                presenter.getProduct(id);
            }
        });
        presenter.getData();
        presenter.getProduct(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
