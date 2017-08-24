package no.triki.waffeltown.ui;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.triki.waffeltown.R;
import no.triki.waffeltown.camera.CameraPresenter;
import no.triki.waffeltown.camera.CameraPreview;
import no.triki.waffeltown.camera.ICameraView;
import no.triki.waffeltown.camera.OnCameraPreviewListener;
import no.triki.waffeltown.shared.utils.GlobalUtils;

public class CameraActivity extends AppCompatActivity implements ICameraView, OnCameraPreviewListener {

    public static final int CAMERA_REQUEST = 1007;

    @BindView(R.id.cameraFlipper)
    ViewFlipper cameraFlipper;

    @BindView(R.id.cameraPreview)
    FrameLayout cameraPreview;

    @BindView(R.id.ivWaffel)
    ImageView ivWaffel;

    @BindView(R.id.layoutLoading)
    RelativeLayout layoutLoading;

    private CameraPresenter presenter;
    private CameraPreview cPreview;
    private byte[] waffelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        presenter = new CameraPresenter(this);
        initUi();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cPreview.initCamera(this);
                }
                break;
        }
    }

    private void initUi() {
        cPreview = new CameraPreview(this, this);
        cameraPreview.addView(cPreview);
    }

    @OnClick(R.id.btnCaptureWaffel)
    public void btnCaptureWaffelClick() {
        waffelData = null;
        cPreview.captureWaffel();
    }

    @Override
    public void onCameraInitError() {

    }

    @Override
    public void onWaffelCaptured(byte[] data) {
        waffelData = data;

        // We have JPEG data, send it to the API to check if we have any waffels.
        cameraFlipper.setDisplayedChild(1);
        presenter.recognizeWaffel(data);
    }

    @Override
    public void onWaffelOk() {
        layoutLoading.setVisibility(View.GONE);
        GlobalUtils.setImageViewWithByteArray(ivWaffel, waffelData);
    }

    @Override
    public void onWaffelNotRecognized() {
        Toast.makeText(this, R.string.waffel_not_recognized, Toast.LENGTH_LONG).show();
        finish();
    }
}
