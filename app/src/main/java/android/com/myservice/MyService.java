package android.com.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dell on 2017/12/25.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("xuzhenyue","onBind()");
        return myServiceBinder;
    }
    public final Binder myServiceBinder = new MyServiceBinder();
    public class MyServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("xuzhenyue","onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    public int getCountNum(){
        return i;
    }
    @Override
    public void onCreate() {
        Log.e("xuzhenyue","onCreate()");
        startTimer();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e("xuzhenyue","OnDestory()");
        stopTimer();
        super.onDestroy();
    }
    private int i = 0;
    public void startTimer(){
        if(timer == null){
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    i++;
                    Log.d("xuzhenyue"," i =" + i);
                }
            };
            timer.schedule(task,1000,1000);
        }
    }
    public void stopTimer(){
        if(timer!=null){
            task.cancel();
            timer.cancel();
            task = null;
            timer = null;
        }
    }
    private Timer timer = null;
    private TimerTask task = null;
}
