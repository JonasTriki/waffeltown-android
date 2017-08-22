package no.triki.waffeltown.models;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public interface OnWaffelDataReceivedListener {
    void onWaffelDataReceived(WaffelData<?> data, WaffelRestTypes waffelRestType);
    void onWaffelDataError(Throwable t, WaffelRestTypes waffelRestType);
}
