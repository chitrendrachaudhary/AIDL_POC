package com.example.kallaidlserver;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class AIDLColorService extends Service {

    private static final String TAG = "AIDLColorService";

    public AIDLColorService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " + intent+" binder "+binder);
        return binder;
    }

    private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {

        @Override
        public int getColor() throws RemoteException {
            Log.d(TAG, "getColor:  binder "+binder);
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Log.d(TAG, "getColor: " + color);
            return color;
        }
    };
}
