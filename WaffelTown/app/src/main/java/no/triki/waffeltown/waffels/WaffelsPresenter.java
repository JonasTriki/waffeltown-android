package no.triki.waffeltown.waffels;

import android.content.Intent;

import java.util.ArrayList;

import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.models.WaffelData;
import no.triki.waffeltown.models.WaffelRestTypes;
import no.triki.waffeltown.network.OnWaffelDataReceivedListener;
import no.triki.waffeltown.network.WaffelDataReceiver;

/**
 * Created by Jonas on 24/08/2017.
 */

public class WaffelsPresenter implements IWaffelsPresenter, OnWaffelDataReceivedListener {

    private IWaffesView view;
    private WaffelDataReceiver waffelDataReceiver;

    public WaffelsPresenter(IWaffesView view) {
        this.view = view;
        waffelDataReceiver = new WaffelDataReceiver(this);
    }

    @Override
    public void loadIntent(Intent intent) {
        view.waffelsLoaded(intent.<Waffel>getParcelableArrayListExtra("waffels"));
    }

    @Override
    public void getWaffels() {
        waffelDataReceiver.getWaffels();
    }

    @Override
    public void onWaffelDataReceived(WaffelData<?> data, WaffelRestTypes waffelRestType) {
        if (waffelRestType == WaffelRestTypes.GET_WAFFELS) {
            view.waffelsLoaded((ArrayList<Waffel>)data.getData());
        }
    }

    @Override
    public void onWaffelDataError(Throwable t, WaffelRestTypes waffelRestType) {
        // TODO: 24/08/2017 something?
    }
}
