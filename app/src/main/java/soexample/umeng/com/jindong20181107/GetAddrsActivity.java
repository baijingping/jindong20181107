package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.jindong20181107.adapter.GetAddrsAdapter;
import soexample.umeng.com.jindong20181107.addr.getaddrs.presenter.GetAddrsPresenter;
import soexample.umeng.com.jindong20181107.addr.getaddrs.view.GetAddrsView;
import soexample.umeng.com.jindong20181107.bane.GetAddrsBean;
import soexample.umeng.com.jindong20181107.bane.SetAddrBean;

public class GetAddrsActivity extends AppCompatActivity implements GetAddrsView {


    @BindView(R.id.getAddrs)
    RecyclerView getAddrs;
    private GetAddrsPresenter presenter;
    private List<GetAddrsBean.DataBean> list;
    private SharedPreferences sp;
    private int uid;
    private GetAddrsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_addrs2);
        ButterKnife.bind(this);
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        presenter = new GetAddrsPresenter();
        presenter.attach(this);
        initData();
        presenter.getData(uid);
    }

    private void initData() {
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        getAddrs.setLayoutManager(layoutManager);
        adapter=new GetAddrsAdapter(this,list);
        getAddrs.setAdapter(adapter);
        adapter.setOnGetAddrsClickListener(new GetAddrsAdapter.OnGetAddrsClickListener() {
            @Override
            public void onGetAddrsClick(int uid, int addrid, int status) {
                presenter.getSetAddr(uid,addrid,status);
                finish();
            }
        });
    }

    @Override
    public void successful(GetAddrsBean data) {
        if (data!=null){
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setAddr(SetAddrBean data) {
        Toast.makeText(this, data.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
