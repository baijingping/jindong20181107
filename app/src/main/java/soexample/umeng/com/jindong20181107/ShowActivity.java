package soexample.umeng.com.jindong20181107;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.bane.JoinCart;
import soexample.umeng.com.jindong20181107.joincart.presenter.JoinCartPresenter;
import soexample.umeng.com.jindong20181107.joincart.view.JoinCartView;
import soexample.umeng.com.jindong20181107.utils.StringUtils;

public class ShowActivity extends AppCompatActivity implements JoinCartView {
    private static final String TAG = "ShowActivity";
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.btn_cart)
    TextView btnCart;
    private Integer pid;
    private JoinCartPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String spid = intent.getStringExtra("spid");
        pid = Integer.valueOf(spid);
        Log.i(TAG, "spid=  " + pid);
        web.loadUrl(StringUtils.https2Http(url));
        web.setWebViewClient(new WebViewClient());
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        presenter=new JoinCartPresenter();
        presenter.attach(this);
    }

    @OnClick(R.id.btn_cart)
    public void onViewClicked() {
        SharedPreferences sp = getSharedPreferences("Nickname", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        Log.i(TAG, "uid=  " + uid);
        if (uid == 0) {
            Toast.makeText(ShowActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
        } else {
            presenter.getData(uid, pid);
        }
    }
    @Override
    public void succcessful(JoinCart cart) {
        Toast.makeText(ShowActivity.this,""+cart.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(ShowActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
