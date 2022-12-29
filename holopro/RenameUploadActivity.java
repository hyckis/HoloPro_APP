package com.holopro;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.ContentResolverCompat;
import android.support.p000v4.p002os.CancellationSignal;
import android.support.p003v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.holopro.R;
import com.holopro.ref.ButtonOnClickFac;
import com.holopro.ref.FTPManager;
import java.p004io.File;
import java.p004io.InputStream;
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTPFile;

public class RenameUploadActivity extends AppCompatActivity {
    private TextView direction;
    /* access modifiers changed from: private */
    public ImageButton displayButton;
    /* access modifiers changed from: private */
    public ImageButton downloadButton;
    private ButtonOnClickFac fac;
    /* access modifiers changed from: private */
    public File file;
    /* access modifiers changed from: private */
    public String fileName;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public InputStream input;
    /* access modifiers changed from: private */
    public FTPManager manager;
    /* access modifiers changed from: private */
    public ProgressBar progressBarDown;
    /* access modifiers changed from: private */
    public ProgressBar progressBarUp;
    /* access modifiers changed from: private */
    public TextView reminderDown;
    /* access modifiers changed from: private */
    public TextView reminderUp;
    /* access modifiers changed from: private */
    public ImageButton renameButton;
    /* access modifiers changed from: private */
    public EditText renameField;
    private ImageButton uploadButton;
    private Uri uri;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_rename_upload);
        this.manager = new FTPManager();
        this.fac = new ButtonOnClickFac(this);
        this.uri = Uri.parse(getIntent().getStringExtra("fileUri"));
        this.fileName = getFileName(getApplicationContext().getContentResolver(), this.uri);
        try {
            this.input = getContentResolver().openInputStream(this.uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.direction = (TextView) findViewById(R.id.dir_rename);
        this.direction.setText(R.string.rename_intro);
        this.renameField = (EditText) findViewById(R.id.renameField);
        this.renameButton = (ImageButton) findViewById(R.id.renameButton);
        this.renameField.setOnTouchListener(listener());
        this.uploadButton = (ImageButton) findViewById(R.id.uploadButton_word);
        this.progressBarUp = (ProgressBar) findViewById(R.id.progressBar_upload);
        this.reminderUp = (TextView) findViewById(R.id.reminder_upload);
        this.downloadButton = (ImageButton) findViewById(R.id.downloadButton_direct);
        this.progressBarDown = (ProgressBar) findViewById(R.id.progressBar_download);
        this.reminderDown = (TextView) findViewById(R.id.reminder_download);
        this.displayButton = (ImageButton) findViewById(R.id.displayButton_direct);
        resetUI();
        checkServer();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.CharSequence, java.lang.String] */
    private void resetUI() {
        this.renameField.setText(this.fileName);
        this.progressBarUp.setVisibility(8);
        this.downloadButton.setVisibility(8);
        this.progressBarDown.setVisibility(8);
        this.displayButton.setVisibility(8);
    }

    private View.OnTouchListener listener() {
        return new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!RenameUploadActivity.this.renameField.isEnabled()) {
                    return false;
                }
                RenameUploadActivity.this.renameButton.setImageResource(R.drawable.rename_1);
                RenameUploadActivity.this.renameField.setInputType(1);
                return false;
            }
        };
    }

    public void renameOnClick(View view) {
        this.renameButton.setImageResource(R.drawable.rename_2);
        this.renameField.setInputType(0);
        checkServer();
    }

    public void uploadOnClick(View view) {
        this.uploadButton.setImageResource(R.drawable.upload_word_2);
        this.renameButton.setImageResource(R.drawable.rename_2);
        this.uploadButton.setEnabled(false);
        this.renameButton.setEnabled(false);
        this.renameField.setEnabled(false);
        this.progressBarUp.setVisibility(0);
        this.reminderUp.setText("Connecting...");
        this.fileName = this.renameField.getText().toString();
        new Thread() {
            public void run() {
                RenameUploadActivity.this.manager.uploadFile(RenameUploadActivity.this.input, RenameUploadActivity.this.fileName, RenameUploadActivity.this.progressBarUp, RenameUploadActivity.this.reminderUp, RenameUploadActivity.this.downloadButton);
            }
        }.start();
    }

    public void downloadOnClick(View view) {
        this.downloadButton.setImageResource(R.drawable.download_word_2);
        this.downloadButton.setEnabled(false);
        this.fileName = this.renameField.getText().toString();
        this.file = this.fac.setDownloadButton();
        new Thread() {
            public void run() {
                RenameUploadActivity.this.manager.downloadFile(RenameUploadActivity.this.file, RenameUploadActivity.this.fileName, RenameUploadActivity.this.progressBarDown, RenameUploadActivity.this.reminderDown, RenameUploadActivity.this.displayButton);
            }
        }.start();
    }

    public void displayOnClick(View view) {
        this.fileName = this.renameField.getText().toString();
        this.fac.setDisplayButton(this.fileName, BuildConfig.FLAVOR);
    }

    public void menuOnClick(View view) {
        resetUI();
        this.fac.setMenuButton();
        finish();
    }

    private void checkServer() {
        this.renameField.setError((CharSequence) null);
        this.handler = setHandler();
        new Thread() {
            public void run() {
                String obj = RenameUploadActivity.this.renameField.getText().toString();
                FTPFile[] returnFTPFile = RenameUploadActivity.this.manager.returnFTPFile();
                ArrayList arrayList = new ArrayList();
                for (FTPFile name : returnFTPFile) {
                    arrayList.add(name.getName());
                }
                if (arrayList.contains(obj)) {
                    RenameUploadActivity.this.handler.obtainMessage(2).sendToTarget();
                } else if (obj.equals(BuildConfig.FLAVOR) || obj.equals(".mp4") || obj.startsWith(" ") || !obj.endsWith(".mp4")) {
                    RenameUploadActivity.this.handler.obtainMessage(3).sendToTarget();
                } else {
                    RenameUploadActivity.this.handler.obtainMessage(1).sendToTarget();
                }
            }
        }.start();
    }

    private Handler setHandler() {
        return new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 1) {
                    RenameUploadActivity.this.reminderUp.setText("This file name is OK!");
                } else if (i == 2) {
                    RenameUploadActivity.this.reminderUp.setText(BuildConfig.FLAVOR);
                    RenameUploadActivity.this.renameField.setError("File name in server already!");
                } else if (i == 3) {
                    RenameUploadActivity.this.reminderUp.setText(BuildConfig.FLAVOR);
                    RenameUploadActivity.this.renameField.setError("Illegal file name!");
                }
            }
        };
    }

    @Nullable
    private String getFileName(@NonNull ContentResolver contentResolver, @NonNull Uri uri2) {
        Cursor query;
        if ("content".equals(uri2.getScheme()) && (query = ContentResolverCompat.query(contentResolver, uri2, new String[]{"_display_name"}, (String) null, (String[]) null, (String) null, (CancellationSignal) null)) != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(0);
                }
                query.close();
            } finally {
                query.close();
            }
        }
        return uri2.getLastPathSegment();
    }
}
