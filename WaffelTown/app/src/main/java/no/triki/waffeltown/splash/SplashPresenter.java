package no.triki.waffeltown.splash;

import android.content.Context;

import java.util.ArrayList;

import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.models.WaffelData;
import no.triki.waffeltown.models.WaffelRestTypes;
import no.triki.waffeltown.network.NetworkStateReceiver;
import no.triki.waffeltown.network.OnNetworkStateChangedListener;
import no.triki.waffeltown.network.OnWaffelDataReceivedListener;
import no.triki.waffeltown.network.WaffelDataReceiver;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public class SplashPresenter implements ISplashPresenter, OnNetworkStateChangedListener, OnWaffelDataReceivedListener {

    private ISplashView view;
    private NetworkStateReceiver networkStateReceiver;
    private WaffelDataReceiver waffelDataReceiver;

    public SplashPresenter(ISplashView view) {
        this.view = view;
        networkStateReceiver = new NetworkStateReceiver(this);
        waffelDataReceiver = new WaffelDataReceiver(this);
    }

    @Override
    public void start(Context context) {
        networkStateReceiver.registerContext(context);
    }

    @Override
    public void pause(Context context) {
        networkStateReceiver.unregisterContext(context);
    }

    @Override
    public void onNetworkConnected() {

        // Connected to internet, load waffels from API.
        waffelDataReceiver.getWaffels();
    }

    @Override
    public void onNetworkDisconnected() {
        view.onNetworkDisconnected();
    }

    @Override
    public void onWaffelDataReceived(WaffelData<?> data, WaffelRestTypes waffelRestType) {
        if (waffelRestType == WaffelRestTypes.GET_WAFFELS) {
            view.onWaffelsReady((ArrayList<Waffel>)data.getData());
        }
    }

    @Override
    public void onWaffelDataError(Throwable t, WaffelRestTypes waffelRestType) {
        t.printStackTrace();
    }
}
