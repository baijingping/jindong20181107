package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.ProductBean;

/**
 * Created by Shinelon on 2018/11/12.
 */

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.ViewHolder> {
    public interface OnRightClickLister{
        void onRightClick(int pid);
    }
    private OnRightClickLister listener;
    public void setOnRightClickLister(OnRightClickLister listener){
        this.listener=listener;
    }
    private Context context;
    private List<ProductBean.DataBean.ListBean> list;
    public RightListAdapter(Context context, List<ProductBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rightlist_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.rightImg.setImageURI(list.get(position).getIcon());
        holder.rightListShopName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rightListShopName;
        private SimpleDraweeView rightImg;
        public ViewHolder(View itemView) {
            super(itemView);
            rightImg=itemView.findViewById(R.id.right_img);
            rightListShopName=itemView.findViewById(R.id.right_list_shopname);
        }
    }
}
