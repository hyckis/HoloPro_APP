package com.holopro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.example.holopro.R;
import com.holopro.ref.ButtonOnClickFac;
import java.p004io.File;
import java.util.ArrayList;
import java.util.List;

public class FileChooseActivity extends AppCompatActivity {
    private String address;
    /* access modifiers changed from: private */
    public ImageButton displayButton;
    private ButtonOnClickFac fac;
    private List fileList = new ArrayList();
    /* access modifiers changed from: private */
    public List fileNameList = new ArrayList();
    /* access modifiers changed from: private */
    public String fileOnChoose;
    /* access modifiers changed from: private */
    public TextView reminder;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_file_choose);
        this.fac = new ButtonOnClickFac(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.address = extras.getString("Bluetooth_Addr");
        } else {
            this.address = BuildConfig.FLAVOR;
        }
        this.displayButton = (ImageButton) findViewById(R.id.displayButton_choose);
        this.displayButton.setVisibility(8);
        this.reminder = (TextView) findViewById(R.id.reminder_display_list);
        ListView listView = (ListView) findViewById(R.id.listview);
        for (File file : this.fac.getFiles()) {
            this.fileList.add(file.getPath());
            this.fileNameList.add(file.getName());
        }
        listView.setAdapter(new ArrayAdapter(this, 17367043, this.fileNameList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r2v6, types: [java.lang.CharSequence, java.lang.String] */
            @SuppressLint({"SetTextI18n"})
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                FileChooseActivity.this.displayButton.setVisibility(0);
                FileChooseActivity fileChooseActivity = FileChooseActivity.this;
                String unused = fileChooseActivity.fileOnChoose = fileChooseActivity.fileNameList.get(i).toString();
                TextView access$300 = FileChooseActivity.this.reminder;
                access$300.setText("Choosing: \n" + FileChooseActivity.this.fileOnChoose);
            }
        });
    }

    public void displayListOnClick(View view) {
        String str = this.fileOnChoose;
        if (str != null) {
            this.fac.setDisplayButton(str, this.address);
        } else {
            this.reminder.setText("There's no file chosen!");
        }
    }

    public void menuChooseOnClick(View view) {
        this.fac.setMenuButton();
    }
}
