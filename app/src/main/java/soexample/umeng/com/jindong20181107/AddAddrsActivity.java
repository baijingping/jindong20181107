package soexample.umeng.com.jindong20181107;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.addr.addaddr.presenter.AddAddrPresenter;
import soexample.umeng.com.jindong20181107.addr.addaddr.view.AddAddrView;
import soexample.umeng.com.jindong20181107.bane.AddAddrBean;

public class AddAddrsActivity extends AppCompatActivity implements AddAddrView {

    @BindView(R.id.a_addr)
    EditText aAddr;
    @BindView(R.id.a_mobile)
    EditText aMobile;
    @BindView(R.id.a_name)
    EditText aName;
    @BindView(R.id.btn_add)
    Button btnAdd;

    private AddAddrPresenter presenter;
    private SharedPreferences sp;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddr);
        ButterKnife.bind(this);
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        presenter=new AddAddrPresenter();
        presenter.attach(this);

    }

    @Override
    public void successful(AddAddrBean data) {
        if (data!=null){
            Toast.makeText(this, data.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        String addr = aAddr.getText().toString();
        String mobile = aMobile.getText().toString();
        String name = aName.getText().toString();
        presenter.getData(uid,addr,mobile,name);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
