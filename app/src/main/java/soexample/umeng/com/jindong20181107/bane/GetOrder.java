package soexample.umeng.com.jindong20181107.bane;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Shinelon on 2018/11/19.
 */

public class GetOrder {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2018-11-15T11:44:10","orderid":13832,"price":99.99,"status":33,"title":"订单测试标题21227","uid":21227},{"createtime":"2018-11-15T11:45:11","orderid":13833,"price":99.99,"status":0,"title":"订单测试标题21227","uid":21227},{"createtime":"2018-11-19T14:01:44","orderid":13886,"price":99.99,"status":0,"title":"订单测试标题21227","uid":21227},{"createtime":"2018-11-19T14:01:58","orderid":13887,"price":99999,"status":0,"title":"订单测试标题21227","uid":21227}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public static GetOrder objectFromData(String str) {

        return new Gson().fromJson(str, GetOrder.class);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-11-15T11:44:10
         * orderid : 13832
         * price : 99.99
         * status : 33
         * title : 订单测试标题21227
         * uid : 21227
         */

        private String createtime;
        private int orderid;
        private float price;
        private int status;
        private String title;
        private int uid;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
