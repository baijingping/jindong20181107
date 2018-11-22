package soexample.umeng.com.jindong20181107.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.SerachActivity;
import soexample.umeng.com.jindong20181107.ShowActivity;
import soexample.umeng.com.jindong20181107.adapter.BannerAdapter;
import soexample.umeng.com.jindong20181107.adapter.FenLeiAdapter;
import soexample.umeng.com.jindong20181107.adapter.MiaoShaAdapter;
import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.base.BaseFragment;
import soexample.umeng.com.jindong20181107.home.presenter.HomePresenter;
import soexample.umeng.com.jindong20181107.home.view.HomeView;

/**
 * Created by Shinelon on 2018/11/7.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.banner)
    ViewPager banner;
    Unbinder unbinder;
    @BindView(R.id.type)
    RecyclerView type;
    @BindView(R.id.miaosha)
    RecyclerView miaosha;
    @BindView(R.id.home_search)
    EditText homeSearch;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.tv_miaosha)
    TextView tvMiaosha;
    @BindView(R.id.tv_miaosha_time)
    TextView tvMiaoshaTime;
    @BindView(R.id.tv_miaosha_shi)
    TextView tvMiaoshaShi;
    @BindView(R.id.tv_miaosha_minter)
    TextView tvMiaoshaMinter;
    @BindView(R.id.tv_miaosha_second)
    TextView tvMiaoshaSecond;
    private List<HomeBean.DataBean.BannerBean> bannerList;
    private List<HomeBean.DataBean.FenleiBean> fenleiList;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshaList;
    private BannerAdapter bannerAdapter;
    private FenLeiAdapter fenLeiAdapter;
    private MiaoShaAdapter miaoshaAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 123) {
                int item = banner.getCurrentItem();
                if (item < bannerList.size() - 1) {
                    item++;
                } else {
                    item = 0;
                }
                banner.setCurrentItem(item);
                handler.sendEmptyMessageDelayed(123, 1000);
            }
        }
    };
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onTime();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    @Override
    protected void initData() {
        presenter.getData();
        bannerList = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity(), bannerList);
        banner.setAdapter(bannerAdapter);

        fenleiList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        type.setLayoutManager(layoutManager);
        fenLeiAdapter = new FenLeiAdapter(getActivity(), fenleiList);
        type.setAdapter(fenLeiAdapter);

        miaoshaList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        miaosha.setLayoutManager(layoutManager1);
        miaoshaAdapter = new MiaoShaAdapter(getActivity(), miaoshaList);
        miaoshaAdapter.setOnProductClickListener(new MiaoShaAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(String url, int pid) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                Log.i(TAG, "pid=   " + pid);
                String spid = String.valueOf(pid);
                intent.putExtra("url", url);
                intent.putExtra("spid", spid);
                Toast.makeText(getActivity(), "" + url, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        miaosha.setAdapter(miaoshaAdapter);

        handler.sendEmptyMessageDelayed(123, 1000);
        onDoku();
        handler1.sendEmptyMessage(0);
        onTime();
    }

    private void onTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            tvMiaoshaTime.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            tvMiaoshaTime.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            tvMiaoshaShi.setText("0" + hours + "");
            if (minute >= 10) {
                tvMiaoshaMinter.setText(minute + "");
            } else {
                tvMiaoshaMinter.setText("0" + minute + "");
            }
            if (second >= 10) {
                tvMiaoshaSecond.setText(second + "");
            } else {
                tvMiaoshaSecond.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void onDoku() {
        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("大家有没有在 听课");
        info.add("是不是还有人在睡觉");
        info.add("我真帅");
        info.add("赶紧的好好学习吧 马上毕业了");
        info.add("你没有事件睡觉了");
        marqueeView.startWithList(info);
    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.home_item;
    }

    @Override
    public void getBanner(List<HomeBean.DataBean.BannerBean> bannerBean) {
        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
        if (bannerBean != null) {
            bannerList.clear();
            bannerList.addAll(bannerBean);
            bannerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getFenlei(List<HomeBean.DataBean.FenleiBean> fenleiBean) {
        if (fenleiBean != null) {
            fenleiList.clear();
            fenleiList.addAll(fenleiBean);
            fenLeiAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getMiaoshas(HomeBean.DataBean.MiaoshaBean miaoshaBean) {
        if (miaoshaBean != null) {
            List<HomeBean.DataBean.MiaoshaBean.ListBean> list = miaoshaBean.getList();
            if (list != null) {
                miaoshaList.clear();
                miaoshaList.addAll(list);
                miaoshaAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler1.removeCallbacksAndMessages(null);
    }

    @OnClick(R.id.home_search)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SerachActivity.class);
        startActivity(intent);
    }
}
