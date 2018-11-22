package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.jindong20181107.adapter.DetailsAdapter;
import soexample.umeng.com.jindong20181107.bane.DetailsBean;
import soexample.umeng.com.jindong20181107.details.presenter.DetailsPresenter;
import soexample.umeng.com.jindong20181107.details.view.DetailsView;

public class DatailsActivity extends AppCompatActivity implements DetailsView {

    @BindView(R.id.details_recy)
    RecyclerView detailsRecy;
    private String key;
    private DetailsPresenter presenter;
    private DetailsAdapter adapter;
    private List<DetailsBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datails);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        initData();
        presenter = new DetailsPresenter();
        presenter.attach(this);
        presenter.getData(key, 1);
    }

    private void initData() {
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        detailsRecy.setLayoutManager(layoutManager);
        adapter=new DetailsAdapter(this,list);
        adapter.setOnDetailsClickListener(new DetailsAdapter.OnDetailsClickListener() {
            @Override
            public void onDetailsClick(String url,int pid) {
                Intent intent=new Intent(DatailsActivity.this,ShowActivity.class);
                String spid = String.valueOf(pid);
                intent.putExtra("url", url);
                intent.putExtra("spid", spid);
                startActivity(intent);
            }
        });
        detailsRecy.setAdapter(adapter);
    }

    @Override
    public void successful(DetailsBean detailsBean) {
        if (detailsBean!=null){
            list.clear();
            list.addAll(detailsBean.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
