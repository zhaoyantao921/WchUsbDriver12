package com.wch.wchusbdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class ConnectionChangeReceiver extends BroadcastReceiver {


    @Override

    public void onReceive(Context context, Intent intent) {
        boolean isConnect =false;
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //WIFI
        NetworkInfo.State state=manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(NetworkInfo.State.CONNECTED==state){
            isConnect=true;
        }
        //GPRS
        state=manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if(NetworkInfo.State.CONNECTED==state){
            isConnect=true;
        }
        if(!isConnect){
            Toast.makeText(context,R.string.neterror,Toast.LENGTH_LONG).show();
        }
    }
}
