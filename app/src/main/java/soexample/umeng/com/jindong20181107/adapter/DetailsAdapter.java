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
import soexample.umeng.com.jindong20181107.bane.DetailsBean;
import soexample.umeng.com.jindong20181107.utils.StringUtils;

/**
 * Created by Shinelon on 2018/11/14.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    public interface OnDetailsClickListener {
        void onDetailsClick(String url,int pid);
    }

    private OnDetailsClickListener listener;

    public void setOnDetailsClickListener(OnDetailsClickListener listener) {
        this.listener = listener;
    }
    private Context context;
    private List<DetailsBean.DataBean> list;

    public DetailsAdapter(Context context, List<DetailsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_details, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        DetailsBean.DataBean dataBean = list.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        holder.detailsImg.setImageURI(StringUtils.https2Http(split[0]));
        holder.detailsTitle.setText(dataBean.getTitle());
        holder.detailsTime.setText(dataBean.getCreatetime());
        holder.detailsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = list.get(position).getDetailUrl();
                int pid = list.get(position).getPid();
                listener.onDetailsClick(url,pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView detailsImg;
        private TextView detailsTitle;
        private TextView detailsTime;
        public ViewHolder(View itemView) {
            super(itemView);
            detailsImg=itemView.findViewById(R.id.details_img);
            detailsTitle=itemView.findViewById(R.id.details_title);
            detailsTime=itemView.findViewById(R.id.details_time);
        }
    }
}
