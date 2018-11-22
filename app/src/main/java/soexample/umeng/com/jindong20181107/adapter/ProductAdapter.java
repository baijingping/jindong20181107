package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
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
import soexample.umeng.com.jindong20181107.bane.ShopBean;

/**
 * Created by Shinelon on 2018/11/9.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {
    private Context context;
    private List<ProductBean.DataBean> list;
    public ProductAdapter(Context context, List<ProductBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_product, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductBean.DataBean dataBean = list.get(position);
        //holder.productImg.setImageURI(list.get(position).getList().get(position).getIcon());
        holder.productName.setText(list.get(position).getName());
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false);
        holder.productRecy.setLayoutManager(layoutManager);
        RightListAdapter adapter=new RightListAdapter(context,dataBean.getList());
        holder.productRecy.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        //private SimpleDraweeView productImg;
        private RecyclerView productRecy;
        public ViewHolder(View itemView) {
            super(itemView);
            productRecy=itemView.findViewById(R.id.product_recy);
            //productImg=itemView.findViewById(R.id.product_img);
            productName=itemView.findViewById(R.id.product_name);
        }
    }
}
