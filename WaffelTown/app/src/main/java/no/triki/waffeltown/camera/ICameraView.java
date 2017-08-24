package no.triki.waffeltown.camera;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public interface ICameraView {
    void onWaffelOk();
    void onWaffelNotRecognized();
    void onWaffelUploaded();
    void onWaffelErrorUploading();
}
