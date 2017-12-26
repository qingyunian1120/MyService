package android.com.myservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener, ServiceConnection {
    Button btnStartService;
    Button btnStopService, bindService,unbindService,getCountNum;
    private Intent serviceIntent;
    private MyService myService = null;
    private boolean isOnbind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent(this,MyService.class);
        btnStartService = findViewById(R.id.btnSartService);
        btnStopService = findViewById(R.id.btnStopService);
        bindService = findViewById(R.id.bindService);
        unbindService = findViewById(R.id.unBindService);
        getCountNum = findViewById(R.id.getCountNum);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        getCountNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSartService:
                startService(serviceIntent);
                break;
            case R.id.btnStopService:
                stopService(serviceIntent);
                break;
            case R.id.bindService:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unBindService:
                try {
                    if(isOnbind) {
                        unbindService(this);
                        isOnbind = false;
                    }
                } catch (Exception e){
                    Log.e("xuzhenyue" ,"e = " + e);
                }
                    myService = null;
                break;
            case R.id.getCountNum:
                if(myService!=null){
                    Log.e("xuzhenyue","num = " +myService.getCountNum());
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("xuzhenyue","onServiceConnected");
            isOnbind = true;
            myService = ((MyService.MyServiceBinder) iBinder).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.e("xuzhenyue","onServiceDisconnected");
    }
}
