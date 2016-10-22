package scut.tranplant_push_demo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by yany on 2016/10/22.
 */
public class PushApplication extends Application {
    // user your appid the key.
    private static final String APP_ID = "2882303761517519386";
    // user your appid the key.
    private static final String APP_KEY = "5461751958386";
    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "scut.tranplant_push_demo";

    @Override
    public void onCreate() {
        super.onCreate();

        // 注册push服务，注册成功后会向DemoMessageReceiver发送广播
        // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }

    }

    //检测需不需要初始化小米推送服务
    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
