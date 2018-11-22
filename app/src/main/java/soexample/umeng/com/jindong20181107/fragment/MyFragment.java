package soexample.umeng.com.jindong20181107.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.jindong20181107.AddressActivity;
import soexample.umeng.com.jindong20181107.LoginActivity;
import soexample.umeng.com.jindong20181107.OrderActivity;
import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.ShowActivity;
import soexample.umeng.com.jindong20181107.UserActivity;
import soexample.umeng.com.jindong20181107.adapter.TuiJianAdapter;
import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.base.BaseFragment;
import soexample.umeng.com.jindong20181107.my.presenter.MyPresenter;
import soexample.umeng.com.jindong20181107.my.view.MyView;

/**
 * Created by Shinelon on 2018/11/7.
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyView {

    @BindView(R.id.btn_user)
    ImageView btnUser;
    @BindView(R.id.txt_user)
    TextView txtUser;
    @BindView(R.id.my_rec)
    RecyclerView myRec;
    Unbinder unbinder;
    @BindView(R.id.btn_img)
    ImageView btnImg;
    @BindView(R.id.btn_my_img)
    ImageView btnMyImg;

    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list;
    private TuiJianAdapter adapter;
    private SharedPreferences sp;
    private int uid;
    private String token;
    private String username;

    @Override
    public void getTuiJian(HomeBean.DataBean.TuijianBean tuijianBean) {
        if (tuijianBean != null) {
            List<HomeBean.DataBean.TuijianBean.ListBeanX> data = tuijianBean.getList();
            if (data != null) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.my_item;
    }

    @Override
    protected void initData() {
        sp = getActivity().getSharedPreferences("Nickname", Context.MODE_PRIVATE);
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        myRec.setLayoutManager(layoutManager);
        adapter = new TuiJianAdapter(getActivity(), list);
        adapter.setOnTuiJianClickListener(new TuiJianAdapter.OnTuiJianClickListener() {
            @Override
            public void onTuiJianClick(String url, int pid) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                String spid = String.valueOf(pid);
                intent.putExtra("url", url);
                intent.putExtra("spid", spid);
                startActivity(intent);
            }
        });
        myRec.setAdapter(adapter);
        presenter.getData();
        btnMyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });
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

    @OnClick({R.id.btn_user, R.id.txt_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_user:
                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    String suid = String.valueOf(uid);
                    intent.putExtra("uid", suid);
                    intent.putExtra("token", token);
                    startActivity(intent);
                }
                break;
            case R.id.txt_user:
                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    String suid = String.valueOf(uid);
                    intent.putExtra("uid", suid);
                    intent.putExtra("token", token);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = sp.getInt("uid", 0);
        token = sp.getString("token", "");
        username = sp.getString("username", "");
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            txtUser.setText("登录/注册>");
            btnUser.setImageResource(R.drawable.user);
        } else {
            txtUser.setText(username);
            btnUser.setImageResource(R.drawable.yhtx);
        }
    }

    @OnClick(R.id.btn_img)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        startActivity(intent);
    }
}
