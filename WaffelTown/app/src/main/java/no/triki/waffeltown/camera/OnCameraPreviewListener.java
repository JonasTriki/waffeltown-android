package no.triki.waffeltown.camera;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public interface OnCameraPreviewListener {
    void onCameraInitError();
    void onWaffelCaptured(byte[] data);
}
