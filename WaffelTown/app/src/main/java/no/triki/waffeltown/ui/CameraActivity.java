package no.triki.waffeltown.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;

import java.util.ArrayList;

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

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.etDescription)
    EditText etDescription;

    @BindView(R.id.listTopping)
    LinearLayout listTopping;

    @BindView(R.id.rgConsistency)
    RadioGroup rgConsistency;

    @BindView(R.id.btnPostWaffel)
    Button btnPostWaffel;

    private CameraPresenter presenter;
    private CameraPreview cPreview;
    private boolean waffelImageOk;
    private byte[] waffelData;
    private Bitmap waffelBitmap;

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
        waffelBitmap = null;
        waffelImageOk = false;
        cPreview.captureWaffel();
    }

    @OnClick(R.id.btnAddTopping)
    public void btnAddToppingClick() {

    }

    @OnClick(R.id.btnPostWaffel)
    public void btnPostWaffelClick() {

        // First, check if we are ready to upload waffel.
        if (!waffelImageOk) return;
        if (ratingBar.getRating() == 0) {
            Toast.makeText(this, R.string.rating_insufficient, Toast.LENGTH_SHORT).show();
            return;
        }
        if (etDescription.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.desc_insufficient, Toast.LENGTH_SHORT).show();
            return;
        }
        if (rgConsistency.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, R.string.consistency_insufficient, Toast.LENGTH_SHORT).show();
            return;
        }

        // Get waffel topping in a json array.
        ArrayList<String> toppings = new ArrayList<>();
        for (int i = 0; i < listTopping.getChildCount(); i++) {
            View view = listTopping.getChildAt(i);
            if (view instanceof CheckBox) {
                if (((CheckBox)view).isChecked()) toppings.add(((CheckBox)view).getText().toString());
            }
        }
        String topping = (new Gson()).toJson(toppings);

        // Next figure out what consistency we have.
        int consistency = 0;
        switch (rgConsistency.getCheckedRadioButtonId()) {
            case R.id.rbCrispy:
                consistency = 1;
                break;
            case R.id.rbOkay:
                consistency = 2;
                break;
            case R.id.rbSoft:
                consistency = 3;
                break;
        }

        // Ready! Upload waffel.
        presenter.uploadWaffel((int)ratingBar.getRating(), etDescription.getText().toString().trim(), topping, consistency, waffelBitmap);
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

    private void showWaffel() {
        layoutLoading.setVisibility(View.GONE);
        waffelBitmap = GlobalUtils.setImageViewWithByteArray(ivWaffel, waffelData);
        waffelData = null;
        waffelImageOk = true;
    }

    @Override
    public void onWaffelOk() {
        showWaffel();
    }

    @Override
    public void onWaffelMaybe() {
        showWaffel();
    }

    @Override
    public void onWaffelNotRecognized() {
        Toast.makeText(this, R.string.waffel_not_recognized, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onWaffelUploaded() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void onWaffelErrorUploading() {
        setResult(MainActivity.ERROR_UPLOADING_WAFFEL);
        finish();
    }
}
