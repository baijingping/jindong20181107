package soexample.umeng.com.jindong20181107.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import soexample.umeng.com.jindong20181107.bane.AddAddrBean;
import soexample.umeng.com.jindong20181107.bane.CreatOrder;
import soexample.umeng.com.jindong20181107.bane.DetailsBean;
import soexample.umeng.com.jindong20181107.bane.GetAddrsBean;
import soexample.umeng.com.jindong20181107.bane.GetDefaultAddrBean;
import soexample.umeng.com.jindong20181107.bane.GetOrder;
import soexample.umeng.com.jindong20181107.bane.HomeBean;
import soexample.umeng.com.jindong20181107.bane.JoinCart;
import soexample.umeng.com.jindong20181107.bane.LoginBane;
import soexample.umeng.com.jindong20181107.bane.ProductBean;
import soexample.umeng.com.jindong20181107.bane.Regist;
import soexample.umeng.com.jindong20181107.bane.SetAddrBean;
import soexample.umeng.com.jindong20181107.bane.ShopBean;
import soexample.umeng.com.jindong20181107.bane.ShoppingBean;
import soexample.umeng.com.jindong20181107.bane.UserBean;

/**
 * Created by Shinelon on 2018/11/8.
 */

public interface Api {
    //登录
    @GET("user/login")
    Observable<LoginBane> login(@Query("mobile") String mobile,@Query("password") String password);

    // 注册
    @GET("user/reg")
    Observable<Regist> getRegisterData(@Query("mobile") String mobile, @Query("password") String password);

    //个人信息
    @GET("user/getUserInfo")
    Observable<UserBean> user(@Query("uid") int uid,@Query("token") String token);
    //首页
    @GET("home/getHome ")
    Observable<HomeBean> home();
    //分类
    @GET("product/getCatagory")
    Observable<ShopBean> shop();

    @GET("product/getProductCatagory")
    Observable<ProductBean> product(@Query("cid") int cid);

    //加入购物车
    @GET("product/addCart")
    Observable<JoinCart> cart(@Query("uid") int uid,@Query("pid") int pid);

    //购物车
    @GET("product/getCarts")
    Observable<ShoppingBean> shopping(@Query("uid") int uid);

    //搜索
    @GET("product/searchProducts")
    Observable<DetailsBean> details(@Query("keywords") String keywords,@Query("page") int page);

    //创建订单
    @GET("product/createOrder")
    Observable<CreatOrder> creatOrder(@Query("uid") int uid,@Query("price") String price);

    //订单列表
    @GET("product/getOrders")
    Observable<GetOrder> getOrder(@Query("uid") int uid,@Query("page") int page);

    //收货地址列表
    @GET("user/getAddrs")
    Observable<GetAddrsBean> getAddrs(@Query("uid") int uid);

    //添加收货地址
    @GET("user/addAddr")
    Observable<AddAddrBean> addAddrs(@Query("uid") int uid,@Query("addr") String addr,@Query("mobile") String mobile,@Query("name") String name);

    //设置默认地址
    @GET("user/setAddr")
    Observable<SetAddrBean> setAddrs(@Query("uid") int uid,@Query("addrid") int addrid,@Query("status") int status);

    //获取默认地址
    @GET("user/getDefaultAddr")
    Observable<GetDefaultAddrBean> getDefaultAddr(@Query("uid") int uid);
}
