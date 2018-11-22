package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.GetOrder;

/**
 * Created by Shinelon on 2018/11/19.
 */

public class GetOrderAdapter extends RecyclerView.Adapter<GetOrderAdapter.ViewHolder>{
    private Context context;
    private List<GetOrder.DataBean> list;

    public GetOrderAdapter(Context context, List<GetOrder.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_getorder, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetOrder.DataBean dataBean = list.get(position);
        holder.creatTime.setText(dataBean.getCreatetime());
        holder.orderId.setText(dataBean.getOrderid()+"");
        holder.price.setText(dataBean.getPrice()+"");
        holder.status.setText(dataBean.getStatus()+"");
        holder.orderTitle.setText(dataBean.getTitle());
        holder.orderUid.setText(dataBean.getUid()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView creatTime;
        private TextView orderId;
        private TextView price;
        private TextView status;
        private TextView orderTitle;
        private TextView orderUid;
        public ViewHolder(View itemView) {
            super(itemView);
            creatTime=itemView.findViewById(R.id.createtime);
            orderId=itemView.findViewById(R.id.orderid);
            price=itemView.findViewById(R.id.price);
            status=itemView.findViewById(R.id.status);
            orderTitle=itemView.findViewById(R.id.order_title);
            orderUid=itemView.findViewById(R.id.order_uid);
        }
    }
}
