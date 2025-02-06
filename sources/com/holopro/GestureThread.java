package com.example.ftptest;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/***************  The thread to do data transfer  *****************/

public class GestureThread {

    private final String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private final int MESSAGE_HOLO = 1;
    private final Handler transHandler = getTransHandler();

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private byte[] mmBuffer = new byte[1024];
    private int bytes = 0;

    private int angleNum;
    private float playLen;

    private Context context;
    private GestureInterface gi;

    public GestureThread(Context context, BluetoothDevice device, GestureInterface gi) {

        BluetoothSocket tmp = null;
        this.context = context;
        this.gi = gi;

        if (device != null)
            System.out.println(device.getAddress());

        // get socket
        try {
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
        } catch (IOException E) {
            Toast.makeText(context.getApplicationContext(), "Socket has not been connected", Toast.LENGTH_SHORT).show();
        }
        mmSocket = tmp;
        if (mmSocket.isConnected())
            System.out.println("socket ok");
        else System.out.println("socket not ok");

        // get inputStream
        InputStream tmpIn = null;
        try {
            tmpIn = mmSocket.getInputStream();
        } catch (IOException e) {}

        mmInStream = tmpIn;
        if (mmInStream != null) {
            Toast.makeText(context.getApplicationContext(), "STREAM ESTABLISHED", Toast.LENGTH_SHORT).show();
            System.out.println("stream ok");
        } else {
            Toast.makeText(context.getApplicationContext(), "mmInStream is null", Toast.LENGTH_SHORT).show();
            System.out.println("stream no");
        }
    }

    public void run() {
        try {
            while (true) {
                bytes = mmInStream.read(mmBuffer);
                String dataStr = new String(mmBuffer);
                mmBuffer = new byte[1024];
                /*** Received data: data_first(int), data_second(float) ***/
                if(dataStr.equals("00_00_")){
                    mmInStream.close();
                    mmSocket.close();
                }
                transHandler.obtainMessage(MESSAGE_HOLO, dataStr).sendToTarget();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            mmInStream.close();
            mmSocket.close();
        } catch (IOException e) {
            Toast.makeText(context.getApplicationContext(), "Close of connect socket failed", Toast.LENGTH_SHORT).show();
        }
    }

    private Handler getTransHandler() {
        return new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case MESSAGE_HOLO:
                        String dataStr = (String) msg.obj;
                        String[] separate = dataStr.split("_");
                        String first = separate[0];
                        String sec = separate[1];
                        int num = Integer.parseInt(first);
                        float len = Float.parseFloat(sec);
                        if (num-angleNum!=0 || len-playLen!=0f) {
                            angleNum = num;
                            playLen = len;
                            gi.gestureMonitor(angleNum, playLen);
                        }
                }
            }
        };
    }

}
