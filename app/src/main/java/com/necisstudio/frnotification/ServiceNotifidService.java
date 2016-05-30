package com.necisstudio.frnotification;

import android.content.ComponentCallbacks;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Jarcode on 2016-05-30.
 */

public class ServiceNotifidService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("notif", "Refreshed token: " + refreshedToken);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        Log.e("service", "Refreshed token: " + callback.toString());
    }

}
