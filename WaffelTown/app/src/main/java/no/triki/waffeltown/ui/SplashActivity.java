package no.triki.waffeltown.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.splash.ISplashView;
import no.triki.waffeltown.splash.SplashPresenter;

/**
 * Created by Jonas on 23/08/2017.
 */

public class SplashActivity  extends AppCompatActivity implements ISplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SplashPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause(this);
    }

    @Override
    public void onWaffelsReady(ArrayList<Waffel> waffels) {
        Intent i = new Intent(this, MainActivity.class);
        i.putParcelableArrayListExtra("toilets", waffels);
        startActivity(i);
        finish();
    }

    @Override
    public void onNetworkDisconnected() {
        // TODO: 23.08.2017 something
    }
}
