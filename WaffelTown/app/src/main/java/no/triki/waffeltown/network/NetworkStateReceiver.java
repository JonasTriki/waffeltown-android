package no.triki.waffeltown.network;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import no.triki.waffeltown.shared.StateBroadcastReceiver;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public class NetworkStateReceiver extends StateBroadcastReceiver {

    private OnNetworkStateChangedListener mListener;
    private boolean mConnectedToInternet;

    public NetworkStateReceiver(OnNetworkStateChangedListener listener) {
        mListener = listener;
    }

    public void registerContext(Context context) {
        context.registerReceiver(this, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private boolean checkNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.net.wifi.STATE_CHANGE") || intent.getAction().matches("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (checkNetworkConnection(context)) {
                if (!mConnectedToInternet) {
                    mConnectedToInternet = true;
                    mListener.onNetworkConnected();
                }
            } else {
                if (mConnectedToInternet) {
                    mConnectedToInternet = false;
                    mListener.onNetworkDisconnected();
                }
            }
        }
    }
}
