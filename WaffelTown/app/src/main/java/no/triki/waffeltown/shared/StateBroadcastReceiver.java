package no.triki.waffeltown.shared;

import android.content.BroadcastReceiver;
import android.content.Context;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public abstract class StateBroadcastReceiver extends BroadcastReceiver {
    public abstract void registerContext(Context context);
    public void unregisterContext(Context context) {
        context.unregisterReceiver(this);
    }
}
