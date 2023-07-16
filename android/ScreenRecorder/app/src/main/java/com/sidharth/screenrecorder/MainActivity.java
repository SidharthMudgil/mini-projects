package com.sidharth.screenrecorder;

import android.app.Activity;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaProjection mediaProjection;
    VirtualDisplay virtualDisplay;
    MediaProjectionManager mediaProjectionManager;
    FloatingActionButton recordButton;
    DisplayMetrics displayMetrics;
    Intent backgroundService;
    boolean recording = false;
    MediaRecorder mediaRecorder;
    MediaProjectionCallback mediaProjectionCallback;

    // launcher to start the media projection after a popup if user has given positive response
    ActivityResultLauncher<Intent> startMediaProjection = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    recording = true;
                    recordButton.setImageResource(R.drawable.ic_stop);
                    mediaProjection = mediaProjectionManager.getMediaProjection(result.getResultCode(), result.getData());
                    mediaProjection.registerCallback(mediaProjectionCallback, null);
                    mediaRecorder.start();
                    setUpVirtualDisplay();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundService = new Intent(this, BackgroundService.class);

        startForegroundService(backgroundService);

        recordButton = findViewById(R.id.recording_button);
        recordButton.setOnClickListener(v -> {
            if (!recording) {
                startRecording();
            } else {
                stopRecording();
            }
        });

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);

        mediaProjectionCallback = new MediaProjectionCallback();

        mediaRecorder = new MediaRecorder();
    }

    // method to set up the virtual display by the help of which the screen recording is done.
    private void setUpVirtualDisplay() {
        virtualDisplay = mediaProjection.createVirtualDisplay(
                "ScreenCapture",
                displayMetrics.widthPixels,
                displayMetrics.heightPixels,
                displayMetrics.densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mediaRecorder.getSurface(), null, null);
    }

    // method to start the recording
    private void startRecording() {
        recording = true;
        initRecorder();
        if (mediaProjection != null) {
            setUpVirtualDisplay();
            mediaRecorder.start();
        } else {
            startMediaProjection.launch(mediaProjectionManager.createScreenCaptureIntent());
        }
    }

    // method to stop the recording
    private void stopRecording() {
        if (virtualDisplay == null) {
            return;
        }
        mediaRecorder.stop();
        mediaRecorder.reset();
        virtualDisplay.release();
        destroyMediaProjection();
        recording = false;
        recordButton.setImageResource(R.drawable.ic_start);
    }

    // overridden method to stop service on activity destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaProjection != null) {
            destroyMediaProjection();
        }
        stopService(backgroundService);
    }

    // method to stop mediaProjection and unregister callbacks;
    private void destroyMediaProjection() {
        if (mediaProjection != null) {
            mediaProjection.unregisterCallback(mediaProjectionCallback);
            mediaProjection.stop();
            mediaProjection = null;
        }
    }

    // MediaProjectionCallback to stop recording when mediaProjection is stopped
    private class MediaProjectionCallback extends MediaProjection.Callback {
        @Override
        public void onStop() {
            if (recording) {
                recording = false;
            }
            stopRecording();
        }
    }

    // method to prepare the MediaRecorder save the screen recording
    private void initRecorder() {
        try {
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setVideoEncodingBitRate(512 * 1000);
            mediaRecorder.setVideoFrameRate(60);
            mediaRecorder.setVideoSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + System.currentTimeMillis() + ".mp4");
            mediaRecorder.setOutputFile(file);
            mediaRecorder.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}