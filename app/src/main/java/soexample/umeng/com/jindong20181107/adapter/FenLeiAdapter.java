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

/**
 * Created by Shinelon on 2018/11/8.
 */

public class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.FenleiBean> list;

    public FenLeiAdapter(Context context, List<HomeBean.DataBean.FenleiBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_fenlei, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageURI(list.get(position).getIcon());
        holder.txtTitle.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView txtTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            txtTitle=itemView.findViewById(R.id.txt_title);
        }
    }
}
