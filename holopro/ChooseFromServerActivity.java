package com.holopro;

import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.holopro.R;
import com.holopro.ref.ButtonOnClickFac;
import com.holopro.ref.FTPManager;
import java.p004io.File;

public class ChooseFromServerActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public ImageButton displayButton;
    /* access modifiers changed from: private */
    public ImageButton downloadButton;
    private ButtonOnClickFac fac;
    /* access modifiers changed from: private */
    public File file;
    /* access modifiers changed from: private */
    public String[] fileNames;
    /* access modifiers changed from: private */
    public String fileOnChoose;
    private ListView listView;
    /* access modifiers changed from: private */
    public FTPManager manager;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public TextView reminder;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_choose_from_server);
        setUI();
        this.manager = new FTPManager();
        this.fac = new ButtonOnClickFac(this);
        this.listView = (ListView) findViewById(R.id.listview_server);
        this.fileNames = getIntent().getStringArrayExtra("names");
        this.listView.setAdapter(new ArrayAdapter(this, 17367043, this.fileNames));
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r2v5, types: [java.lang.CharSequence, java.lang.String] */
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (ChooseFromServerActivity.this.progressBar.getVisibility() == 8) {
                    ChooseFromServerActivity.this.downloadButton.setVisibility(0);
                    ChooseFromServerActivity.this.downloadButton.setImageResource(R.drawable.download_word_1);
                    ChooseFromServerActivity.this.downloadButton.setEnabled(true);
                    ChooseFromServerActivity.this.displayButton.setVisibility(8);
                    ChooseFromServerActivity chooseFromServerActivity = ChooseFromServerActivity.this;
                    String unused = chooseFromServerActivity.fileOnChoose = chooseFromServerActivity.fileNames[i];
                    TextView access$500 = ChooseFromServerActivity.this.reminder;
                    access$500.setText("Choosing: \n" + ChooseFromServerActivity.this.fileOnChoose);
                }
            }
        });
    }

    public void downloadOnClick(View view) {
        if (this.fileOnChoose != null) {
            this.downloadButton.setImageResource(R.drawable.download_word_2);
            this.downloadButton.setEnabled(false);
            this.file = this.fac.setDownloadButton();
            new Thread() {
                public void run() {
                    ChooseFromServerActivity.this.manager.downloadFile(ChooseFromServerActivity.this.file, ChooseFromServerActivity.this.fileOnChoose, ChooseFromServerActivity.this.progressBar, ChooseFromServerActivity.this.reminder, ChooseFromServerActivity.this.displayButton);
                }
            }.start();
            return;
        }
        this.reminder.setText("There's no file chosen!");
    }

    public void displayOnClick(View view) {
        this.fac.setDisplayButton(this.fileOnChoose, BuildConfig.FLAVOR);
    }

    public void menuListOnClick(View view) {
        setUI();
        this.fac.setMenuButton();
        finish();
    }

    private void setUI() {
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar_download_list);
        this.progressBar.setVisibility(8);
        this.downloadButton = (ImageButton) findViewById(R.id.downloadButton_list);
        this.downloadButton.setVisibility(8);
        this.displayButton = (ImageButton) findViewById(R.id.displayButton_direct_list);
        this.displayButton.setVisibility(8);
        this.reminder = (TextView) findViewById(R.id.reminder_list);
        this.reminder.setText(BuildConfig.FLAVOR);
    }
}
