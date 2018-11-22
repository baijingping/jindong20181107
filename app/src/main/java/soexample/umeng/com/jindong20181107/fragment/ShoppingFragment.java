package soexample.umeng.com.jindong20181107.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.jindong20181107.LoginActivity;
import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.adapter.ProductterAdapter;
import soexample.umeng.com.jindong20181107.adapter.ShopperAdapter;
import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.base.BaseFragment;
import soexample.umeng.com.jindong20181107.shopping.presenter.ShoppingPresenter;
import soexample.umeng.com.jindong20181107.shopping.view.ShoppingView;

/**
 * Created by Shinelon on 2018/11/7.
 */

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements ShoppingView {
    private static final String TAG = "ShoppingFragment";
    @BindView(R.id.txt_edit_or_finish)
    TextView txtEditOrFinish;
    @BindView(R.id.cb_total_select)
    CheckBox cbTotalSelect;
    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.btn_calu)
    Button btnCalu;
    @BindView(R.id.rv_shopper)
    RecyclerView rvShopper;
    Unbinder unbinder;
    Unbinder unbinder1;
    @BindView(R.id.cart_login)
    TextView cartLogin;


    private List<ShoppingBean.DataBean> list;
    private ShopperAdapter adapter;
    private SharedPreferences conn;
    private int uid;
    private boolean isFirst;


    @Override
    public void successful(ShoppingBean data) {
        if (data != null) {
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Log.i("aaa", e.getMessage());
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected ShoppingPresenter providePresenter() {
        return new ShoppingPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.shopping_item;
    }

    @Override
    protected void initData() {
        conn = getActivity().getSharedPreferences("Nickname", Context.MODE_PRIVATE);
        uid = conn.getInt("uid", 0);
        isFirst = conn.getBoolean("isFirst", true);
        Log.i(TAG, "uid=   " + uid);
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvShopper.setLayoutManager(layoutManager);
        adapter = new ShopperAdapter(getActivity(), list);
        adapter.setOnShopperClickListener(new ShopperAdapter.OnShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isCheck) {
                if (!isCheck) {
                    cbTotalSelect.setChecked(false);
                } else {
                    boolean isAllShopperChecked = true;
                    for (ShoppingBean.DataBean dataBean : list) {
                        if (!dataBean.isChecked()) {
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbTotalSelect.setChecked(isAllShopperChecked);
                }
                calculatePrice();
            }
        });
        adapter.setOnAddDecreaseProductListener(new ProductterAdapter.OnAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });
        rvShopper.setAdapter(adapter);
        islog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick(R.id.cb_total_select)
    public void onViewClicked() {
        boolean isChecked = cbTotalSelect.isChecked();
        for (ShoppingBean.DataBean dataBean : list) {
            dataBean.setChecked(isChecked);
            List<ShoppingBean.DataBean.ListBean> productList = dataBean.getList();
            for (ShoppingBean.DataBean.ListBean listBean : productList) {
                listBean.setBisChecked(isChecked);
            }
        }
        calculatePrice();
        adapter.notifyDataSetChanged();
    }

    // 计算商品总价
    private void calculatePrice() {
        // 遍历商家
        float totalPrice = 0;
        for (ShoppingBean.DataBean dataBean : list) {
            List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
            for (ShoppingBean.DataBean.ListBean listBean : list) {
                if (listBean.isBisChecked()) {
                    totalPrice += listBean.getNum() * listBean.getPrice();
                }
            }
        }
        txtTotalPrice.setText("总价:" + totalPrice);
    }

    @Override
    public void onResume() {
        super.onResume();
        islog();

    }

    private void islog() {
        if (isFirst) {
            rvShopper.setVisibility(View.GONE);
            cartLogin.setVisibility(View.VISIBLE);
            cartLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            rvShopper.setVisibility(View.VISIBLE);
            cartLogin.setVisibility(View.GONE);
            presenter.getData(uid);
        }
    }


}

