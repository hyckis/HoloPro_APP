package com.holopro.ref;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.holopro.BuildConfig;
import java.p004io.BufferedOutputStream;
import java.p004io.File;
import java.p004io.FileOutputStream;
import java.p004io.IOException;
import java.p004io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPManager {
    /* access modifiers changed from: private */
    public FTPClient ftpClient = new FTPClient();
    /* access modifiers changed from: private */
    public Handler handlerIB;
    /* access modifiers changed from: private */
    public Handler handlerPB;
    /* access modifiers changed from: private */
    public Handler handlerTV;
    /* access modifiers changed from: private */
    public ImageButton imageButton;
    /* access modifiers changed from: private */
    public String name = BuildConfig.FLAVOR;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public TextView textView;

    public FTPManager() {
        this.ftpClient.setDataTimeout(1000000);
        this.ftpClient.setControlEncoding("utf-8");
        setHandlers();
    }

    public void uploadFile(InputStream inputStream, String str, ProgressBar progressBar2, TextView textView2, ImageButton imageButton2) {
        this.progressBar = progressBar2;
        this.textView = textView2;
        this.imageButton = imageButton2;
        try {
            ArrayList arrayList = new ArrayList();
            for (FTPFile name2 : returnFTPFile()) {
                arrayList.add(name2.getName());
            }
            if (this.ftpClient.isConnected()) {
                this.ftpClient.disconnect();
            }
            this.ftpClient.connect("140.115.87.245", 21);
            if (FTPReply.isPositiveCompletion(this.ftpClient.getReplyCode())) {
                System.out.println("connected");
            }
            if (this.ftpClient.login("anonymous", BuildConfig.FLAVOR)) {
                if (arrayList.contains(str)) {
                    this.handlerPB.obtainMessage(2).sendToTarget();
                    this.handlerTV.obtainMessage(2).sendToTarget();
                } else {
                    this.handlerPB.obtainMessage(1).sendToTarget();
                    this.handlerTV.obtainMessage(3).sendToTarget();
                    this.ftpClient.setFileType(2);
                    this.ftpClient.enterLocalPassiveMode();
                    this.ftpClient.changeWorkingDirectory("/upload");
                    if (this.ftpClient.storeFile(str, inputStream)) {
                        this.handlerTV.obtainMessage(4).sendToTarget();
                        this.name = str;
                        new Timer().schedule(setTimerTask(), 10000);
                    } else {
                        System.out.println(this.ftpClient.getReplyString());
                        this.handlerPB.obtainMessage(2).sendToTarget();
                        this.handlerTV.obtainMessage(2).sendToTarget();
                    }
                }
                this.ftpClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadFile(File file, String str, ProgressBar progressBar2, TextView textView2, ImageButton imageButton2) {
        this.progressBar = progressBar2;
        this.textView = textView2;
        this.imageButton = imageButton2;
        try {
            this.handlerPB.obtainMessage(1).sendToTarget();
            if (this.ftpClient.isConnected()) {
                this.ftpClient.disconnect();
            }
            this.ftpClient.connect("140.115.87.245", 21);
            if (FTPReply.isPositiveCompletion(this.ftpClient.getReplyCode())) {
                System.out.println("connected");
            }
            if (this.ftpClient.login("anonymous", BuildConfig.FLAVOR)) {
                this.ftpClient.setFileType(2);
                this.ftpClient.enterLocalPassiveMode();
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath() + File.separator + str));
                    FTPClient fTPClient = this.ftpClient;
                    if (fTPClient.retrieveFile("/upload/output/" + str, bufferedOutputStream)) {
                        this.handlerTV.obtainMessage(1).sendToTarget();
                    } else {
                        this.handlerTV.obtainMessage(6).sendToTarget();
                    }
                    this.handlerPB.obtainMessage(2).sendToTarget();
                    this.handlerIB.obtainMessage(1).sendToTarget();
                    bufferedOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.ftpClient.disconnect();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public FTPFile[] returnFTPFile() {
        try {
            if (this.ftpClient.isConnected()) {
                this.ftpClient.disconnect();
            }
            this.ftpClient.connect("140.115.87.245", 21);
            if (this.ftpClient.login("anonymous", BuildConfig.FLAVOR)) {
                return this.ftpClient.listFiles("/upload/output");
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void setTimer() {
        new Timer().schedule(setTimerTask(), 5000);
    }

    private TimerTask setTimerTask() {
        return new TimerTask() {
            public void run() {
                try {
                    if (FTPManager.this.ftpClient.isConnected()) {
                        FTPManager.this.ftpClient.disconnect();
                    }
                    FTPManager.this.ftpClient.connect("140.115.87.245", 21);
                    if (FTPManager.this.ftpClient.login("anonymous", BuildConfig.FLAVOR)) {
                        FTPClient access$000 = FTPManager.this.ftpClient;
                        if (access$000.listFiles("/upload/output/" + FTPManager.this.name).length > 0) {
                            FTPManager.this.handlerPB.obtainMessage(2).sendToTarget();
                            FTPManager.this.handlerTV.obtainMessage(1).sendToTarget();
                            FTPManager.this.handlerIB.obtainMessage(1).sendToTarget();
                            return;
                        }
                        FTPManager.this.setTimer();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void setHandlers() {
        this.handlerPB = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 1) {
                    FTPManager.this.progressBar.setVisibility(0);
                } else if (i == 2) {
                    FTPManager.this.progressBar.setVisibility(8);
                }
            }
        };
        this.handlerTV = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        FTPManager.this.textView.setText("Complete!");
                        return;
                    case 2:
                        FTPManager.this.textView.setText("Failed. Please rename the file.");
                        return;
                    case 3:
                        FTPManager.this.textView.setText("Uploading...");
                        return;
                    case 4:
                        FTPManager.this.textView.setText("Generating...");
                        return;
                    case 5:
                        FTPManager.this.textView.setText("Downloading...");
                        return;
                    case 6:
                        FTPManager.this.textView.setText("Failed. Please retry.");
                        return;
                    default:
                        return;
                }
            }
        };
        this.handlerIB = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    FTPManager.this.imageButton.setVisibility(0);
                }
            }
        };
    }
}
