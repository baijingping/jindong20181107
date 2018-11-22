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
 * Created by Shinelon on 2018/11/9.
 */

public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.ViewHolder> {
    public interface OnTuiJianClickListener {
        void onTuiJianClick(String url,int pid);
    }

    private OnTuiJianClickListener listener;

    public void setOnTuiJianClickListener(OnTuiJianClickListener listener) {
        this.listener = listener;
    }
    private Context context;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list;

    public TuiJianAdapter(Context context, List<HomeBean.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_tuijian, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        HomeBean.DataBean.TuijianBean.ListBeanX listBeanX = list.get(position);
        String images = listBeanX.getImages();
        String[] split = images.split("\\|");
        holder.tuiJIanImg.setImageURI(StringUtils.https2Http(split[0]));
        holder.tuiJianName.setText(listBeanX.getTitle());
        holder.tuiJIanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = list.get(position).getDetailUrl();
                int pid = list.get(position).getPid();
                listener.onTuiJianClick(url,pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView tuiJIanImg;
        private TextView tuiJianName;
        public ViewHolder(View itemView) {
            super(itemView);
            tuiJIanImg=itemView.findViewById(R.id.tuijan_img);
            tuiJianName=itemView.findViewById(R.id.tuijian_name);
        }
    }
}
