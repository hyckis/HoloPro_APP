package com.holopro;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.p003v7.app.AppCompatActivity;
import android.view.TextureView;
import android.widget.Toast;
import com.example.holopro.R;
import com.holopro.gesture.GestureInterface;
import com.holopro.gesture.GestureThread;
import com.holopro.ref.MPsController;
import com.holopro.ref.MediaPlayerForDisplay;
import java.util.ArrayList;

public class HoloDisplayActivity extends AppCompatActivity {
    private String address;
    /* access modifiers changed from: private */
    public MPsController controller;
    private Bundle extras;
    /* access modifiers changed from: private */
    public int flag;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public boolean isEnd;
    private boolean isGesturing;
    private MediaPlayerForDisplay mp0;
    private MediaPlayerForDisplay mp180;
    private MediaPlayerForDisplay mp270;
    private MediaPlayerForDisplay mp90;
    private Runnable task;
    private GestureThread thread;
    private TextureView tv0;
    private TextureView tv180;
    private TextureView tv270;
    private TextureView tv90;
    private String videoPath;
    private Uri videoUri;

    static /* synthetic */ int access$308(HoloDisplayActivity holoDisplayActivity) {
        int i = holoDisplayActivity.flag;
        holoDisplayActivity.flag = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_holo_display);
        this.mp0 = new MediaPlayerForDisplay(this, 0);
        this.mp90 = new MediaPlayerForDisplay(this, 90);
        this.mp180 = new MediaPlayerForDisplay(this, 180);
        this.mp270 = new MediaPlayerForDisplay(this, 270);
        this.tv0 = (TextureView) findViewById(R.id.textureView0);
        this.tv90 = (TextureView) findViewById(R.id.textureView90);
        this.tv90.setRotation(90.0f);
        this.tv180 = (TextureView) findViewById(R.id.textureView180);
        this.tv180.setRotation(180.0f);
        this.tv270 = (TextureView) findViewById(R.id.textureView270);
        this.tv270.setRotation(270.0f);
        this.extras = getIntent().getExtras();
        this.videoPath = this.extras.getString("Holo_Uri");
        this.videoUri = Uri.parse(this.videoPath);
        setMP();
        this.controller = new MPsController(this.mp0, this.mp90, this.mp180, this.mp270);
        try {
            getSupportActionBar().hide();
            Settings.System.putInt(getContentResolver(), "screen_brightness_mode", 0);
        } catch (Exception unused) {
            Toast.makeText(this, "Failed to get permission", 1).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        this.address = this.extras.getString("BT_Addr");
        if (!this.address.equals(BuildConfig.FLAVOR)) {
            this.isGesturing = true;
            this.thread = new GestureThread(this, this.address, new GestureInterface() {
                public void gestureMonitor(int i, float f) {
                    if (i == 10 || i == -10) {
                        HoloDisplayActivity.this.gestureWithLen(i, f);
                    } else {
                        HoloDisplayActivity.this.gestureWithAngle(i);
                    }
                    HoloDisplayActivity.this.controller.printStatus();
                }
            });
            this.thread.start();
            return;
        }
        this.isGesturing = false;
    }

    private void setMP() {
        TextureView textureView = this.tv0;
        textureView.setSurfaceTextureListener(this.mp0.setSTL(textureView, this.videoUri));
        TextureView textureView2 = this.tv90;
        textureView2.setSurfaceTextureListener(this.mp90.setSTL(textureView2, this.videoUri));
        TextureView textureView3 = this.tv180;
        textureView3.setSurfaceTextureListener(this.mp180.setSTL(textureView3, this.videoUri));
        TextureView textureView4 = this.tv270;
        textureView4.setSurfaceTextureListener(this.mp270.setSTL(textureView4, this.videoUri));
    }

    public void onStop() {
        super.onStop();
        startAutoBrightness();
        this.controller.stopMP();
        this.controller.releaseMP();
        if (this.isGesturing) {
            this.thread.cancel();
        }
    }

