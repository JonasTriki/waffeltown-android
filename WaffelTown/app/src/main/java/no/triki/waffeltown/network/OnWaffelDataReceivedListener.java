package no.triki.waffeltown.network;

import no.triki.waffeltown.models.WaffelData;
import no.triki.waffeltown.models.WaffelRestTypes;

/**
 * Created by Jonas Triki on 22.08.2017.
 */

public interface OnWaffelDataReceivedListener {
    void onWaffelDataReceived(WaffelData<?> data, WaffelRestTypes waffelRestType);
    void onWaffelDataError(Throwable t, WaffelRestTypes waffelRestType);
}
