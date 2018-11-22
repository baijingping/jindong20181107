package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.GetAddrsBean;

/**
 * Created by Shinelon on 2018/11/20.
 */

public class GetAddrsAdapter extends RecyclerView.Adapter<GetAddrsAdapter.ViewHolder> {
    public interface OnGetAddrsClickListener {
        void onGetAddrsClick(int uid, int addrid,int status);
    }

    private OnGetAddrsClickListener GetAddrsClickListener;

    public void setOnGetAddrsClickListener(OnGetAddrsClickListener listener) {
        this.GetAddrsClickListener = listener;
    }
    private Context context;
    private List<GetAddrsBean.DataBean> list;

    public GetAddrsAdapter(Context context, List<GetAddrsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_getaddrs, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GetAddrsBean.DataBean dataBean = list.get(position);
        holder.gAddr.setText("addr: " + dataBean.getAddr());
        holder.gAddrid.setText("addrid: "+dataBean.getAddrid());
        holder.gMobile.setText("mobile: "+dataBean.getMobile());
        holder.gName.setText("name: "+dataBean.getName());
        holder.gStatus.setText("status "+dataBean.getStatus());
        holder.gUid.setText("uid "+dataBean.getUid());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int uid = dataBean.getUid();
                int addrid = dataBean.getAddrid();
                int status = dataBean.getStatus();
                GetAddrsClickListener.onGetAddrsClick(uid,addrid,status);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView gAddr;
        private TextView gAddrid;
        private TextView gMobile;
        private TextView gName;
        private TextView gStatus;
        private TextView gUid;
        public ViewHolder(View itemView) {
            super(itemView);
            gAddr=itemView.findViewById(R.id.g_addr);
            gAddrid=itemView.findViewById(R.id.g_addrud);
            gMobile=itemView.findViewById(R.id.g_mobile);
            gName=itemView.findViewById(R.id.g_name);
            gStatus=itemView.findViewById(R.id.g_status);
            gUid=itemView.findViewById(R.id.g_uid);

        }
    }
}
