package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.bane.CreatOrder;
import soexample.umeng.com.jindong20181107.bane.GetOrder;
import soexample.umeng.com.jindong20181107.order.presenter.OrderPresenter;
import soexample.umeng.com.jindong20181107.order.view.OrderView;

public class OrderActivity extends AppCompatActivity implements OrderView {
    private static final String TAG = "OrderActivity";
    @BindView(R.id.txt_order_price)
    EditText txtOrderPrice;
    @BindView(R.id.txt_create_order)
    TextView txtCreateOrder;
    @BindView(R.id.order_list)
    Button orderList;
    private OrderPresenter presenter;
    private SharedPreferences sp;
    private String price;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        Log.i(TAG, "onCreate: "+uid);
        presenter=new OrderPresenter();
        presenter.attach(this);
    }

    @OnClick({R.id.txt_create_order, R.id.order_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_create_order:
                price = txtOrderPrice.getText().toString();
                txtOrderPrice.setText("");
                presenter.CreatOrder(uid,price);
                Log.i(TAG, "initData: "+price);
                break;
            case R.id.order_list:
                Intent intent=new Intent(this,GetOrderActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void creatOrder(CreatOrder data) {
        if (data!=null){
            Toast.makeText(this,data.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getOrder(GetOrder data) {

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
