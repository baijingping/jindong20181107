package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.utils.StringUtils;

/**
 * Created by Shinelon on 2018/11/8.
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.ViewHolder> {
    public interface OnProductClickListener {
        void onProductClick(String url,int pid);
    }

    private OnProductClickListener listener;

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.listener = listener;
    }
    private Context context;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> list;

    public MiaoShaAdapter(Context context, List<HomeBean.DataBean.MiaoshaBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_miaosha, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.mImg.setImageURI(StringUtils.https2Http(split[0]));
        holder.mTitle.setText(list.get(position).getTitle());
        holder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = list.get(position).getDetailUrl();
                int pid = list.get(position).getPid();
                listener.onProductClick(url,pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mImg;
        private TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mImg=itemView.findViewById(R.id.m_img);
            mTitle=itemView.findViewById(R.id.m_title);
        }
    }
}
