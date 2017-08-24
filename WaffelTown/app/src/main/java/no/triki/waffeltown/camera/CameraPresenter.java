package no.triki.waffeltown.camera;

import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.models.WaffelData;
import no.triki.waffeltown.models.WaffelRestTypes;
import no.triki.waffeltown.network.OnWaffelDataReceivedListener;
import no.triki.waffeltown.network.WaffelDataReceiver;
import no.triki.waffeltown.shared.utils.GlobalUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public class CameraPresenter implements ICameraPresenter, OnWaffelDataReceivedListener {

    private ICameraView view;
    private WaffelDataReceiver waffelDataReceiver;

    public CameraPresenter(ICameraView view) {
        this.view = view;
        waffelDataReceiver = new WaffelDataReceiver(this);
    }

    @Override
    public void onWaffelDataReceived(WaffelData<?> data, WaffelRestTypes waffelRestType) {
        switch (waffelRestType) {
            case RECOGNIZE_WAFFEL:
                WaffelData<Boolean> recogData = (WaffelData<Boolean>)data;
                if (recogData.getData() != null && recogData.getData()) {
                    view.onWaffelOk();
                } else {
                    view.onWaffelNotRecognized();
                }
                break;
            case POST_WAFFEL:
                WaffelData<Waffel> postData = (WaffelData<Waffel>)data;
                break;
        }
    }

    @Override
    public void onWaffelDataError(Throwable t, WaffelRestTypes waffelRestType) {
        t.printStackTrace();
    }

    @Override
    public void recognizeWaffel(byte[] data) {
        waffelDataReceiver.recognizeWaffel(GlobalUtils.byteArrayToImage(data));
    }
}
