package soexample.umeng.com.jindong20181107;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.widget.FlowView;

public class SerachActivity extends AppCompatActivity {

    @BindView(R.id.txt_search)
    TextView txtSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    private FlowView flowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        ButterKnife.bind(this);
        flowView = findViewById(R.id.fl_search);

    }

    @OnClick(R.id.txt_search)
    public void onViewClicked() {
        String text = etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(text)) {
            TextView txt = new TextView(this);
            txt.setText(text);
            txt.setPadding(10, 10, 10, 10);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txt.setLayoutParams(layoutParams);
            flowView.addView(txt);
            etSearch.setText("");
        }
        Intent intent=new Intent(SerachActivity.this,DatailsActivity.class);
        intent.putExtra("key",text);
        startActivity(intent);
    }
}
