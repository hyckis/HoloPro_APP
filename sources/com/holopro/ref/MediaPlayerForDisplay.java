package com.holopro.ref;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import java.p004io.IOException;

public class MediaPlayerForDisplay extends MediaPlayer {
    private Context context;
    /* access modifiers changed from: private */
    public Surface surface;
    private int tvDegree;

    public MediaPlayerForDisplay(Context context2, int i) {
        this.context = context2;
        this.tvDegree = i;
    }

    public TextureView.SurfaceTextureListener setSTL(final TextureView textureView, final Uri uri) {
        return new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                Surface unused = MediaPlayerForDisplay.this.surface = new Surface(surfaceTexture);
                MediaPlayerForDisplay mediaPlayerForDisplay = MediaPlayerForDisplay.this;
                mediaPlayerForDisplay.setSurface(mediaPlayerForDisplay.surface);
                MediaPlayerForDisplay.this.setVideo(uri);
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                MediaPlayerForDisplay.this.release();
                return false;
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                MediaPlayerForDisplay.this.updateTextureViewSizeCenterCrop(textureView);
            }
        };
    }

    public void setVideo(Uri uri) {
        try {
            setDataSource(this.context, uri);
            setScreenOnWhilePlaying(true);
            setVolume(0.0f, 0.0f);
            setLooping(true);
            setOnErrorListener(getError(uri));
            setPlaybackParams(getPlaybackParams().setSpeed(getPlaybackParams().getSpeed() * 0.5f));
            prepare();
            mpStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MediaPlayer.OnErrorListener getError(final Uri uri) {
        return new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                MediaPlayerForDisplay.this.setVideo(uri);
                return false;
            }
        };
    }

    public void mpStart() {
        int duration = ((int) ((long) getDuration())) / 4;
        int i = this.tvDegree;
        if (i == 0) {
            seekTo(0);
            start();
        } else if (i == 90) {
            seekTo(duration);
            start();
        } else if (i == 180) {
            seekTo(duration * 2);
            start();
        } else if (i == 270) {
            seekTo(duration * 3);
            start();
        }
    }

    public void mpStartForReload(int i) {
        seekTo(i);
        start();
    }

    /* access modifiers changed from: private */
    public void updateTextureViewSizeCenterCrop(TextureView textureView) {
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        float videoWidth = (float) getVideoWidth();
        float videoHeight = (float) getVideoHeight();
        Matrix matrix = new Matrix();
        float min = Math.min(width / videoWidth, height / videoHeight);
        matrix.preTranslate((width - videoWidth) / 2.0f, (height - videoHeight) / 2.0f);
        matrix.preScale(videoWidth / width, videoHeight / height);
        matrix.postScale(min, min, width / 2.0f, height / 2.0f);
        textureView.setTransform(matrix);
        textureView.postInvalidate();
    }
}
