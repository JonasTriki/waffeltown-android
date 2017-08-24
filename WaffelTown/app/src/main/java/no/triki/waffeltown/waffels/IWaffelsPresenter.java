package no.triki.waffeltown.waffels;

import android.content.Intent;

/**
 * Created by Jonas on 24/08/2017.
 */

public interface IWaffelsPresenter {
    void loadIntent(Intent intent);
    void getWaffels();
}
