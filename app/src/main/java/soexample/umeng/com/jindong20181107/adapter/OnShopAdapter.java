package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import soexample.umeng.com.jindong20181107.bane.ShoppingBean;

/**
 * Created by Shinelon on 2018/11/12.
 */

public class OnShopAdapter extends RecyclerView.Adapter<OnShopAdapter.ViewHolder>{
    private Context context;
    private List<ShoppingBean.DataBean> list;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
