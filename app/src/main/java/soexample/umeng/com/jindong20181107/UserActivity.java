package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.bane.UserBean;
import soexample.umeng.com.jindong20181107.my.user.presenter.UserPresenter;
import soexample.umeng.com.jindong20181107.my.user.view.UserView;

public class UserActivity extends AppCompatActivity implements UserView {
    private static final String TAG = "UserActivity";
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.birthday)
    EditText birthday;
    @BindView(R.id.log_out)
    Button logOut;
    private String token;
    private Integer uid;
    private SharedPreferences sp;
    private UserPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String suid = intent.getStringExtra("uid");
        token = intent.getStringExtra("token");
        uid = Integer.valueOf(suid);
        Log.i(TAG, "token" + token + "uid" + uid);
        initData();
    }

    private void initData() {
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        presenter=new UserPresenter();
        presenter.attach(this);
        presenter.getData(uid,token);
    }

    @OnClick(R.id.log_out)
    public void onViewClicked() {
        sp.edit().clear().commit();
        finish();
    }

    @Override
    public void successful(UserBean user) {
        UserBean.DataBean data = user.getData();
        name.setText(data.getMobile());
        phone.setText(data.getMobile());
        birthday.setText(data.getCreatetime());
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(UserActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }

}
