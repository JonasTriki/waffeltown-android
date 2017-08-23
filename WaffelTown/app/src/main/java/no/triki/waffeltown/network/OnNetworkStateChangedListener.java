package no.triki.waffeltown.network;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public interface OnNetworkStateChangedListener {
    void onNetworkConnected();
    void onNetworkDisconnected();
}
