package soexample.umeng.com.jindong20181107;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.jindong20181107.fragment.ClassificationFragment;
import soexample.umeng.com.jindong20181107.fragment.FoundFragment;
import soexample.umeng.com.jindong20181107.fragment.HomeFragment;
import soexample.umeng.com.jindong20181107.fragment.MyFragment;
import soexample.umeng.com.jindong20181107.fragment.ShoppingFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_home)
    ImageView btnHome;
    @BindView(R.id.btn_classification)
    ImageView btnClassification;
    @BindView(R.id.btn_shop)
    ImageView btnShop;
    @BindView(R.id.btn_found)
    ImageView btnFound;
    @BindView(R.id.btn_my)
    ImageView btnMy;
    private HomeFragment homeFragment;
    private ClassificationFragment classificationFragment;
    private FoundFragment foundFragment;
    private ShoppingFragment shoppingFragment;
    private MyFragment myFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        foundFragment = new FoundFragment();
        shoppingFragment = new ShoppingFragment();
        myFragment = new MyFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.pager, homeFragment).commit();
    }

    @OnClick({R.id.btn_home, R.id.btn_classification, R.id.btn_shop, R.id.btn_found, R.id.btn_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                fragmentManager.beginTransaction().replace(R.id.pager, homeFragment).commit();
                changeBackGround(0);
                break;
            case R.id.btn_classification:
                fragmentManager.beginTransaction().replace(R.id.pager, classificationFragment).commit();
                changeBackGround(1);
                break;
            case R.id.btn_shop:
                fragmentManager.beginTransaction().replace(R.id.pager, foundFragment).commit();
                changeBackGround(2);
                break;
            case R.id.btn_found:
                fragmentManager.beginTransaction().replace(R.id.pager, shoppingFragment).commit();
                changeBackGround(3);
                break;
            case R.id.btn_my:
                fragmentManager.beginTransaction().replace(R.id.pager, myFragment).commit();
                changeBackGround(4);
                break;
        }
    }

    public void changeBackGround(int dex) {
        switch (dex) {
            case 0:
                btnHome.setImageResource(R.drawable.ac1);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 1:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abx);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 2:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.abz);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 3:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abv);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 4:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac3);
                break;
        }
    }
}
