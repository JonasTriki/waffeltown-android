package no.triki.waffeltown.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import no.triki.waffeltown.shared.utils.GlobalUtils;
import no.triki.waffeltown.ui.CameraActivity;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback, Camera.PictureCallback {

    private OnCameraPreviewListener listener;
    private SurfaceHolder holder;
    private Camera camera;

    public CameraPreview(Context context, OnCameraPreviewListener listener) {
        super(context);
        this.listener = listener;
        checkPermissions(context);
    }

    private void checkPermissions(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)context,
                    new String[]{Manifest.permission.CAMERA},
                    CameraActivity.CAMERA_REQUEST);
        } else {
            initCamera(context);
        }
    }

    public void initCamera(Context context) {
        if (hasDeviceCamera(context)) {
            camera = getCameraInstance();
            if (camera != null) {
                Camera.Parameters params = camera.getParameters();
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                camera.setParameters(params);
                camera.setDisplayOrientation(90);
                holder = getHolder();
                holder.addCallback(this);
            } else {
                listener.onCameraInitError();
            }
        } else {
            listener.onCameraInitError();
        }
    }

    public void captureWaffel() {
        camera.takePicture(null, null, this);
    }

    private boolean hasDeviceCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private Camera getCameraInstance() {
        int backCameraId = -1;
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                backCameraId = i;
                break;
            }
        }
        if (backCameraId == -1) return null;
        Camera c = null;
        try {
            c = Camera.open(backCameraId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        listener.onWaffelCaptured(bytes);

        // Stop camera.
        camera.stopPreview();
        camera.release();
        this.camera = null;
    }
}
