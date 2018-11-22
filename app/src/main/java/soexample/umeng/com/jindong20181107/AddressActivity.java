package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.out_login)
    Button outLogin;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        sp = getSharedPreferences("Nickname", MODE_PRIVATE);
    }

    @OnClick({R.id.address, R.id.out_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address:
                Intent intent=new Intent(this,ShippingActivity.class);
                startActivity(intent);
                break;
            case R.id.out_login:
                sp.edit().clear().commit();
                finish();
                break;
        }
    }
}
