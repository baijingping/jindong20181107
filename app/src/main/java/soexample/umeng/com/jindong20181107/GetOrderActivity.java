package soexample.umeng.com.jindong20181107;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.jindong20181107.adapter.GetOrderAdapter;
import soexample.umeng.com.jindong20181107.bane.CreatOrder;
import soexample.umeng.com.jindong20181107.bane.GetOrder;
import soexample.umeng.com.jindong20181107.order.presenter.OrderPresenter;
import soexample.umeng.com.jindong20181107.order.view.OrderView;

public class GetOrderActivity extends AppCompatActivity implements OrderView {

    @BindView(R.id.show_list)
    XRecyclerView showList;
    private OrderPresenter presenter;
    private List<GetOrder.DataBean> list;
    private int uid;
    private GetOrderAdapter adapter;
    private int page=1;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order);
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        uid = sp.getInt("uid", page);
        presenter=new OrderPresenter();
        presenter.attach(this);
        initData();
        presenter.getOrder(uid,1);
    }

    private void initData() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        showList.setLayoutManager(layoutManager);
        adapter=new GetOrderAdapter(this,list);
        showList.setAdapter(adapter);
        showList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getOrder(uid,page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showList.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getOrder(uid,page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showList.loadMoreComplete();
                    }
                },3000);
            }
        });
    }

    @Override
    public void creatOrder(CreatOrder data) {

    }

    @Override
    public void getOrder(GetOrder data) {
        if (data!=null){
            if ("1".equals(data.getPage())) {
                list.clear();
            }
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
