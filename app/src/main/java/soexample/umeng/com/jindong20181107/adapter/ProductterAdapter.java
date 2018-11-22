package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.utils.StringUtils;
import soexample.umeng.com.jindong20181107.widget.AddDecreaseView;

/**
 * Created by Shinelon on 2018/11/13.
 */

public class ProductterAdapter extends RecyclerView.Adapter<ProductterAdapter.ViewHolder> {
    public interface OnProductClickListener {
        void onProductClick(int position, boolean isChecked);
    }

    private OnProductClickListener productClickListener;

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.productClickListener = listener;
    }

    // 加减器发生变化的监听
    public interface OnAddDecreaseProductListener {
        void onChange(int position, int num);
    }

    private OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    private Context context;
    private List<ShoppingBean.DataBean.ListBean> list;

    public ProductterAdapter(Context context, List<ShoppingBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.productter_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShoppingBean.DataBean.ListBean listBean = list.get(position);
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        holder.productImg.setImageURI(StringUtils.https2Http(split[0]));
        holder.productTitle.setText(listBean.getTitle());
        holder.productPricr.setText(String.valueOf(listBean.getPrice()));
        holder.productAdd.setNum(listBean.getNum());
        holder.productAdd.setOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {
                listBean.setNum(num);
                if (productListener!=null){
                    productListener.onChange(position,num);
                }
            }

            @Override
            public void decrease(int num) {
                listBean.setNum(num);
                if (productListener!=null){
                    productListener.onChange(position,num);
                }
            }
        });
        holder.productCheck.setOnCheckedChangeListener(null);
        holder.productCheck.setChecked(listBean.isBisChecked());
        holder.productCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listBean.setBisChecked(b);
                if (productClickListener!=null){
                    productClickListener.onProductClick(position,b);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox productCheck;
        private SimpleDraweeView productImg;
        private TextView productTitle;
        private TextView productPricr;
        private AddDecreaseView productAdd;
        public ViewHolder(View itemView) {
            super(itemView);
            productCheck=itemView.findViewById(R.id.cb_product);
            productImg=itemView.findViewById(R.id.img_product);
            productTitle=itemView.findViewById(R.id.txt_product_title);
            productPricr=itemView.findViewById(R.id.txt_single_price);
            productAdd=itemView.findViewById(R.id.adv_product);
        }
    }
}
