package soexample.umeng.com.jindong20181107.bane;

import com.google.gson.Gson;

/**
 * Created by Shinelon on 2018/11/19.
 */

public class SetAddrBean {

    /**
     * msg : 更新成功
     * code : 0
     */

    private String msg;
    private String code;

    public static SetAddrBean objectFromData(String str) {

        return new Gson().fromJson(str, SetAddrBean.class);
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
}
