package com.holopro;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.holopro.R;
import com.holopro.ref.FTPManager;
import org.apache.commons.net.ftp.FTPFile;

public class MainActivity extends AppCompatActivity {
    private final int GET_WRITE_SETTING_PER = 2;
    private final int UPLOAD = 1;
    private ImageButton cancelButton;
    /* access modifiers changed from: private */
    public TextView directions;
    private ImageButton displayButton;
    private ImageButton downloadButton;
    /* access modifiers changed from: private */
    public String[] fileNames;
    /* access modifiers changed from: private */
    public FTPFile[] ftpFiles;
    private ImageButton generateButton;
    /* access modifiers changed from: private */
    public Handler handler;
    private String isPress;
    /* access modifiers changed from: private */
    public FTPManager manager;
    private ImageButton startButton;
    /* access modifiers changed from: private */
    public TextView title;
    private Uri uri;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        this.title = (TextView) findViewById(R.id.title_dir);
        this.directions = (TextView) findViewById(R.id.directions);
        this.generateButton = (ImageButton) findViewById(R.id.generateButton);
        this.downloadButton = (ImageButton) findViewById(R.id.downloadButton);
        this.displayButton = (ImageButton) findViewById(R.id.displayButton);
        this.startButton = (ImageButton) findViewById(R.id.startButton);
        this.cancelButton = (ImageButton) findViewById(R.id.cancelButton);
        resetUI();
        this.manager = new FTPManager();
        this.handler = getHandler();
        if (!Settings.System.canWrite(this)) {
            startActivity(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS"));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_holopro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_bluetooth /*2131165281*/:
                new AlertDialog.Builder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.intro_bluetooth).setPositiveButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) null).setNegativeButton((CharSequence) "Set Bluetooth", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.startActivity(new Intent(MainActivity.this.getBaseContext(), BluetoothActivity.class));
                    }
                }).show();
                return true;
            case R.id.menu_introductions /*2131165282*/:
                new AlertDialog.Builder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.intro_all).setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) null).show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        setAlertDialog();
        return true;
    }

    private void setAlertDialog() {
        new AlertDialog.Builder(this).setTitle((int) R.string.app_name).setMessage((CharSequence) "Are you sure you want to leave?").setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) null).setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
                Process.killProcess(Process.myPid());
            }
        }).show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            this.uri = intent.getData();
            Intent intent2 = new Intent(getBaseContext(), RenameUploadActivity.class);
            intent2.putExtra("fileUri", this.uri.toString());
            startActivity(intent2);
        }
        if (i != 2) {
            return;
        }
        if (Settings.System.canWrite(this)) {
            System.out.println("OK");
        } else {
            System.out.println("false");
        }
    }

    public void generateOnClick(View view) {
        this.isPress = "generate";
        this.generateButton.setImageResource(R.drawable.generate_2);
        this.downloadButton.setImageResource(R.drawable.download_1);
        this.displayButton.setImageResource(R.drawable.display_1);
        this.title.setText(R.string.title_generate);
        this.directions.setText(R.string.intro_generate);
        this.startButton.setVisibility(0);
        this.cancelButton.setVisibility(0);
    }

    public void downloadOnClick(View view) {
        this.isPress = "download";
        this.generateButton.setImageResource(R.drawable.generate_1);
        this.downloadButton.setImageResource(R.drawable.download_2);
        this.displayButton.setImageResource(R.drawable.display_1);
        this.title.setText(R.string.title_download);
        this.directions.setText(R.string.intro_download);
        this.startButton.setVisibility(0);
        this.cancelButton.setVisibility(0);
    }

    public void displayOnClick(View view) {
        this.isPress = "display";
        this.generateButton.setImageResource(R.drawable.generate_1);
        this.downloadButton.setImageResource(R.drawable.download_1);
        this.displayButton.setImageResource(R.drawable.display_2);
        this.title.setText(R.string.title_display);
        this.directions.setText(R.string.intro_display);
        this.startButton.setVisibility(0);
        this.cancelButton.setVisibility(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        if (r7.equals("generate") == false) goto L_0x0063;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startOnClick(android.view.View r7) {
        /*
            r6 = this;
            java.lang.String r7 = "android.permission.READ_EXTERNAL_STORAGE"
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            java.lang.String[] r7 = new java.lang.String[]{r7, r0}
            int r0 = r7.length
            r1 = 0
            r2 = r1
        L_0x000b:
            if (r2 >= r0) goto L_0x001d
            r3 = r7[r2]
            int r3 = r6.checkSelfPermission(r3)
            if (r3 == 0) goto L_0x001a
            r3 = 101(0x65, float:1.42E-43)
            r6.requestPermissions(r7, r3)
        L_0x001a:
            int r2 = r2 + 1
            goto L_0x000b
        L_0x001d:
            r0 = r7[r1]
            int r0 = r6.checkSelfPermission(r0)
            if (r0 != 0) goto L_0x0095
            r0 = 1
            r7 = r7[r0]
            int r7 = r6.checkSelfPermission(r7)
            if (r7 != 0) goto L_0x0095
            java.lang.String r7 = r6.isPress
            r2 = -1
            int r3 = r7.hashCode()
            r4 = 1427818632(0x551ac888, float:1.06366291E13)
            r5 = 2
            if (r3 == r4) goto L_0x0059
            r4 = 1671764162(0x63a518c2, float:6.0909935E21)
            if (r3 == r4) goto L_0x004f
            r4 = 1810371957(0x6be81575, float:5.6114424E26)
            if (r3 == r4) goto L_0x0046
            goto L_0x0063
        L_0x0046:
            java.lang.String r3 = "generate"
            boolean r7 = r7.equals(r3)
            if (r7 == 0) goto L_0x0063
            goto L_0x0064
        L_0x004f:
            java.lang.String r1 = "display"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0063
            r1 = r5
            goto L_0x0064
        L_0x0059:
            java.lang.String r1 = "download"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0063
            r1 = r0
            goto L_0x0064
        L_0x0063:
            r1 = r2
        L_0x0064:
            if (r1 == 0) goto L_0x0083
            if (r1 == r0) goto L_0x007a
            if (r1 == r5) goto L_0x006b
            goto L_0x0095
        L_0x006b:
            android.content.Intent r7 = new android.content.Intent
            android.content.Context r0 = r6.getBaseContext()
            java.lang.Class<com.holopro.FileChooseActivity> r1 = com.holopro.FileChooseActivity.class
            r7.<init>(r0, r1)
            r6.startActivity(r7)
            goto L_0x0095
        L_0x007a:
            com.holopro.MainActivity$3 r7 = new com.holopro.MainActivity$3
            r7.<init>()
            r7.start()
            goto L_0x0095
        L_0x0083:
            android.content.Intent r7 = new android.content.Intent
            r7.<init>()
            java.lang.String r1 = "video/*"
            r7.setType(r1)
            java.lang.String r1 = "android.intent.action.GET_CONTENT"
            r7.setAction(r1)
            r6.startActivityForResult(r7, r0)
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.holopro.MainActivity.startOnClick(android.view.View):void");
    }

    public void cancelOnClick(View view) {
        resetUI();
    }

    private void resetUI() {
        this.generateButton.setImageResource(R.drawable.generate_1);
        this.downloadButton.setImageResource(R.drawable.download_1);
        this.displayButton.setImageResource(R.drawable.display_1);
        this.title.setText(BuildConfig.FLAVOR);
        this.directions.setText(BuildConfig.FLAVOR);
        this.startButton.setVisibility(8);
        this.cancelButton.setVisibility(8);
    }

    private Handler getHandler() {
        return new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    MainActivity.this.title.setText(BuildConfig.FLAVOR);
                    MainActivity.this.directions.setText("\nFailed to connect to the server. Please check for the network.");
                }
            }
        };
    }
}
