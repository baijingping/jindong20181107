package soexample.umeng.com.jindong20181107;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.bane.Regist;
import soexample.umeng.com.jindong20181107.my.regist.presenter.RegistPresenter;
import soexample.umeng.com.jindong20181107.my.regist.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView {

    @BindView(R.id.ee_username)
    EditText eeUsername;
    @BindView(R.id.ee_password)
    EditText eePassword;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.txt_regist)
    Button txtRegist;

    private RegistPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        presenter=new RegistPresenter();
        presenter.attach(this);
    }

    @OnClick(R.id.txt_regist)
    public void onViewClicked() {
        presenter.check();
    }

    @Override
    public void successful(Regist regist) {
        Toast.makeText(this,regist.getMsg(),Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public String getUsername() {
        return eeUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return eePassword.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword.getText().toString();
    }

    @Override
    public void check(boolean isChecked, String msg) {
        if (!isChecked) {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }else {
            presenter.getData(getUsername(),getConfirmPassword());
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
