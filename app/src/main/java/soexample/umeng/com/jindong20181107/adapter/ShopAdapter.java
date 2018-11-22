package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.ShopBean;

/**
 * Created by Shinelon on 2018/11/9.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>  {
    public interface OnShopClickListener {
        void onProductClick(int id);
    }
    private OnShopClickListener listener;

    public void setOnShopClickListener(OnShopClickListener listener) {
        this.listener = listener;
    }
    private Context context;
    private List<ShopBean.DataBean> list;
    public ShopAdapter(Context context, List<ShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shop, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.shopName.setText(list.get(position).getName());

        holder.shopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cid = list.get(position).getCid();
                listener.onProductClick(cid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shopName;
        public ViewHolder(View itemView) {
            super(itemView);
            shopName=itemView.findViewById(R.id.shop_name);
        }
    }
}
