package no.triki.waffeltown.camera;

import android.graphics.Bitmap;

/**
 * Created by Jonas Triki on 23.08.2017.
 */

public interface ICameraPresenter {
    void recognizeWaffel(byte[] data);
    void uploadWaffel(int rating, String description, String topping, int consistency, Bitmap bitmap);
}
