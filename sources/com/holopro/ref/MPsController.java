package com.holopro.ref;

import android.net.Uri;
import java.p004io.PrintStream;

public class MPsController {
    private int[] currentPosArray = {-1, -1, -1, -1};
    private MediaPlayerForDisplay mp0;
    private MediaPlayerForDisplay mp180;
    private MediaPlayerForDisplay mp270;
    private MediaPlayerForDisplay mp90;

    public MPsController(MediaPlayerForDisplay mediaPlayerForDisplay, MediaPlayerForDisplay mediaPlayerForDisplay2, MediaPlayerForDisplay mediaPlayerForDisplay3, MediaPlayerForDisplay mediaPlayerForDisplay4) {
        this.mp0 = mediaPlayerForDisplay;
        this.mp90 = mediaPlayerForDisplay2;
        this.mp180 = mediaPlayerForDisplay3;
        this.mp270 = mediaPlayerForDisplay4;
    }

    public void resetMP() {
        this.mp0.reset();
        this.mp90.reset();
        this.mp180.reset();
        this.mp270.reset();
    }

    public void prepareMP(Uri uri) {
        this.mp0.setVideo(uri);
        this.mp90.setVideo(uri);
        this.mp180.setVideo(uri);
        this.mp270.setVideo(uri);
    }

    public void startMP() {
        this.mp0.start();
        this.mp90.start();
        this.mp180.start();
        this.mp270.start();
    }

    public void reloadMP(Uri uri, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < 4; i5++) {
            System.out.println("before reload: " + this.currentPosArray[i5]);
        }
        if (!this.mp0.isPlaying() && i > -1) {
            this.mp0.mpStartForReload(i);
            this.currentPosArray[0] = -1;
        }
        if (!this.mp90.isPlaying() && i2 > -1) {
            this.mp90.mpStartForReload(i2);
            this.currentPosArray[1] = -1;
        }
        if (!this.mp180.isPlaying() && i3 > -1) {
            this.mp180.mpStartForReload(i3);
            this.currentPosArray[2] = -1;
        }
        if (!this.mp270.isPlaying() && i4 > -1) {
            this.mp270.mpStartForReload(i4);
            this.currentPosArray[3] = -1;
        }
        for (int i6 = 0; i6 < 4; i6++) {
            System.out.println("after reload: " + this.currentPosArray[i6]);
        }
    }

    public void stopMP() {
        this.mp0.stop();
        this.mp90.stop();
        this.mp180.stop();
        this.mp270.stop();
    }

    public void pauseMP() {
        for (int i = 0; i < 4; i++) {
            System.out.println("before pause: " + this.currentPosArray[i]);
        }
        if (this.mp0.isPlaying()) {
            this.currentPosArray[0] = this.mp0.getCurrentPosition();
            this.mp0.pause();
        }
        if (this.mp90.isPlaying()) {
            this.currentPosArray[1] = this.mp90.getCurrentPosition();
            this.mp90.pause();
        }
        if (this.mp180.isPlaying()) {
            this.currentPosArray[2] = this.mp180.getCurrentPosition();
            this.mp180.pause();
        }
        if (this.mp270.isPlaying()) {
            this.currentPosArray[3] = this.mp270.getCurrentPosition();
            this.mp270.pause();
        }
        for (int i2 = 0; i2 < 4; i2++) {
            System.out.println("after pause: " + this.currentPosArray[i2]);
        }
    }

    public void releaseMP() {
        this.mp0.release();
        this.mp90.release();
        this.mp180.release();
        this.mp270.release();
    }

    public void printStatus() {
        PrintStream printStream = System.out;
        printStream.println("mp0: " + this.mp0.isPlaying());
        PrintStream printStream2 = System.out;
        printStream2.println("mp90: " + this.mp90.isPlaying());
        PrintStream printStream3 = System.out;
        printStream3.println("mp180: " + this.mp180.isPlaying());
        PrintStream printStream4 = System.out;
        printStream4.println("mp270: " + this.mp270.isPlaying());
    }

    public int[] returnPos() {
        return this.currentPosArray;
    }
}
