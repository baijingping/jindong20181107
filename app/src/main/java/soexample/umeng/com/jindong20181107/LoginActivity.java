package soexample.umeng.com.jindong20181107;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.adapter.ShopperAdapter;
import soexample.umeng.com.jindong20181107.bane.LoginBane;
import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.my.login.presenter.LoginPresenter;
import soexample.umeng.com.jindong20181107.my.login.view.LoginView;
import soexample.umeng.com.jindong20181107.shopping.presenter.ShoppingPresenter;
import soexample.umeng.com.jindong20181107.shopping.view.ShoppingView;

public class LoginActivity extends AppCompatActivity implements LoginView,ShoppingView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.dsf)
    TextView dsf;
    @BindView(R.id.btn_regist)
    TextView btnRegist;

    private LoginPresenter presenter;
    private SharedPreferences sp;
    private ShoppingPresenter shoppingPresenter;
    private List<ShoppingBean.DataBean> list;
    private ShopperAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter=new LoginPresenter();
        presenter.attach(this);
        list = new ArrayList<>();
        adapter = new ShopperAdapter(this, list);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.check();
            }
        });
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
        shoppingPresenter = new ShoppingPresenter();
        shoppingPresenter.attach(this);
    }

    @OnClick(R.id.btn_regist)
    public void onViewClicked() {
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
    }

    @Override
    public void success(LoginBane login) {
        sp.edit().putInt("uid", login.getData().getUid())
                .putString("token", login.getData().getToken())
                .putString("username",getUsername()).
                putBoolean("isFirst",false).commit();

        Toast.makeText(this, "登录成功" , Toast.LENGTH_SHORT).show();
        shoppingPresenter.getData(login.getData().getUid());
        finish();
    }

    @Override
    public String getUsername() {
        return edUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edPassword.getText().toString();
    }

    @Override
    public void check(boolean isChecked, String msg) {
        if (!isChecked){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }else {
            presenter.getData(getUsername(),getPassword());
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void successful(ShoppingBean data) {
        Toast.makeText(this, "登录成功" , Toast.LENGTH_SHORT).show();

        if (data != null) {
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Log.i(TAG, "aaa"+e.getMessage());
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
