package soexample.umeng.com.jindong20181107.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Shinelon on 2018/11/7.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
