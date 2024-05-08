package com.example.myaidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kallaidlserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    IMyAidlInterface iMyAidlService;
    private static final String TAG ="MainActivity";
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlService = IMyAidlInterface.Stub.asInterface(iBinder);
            Log.d(TAG, "Remote config Service Connected!!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("AIDLColorService");
        intent.setPackage("com.example.kallaidlserver");
        bindService(intent, mConnection, BIND_AUTO_CREATE);

        //create an onClick
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int color = 0;
                try {
                    System.out.println("2nd here iMyAidlService "+iMyAidlService);
                    color = iMyAidlService.getColor();
                    view.setBackgroundColor(color);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }


}