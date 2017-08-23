package no.triki.waffeltown.splash;

import java.util.ArrayList;

import no.triki.waffeltown.models.Waffel;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public interface ISplashView {
    void onWaffelsReady(ArrayList<Waffel> waffels);
    void onNetworkDisconnected();
}
