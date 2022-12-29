package com.holopro.ref;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import com.holopro.FileChooseActivity;
import com.holopro.HoloDisplayActivity;
import com.holopro.MainActivity;
import java.p004io.File;
import java.util.ArrayList;

public class ButtonOnClickFac {
    private Context context;

    public ButtonOnClickFac(Context context2) {
        this.context = context2;
    }

    public File setDownloadButton() {
        String str;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            str = this.context.getExternalFilesDir("output").getAbsolutePath();
        } else {
            str = this.context.getFilesDir() + File.separator;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public void setDisplayButton(String str, String str2) {
        String pathFromName = getPathFromName(str);
        File[] files = getFiles();
        ArrayList arrayList = new ArrayList();
        for (File path : files) {
            arrayList.add(path.getPath());
        }
        Bundle bundle = new Bundle();
        bundle.putString("Holo_Uri", pathFromName);
        bundle.putStringArrayList("Videos_List", arrayList);
        bundle.putString("BT_Addr", str2);
        Intent intent = new Intent(this.context.getApplicationContext(), HoloDisplayActivity.class);
        intent.putExtras(bundle);
        this.context.startActivity(intent);
    }

    public void setChooseFileButton(String str) {
        Intent intent = new Intent(this.context.getApplicationContext(), FileChooseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Bluetooth_Addr", str);
        intent.putExtras(bundle);
        this.context.startActivity(intent);
    }

    public String getPathFromName(String str) {
        String str2;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            str2 = this.context.getExternalFilesDir("output").getAbsolutePath();
        } else {
            str2 = this.context.getFilesDir() + File.separator;
        }
        File file = new File(str2);
        return file.getAbsolutePath() + File.separator + str;
    }

    public File[] getFiles() {
        return setDownloadButton().listFiles();
    }

    public void setMenuButton() {
        this.context.startActivity(new Intent(this.context.getApplicationContext(), MainActivity.class));
    }
}
