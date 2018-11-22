package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.addr.getdefaultaddr.presenter.GetDefaultAddrPresenter;
import soexample.umeng.com.jindong20181107.addr.getdefaultaddr.view.GetDegailtAddrView;
import soexample.umeng.com.jindong20181107.bane.GetDefaultAddrBean;

public class ShippingActivity extends AppCompatActivity implements GetDegailtAddrView {

    @BindView(R.id.getAddrs)
    TextView getAddrs;
    @BindView(R.id.addAddr)
    TextView addAddr;
    @BindView(R.id.addr)
    TextView addr;
    @BindView(R.id.d_addr)
    TextView dAddr;
    @BindView(R.id.d_addrud)
    TextView dAddrud;
    @BindView(R.id.d_mobile)
    TextView dMobile;
    @BindView(R.id.d_name)
    TextView dName;
    @BindView(R.id.d_status)
    TextView dStatus;
    @BindView(R.id.d_uid)
    TextView dUid;


    private GetDefaultAddrPresenter presenter;
    private SharedPreferences sp;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        ButterKnife.bind(this);
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        presenter = new GetDefaultAddrPresenter();
        presenter.attach(this);
        presenter.getData(uid);
    }


    @Override
    public void successful(GetDefaultAddrBean data) {
        GetDefaultAddrBean.DataBean data1 = data.getData();
        if (data1 != null) {
            dAddr.setText("addr: " + data1.getAddr());
            dAddrud.setText("addrid: "+data1.getAddrid());
            dMobile.setText("mobile: "+data1.getMobile());
            dName.setText("name: "+data1.getName());
            dStatus.setText("status "+data1.getStatus());
            dUid.setText("uid "+data1.getUid());
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.getAddrs, R.id.addAddr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getAddrs:
                Intent intent = new Intent(this, GetAddrsActivity.class);
                startActivity(intent);
                break;
            case R.id.addAddr:
                Intent intent2 = new Intent(this, AddAddrsActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData(uid);
    }
}
