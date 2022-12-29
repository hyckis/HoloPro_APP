package com.holopro.gesture;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import java.p004io.IOException;
import java.p004io.InputStream;

public class GestureThread extends Thread {
    private final int MESSAGE_HOLO = 1;
    private final String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    /* access modifiers changed from: private */
    public int angleNum = 0;
    private int bytes = 0;
    private Context context;
    /* access modifiers changed from: private */

    /* renamed from: gi */
    public GestureInterface f33gi;
    private byte[] mmBuffer = new byte[1024];
    private final InputStream mmInStream;
    private final BluetoothSocket mmSocket;
    /* access modifiers changed from: private */
    public float playLen = 0.0f;
    private final Handler transHandler = getTransHandler();

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|5|6|7|(1:9)|12|13|14|(2:17|20)(2:18|19)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.mmSocket.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x005f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GestureThread(android.content.Context r4, java.lang.String r5, com.holopro.gesture.GestureInterface r6) {
        /*
            r3 = this;
            r3.<init>()
            java.lang.String r0 = "00001101-0000-1000-8000-00805F9B34FB"
            r3.MY_UUID = r0
            r1 = 1
            r3.MESSAGE_HOLO = r1
            android.os.Handler r1 = r3.getTransHandler()
            r3.transHandler = r1
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]
            r3.mmBuffer = r1
            r1 = 0
            r3.bytes = r1
            r3.angleNum = r1
            r2 = 0
            r3.playLen = r2
            r3.context = r4
            r3.f33gi = r6
            android.bluetooth.BluetoothAdapter r6 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            android.bluetooth.BluetoothDevice r5 = r6.getRemoteDevice(r5)
            r6 = 0
            java.util.UUID r0 = java.util.UUID.fromString(r0)     // Catch:{ IOException -> 0x0034 }
            android.bluetooth.BluetoothSocket r5 = r5.createRfcommSocketToServiceRecord(r0)     // Catch:{ IOException -> 0x0034 }
            goto L_0x0042
        L_0x0034:
            android.content.Context r5 = r4.getApplicationContext()
            java.lang.String r0 = "Socket has not been connected"
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r0, r1)
            r5.show()
            r5 = r6
        L_0x0042:
            r3.mmSocket = r5
            android.bluetooth.BluetoothSocket r5 = r3.mmSocket     // Catch:{ IOException -> 0x005f }
            r5.connect()     // Catch:{ IOException -> 0x005f }
            android.bluetooth.BluetoothSocket r5 = r3.mmSocket     // Catch:{ IOException -> 0x005f }
            boolean r5 = r5.isConnected()     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0064
            android.content.Context r5 = r4.getApplicationContext()     // Catch:{ IOException -> 0x005f }
            java.lang.String r0 = "Socket connected successfully!"
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r0, r1)     // Catch:{ IOException -> 0x005f }
            r5.show()     // Catch:{ IOException -> 0x005f }
            goto L_0x0064
        L_0x005f:
            android.bluetooth.BluetoothSocket r5 = r3.mmSocket     // Catch:{ IOException -> 0x0064 }
            r5.close()     // Catch:{ IOException -> 0x0064 }
        L_0x0064:
            android.bluetooth.BluetoothSocket r5 = r3.mmSocket     // Catch:{ IOException -> 0x006a }
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ IOException -> 0x006a }
        L_0x006a:
            r3.mmInStream = r6
            java.io.InputStream r5 = r3.mmInStream
            if (r5 == 0) goto L_0x0078
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r5 = "stream ok"
            r4.println((java.lang.String) r5)
            goto L_0x008c
        L_0x0078:
            android.content.Context r4 = r4.getApplicationContext()
            java.lang.String r5 = "mmInStream is null"
            android.widget.Toast r4 = android.widget.Toast.makeText(r4, r5, r1)
            r4.show()
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r5 = "stream no"
            r4.println((java.lang.String) r5)
        L_0x008c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.holopro.gesture.GestureThread.<init>(android.content.Context, java.lang.String, com.holopro.gesture.GestureInterface):void");
    }

    public void run() {
        while (true) {
            try {
                this.bytes = this.mmInStream.read(this.mmBuffer);
                String str = new String(this.mmBuffer);
                this.mmBuffer = new byte[1024];
                if (str.equals("00_00_")) {
                    this.mmInStream.close();
                    this.mmSocket.close();
                }
                this.transHandler.obtainMessage(1, str).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void cancel() {
        try {
            this.mmInStream.close();
            this.mmSocket.close();
            Toast.makeText(this.context.getApplicationContext(), "Bluetooth socket closed", 0).show();
        } catch (IOException unused) {
            Toast.makeText(this.context.getApplicationContext(), "Close of connect socket failed", 0).show();
        }
    }

    private Handler getTransHandler() {
        return new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    String[] split = ((String) message.obj).split("_");
                    String str = split[0];
                    String str2 = split[1];
                    int parseInt = Integer.parseInt(str);
                    float parseFloat = Float.parseFloat(str2);
                    if (parseInt - GestureThread.this.angleNum != 0 || parseFloat - GestureThread.this.playLen != 0.0f) {
                        int unused = GestureThread.this.angleNum = parseInt;
                        float unused2 = GestureThread.this.playLen = parseFloat;
                        System.out.println(GestureThread.this.angleNum);
                        System.out.println(GestureThread.this.playLen);
                        GestureThread.this.f33gi.gestureMonitor(GestureThread.this.angleNum, GestureThread.this.playLen);
                    }
                }
            }
        };
    }
}
