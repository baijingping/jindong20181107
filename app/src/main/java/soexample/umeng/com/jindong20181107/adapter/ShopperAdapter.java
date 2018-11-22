package soexample.umeng.com.jindong20181107.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.jindong20181107.R;
import soexample.umeng.com.jindong20181107.bane.ShoppingBean;

/**
 * Created by Shinelon on 2018/11/13.
 */

public class ShopperAdapter extends RecyclerView.Adapter<ShopperAdapter.ViewHolder> {
    // 一级列表（商家）发生变化的接口
    public interface OnShopperClickListener {
        void onShopperClick(int position, boolean isCheck);
    }

    private OnShopperClickListener shopperClickListener;

    public void setOnShopperClickListener(OnShopperClickListener listener) {
        this.shopperClickListener = listener;
    }

    // 二级列表的加减器监听
    private ProductterAdapter.OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(ProductterAdapter.OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    private Context context;
    private List<ShoppingBean.DataBean> list;

    public ShopperAdapter(Context context, List<ShoppingBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shopper, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShoppingBean.DataBean dataBean = list.get(position);
        holder.shopperName.setText(list.get(position).getSellerName());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        holder.rvProduct.setLayoutManager(layoutManager);
        final ProductterAdapter adapter=new ProductterAdapter(context,list.get(position).getList());
        if (productListener!=null){
            adapter.setOnAddDecreaseProductListener(productListener);
        }
        adapter.setOnProductClickListener(new ProductterAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                if (!isChecked){
                    dataBean.setChecked(false);
                    shopperClickListener.onShopperClick(position,false);
                }else {
                    boolean isAllProductSelected = true;
                    for (ShoppingBean.DataBean.ListBean listBean : dataBean.getList()) {
                        if (!listBean.isBisChecked()){
                            isAllProductSelected=false;
                            break;
                        }
                    }
                    dataBean.setChecked(isAllProductSelected);
                    shopperClickListener.onShopperClick(position,true);
                }
                notifyDataSetChanged();
                productListener.onChange(0,0);
            }
        });
        holder.rvProduct.setAdapter(adapter);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(list.get(position).isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                dataBean.setChecked(b);
                List<ShoppingBean.DataBean.ListBean> productList = dataBean.getList();
                for (ShoppingBean.DataBean.ListBean listBean : productList) {
                    listBean.setBisChecked(b);
                }
                adapter.notifyDataSetChanged();
                if (shopperClickListener!=null){
                    shopperClickListener.onShopperClick(position,b);
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView shopperName;
        private RecyclerView rvProduct;
        public ViewHolder(View itemView) {
            super(itemView);
            rvProduct=itemView.findViewById(R.id.rv_product);
            checkBox=itemView.findViewById(R.id.cb_shopper);
            shopperName=itemView.findViewById(R.id.txt_shopper_name);
        }
    }
}