    public void startAutoBrightness() {
        ContentResolver contentResolver = getContentResolver();
        Settings.System.putInt(contentResolver, "screen_brightness_mode", 1);
        contentResolver.notifyChange(Settings.System.getUriFor("screen_brightness"), (ContentObserver) null);
    }

    /* access modifiers changed from: private */
    public void gestureWithAngle(int i) {
        if (i == 0) {
            this.controller.pauseMP();
        } else if (i == 1) {
            changePlaying("last");
        } else if (i == 2) {
            screenBrightnessUp();
        } else if (i == 3) {
            changePlaying("next");
        } else if (i == 4) {
            int[] returnPos = this.controller.returnPos();
            this.controller.reloadMP(this.videoUri, returnPos[0], returnPos[1], returnPos[2], returnPos[3]);
        }
    }

    /* access modifiers changed from: private */
    public void gestureWithLen(int i, float f) {
        if (i == -10) {
            f *= -1.0f;
        }
        if (!this.mp0.isPlaying() || !this.mp90.isPlaying() || !this.mp180.isPlaying() || !this.mp270.isPlaying()) {
            this.controller.startMP();
        }
        rotate(this.mp0, f);
        rotate(this.mp90, f);
        rotate(this.mp180, f);
        rotate(this.mp270, f);
    }

    private void screenBrightnessUp() {
        Settings.System.putInt(getContentResolver(), "screen_brightness", 255);
    }

    private void changePlaying(String str) {
        ArrayList<String> stringArrayList = this.extras.getStringArrayList("Videos_List");
        int size = stringArrayList.size();
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (stringArrayList.get(i).equals(this.videoPath)) {
                size = i;
            }
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 3314326) {
            if (hashCode == 3377907 && str.equals("next")) {
                c = 0;
            }
        } else if (str.equals("last")) {
            c = 1;
        }
        if (c == 0) {
            try {
                this.videoPath = stringArrayList.get(size + 1).toString();
            } catch (IndexOutOfBoundsException unused) {
                this.videoPath = stringArrayList.get(0).toString();
            }
        } else if (c == 1) {
            try {
                this.videoPath = stringArrayList.get(size - 1).toString();
            } catch (IndexOutOfBoundsException unused2) {
                this.videoPath = stringArrayList.get(stringArrayList.size() - 1).toString();
            }
        }
        this.videoUri = Uri.parse(this.videoPath);
        this.controller.stopMP();
        this.controller.resetMP();
        this.controller.prepareMP(this.videoUri);
    }

    private void rotate(final MediaPlayer mediaPlayer, float f) {
        this.handler = new Handler();
        int abs = (int) Math.abs(((float) mediaPlayer.getDuration()) * f);
        if (f > 0.0f) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    mediaPlayer.pause();
                }
            }, (long) abs);
        } else if (f < 0.0f) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            this.isEnd = false;
            this.flag = 0;
            this.task = setRotation(mediaPlayer, abs, currentPosition);
            this.handler.postDelayed(this.task, 5);
        }
    }

    private Runnable setRotation(final MediaPlayer mediaPlayer, final int i, final int i2) {
        return new Runnable() {
            public void run() {
                int i = i2 - 10;
                if (i <= 10) {
                    i = mediaPlayer.getDuration() - i;
                }
                HoloDisplayActivity.access$308(HoloDisplayActivity.this);
                if (HoloDisplayActivity.this.flag * 10 <= i) {
                    boolean unused = HoloDisplayActivity.this.isEnd = false;
                } else {
                    boolean unused2 = HoloDisplayActivity.this.isEnd = true;
                }
                if (!HoloDisplayActivity.this.isEnd) {
                    mediaPlayer.seekTo(i);
                    mediaPlayer.pause();
                    HoloDisplayActivity.this.handler.postDelayed(this, 75);
                    return;
                }
                mediaPlayer.seekTo(i);
                mediaPlayer.pause();
            }
        };
    }
}
