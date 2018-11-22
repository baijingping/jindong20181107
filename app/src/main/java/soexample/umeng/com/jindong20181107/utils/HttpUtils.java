package soexample.umeng.com.jindong20181107.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shinelon on 2018/11/7.
 */

public class HttpUtils {
    private static final String BASE_URL="http://www.zhaoapi.cn/";
    private static volatile HttpUtils instance;
    private final Retrofit retrofit;

    private HttpUtils(){
        OkHttpClient client=new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static HttpUtils getInstance(){
        if (instance==null){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance=new HttpUtils();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> clazz){
        return retrofit.create(clazz);
    }

}
