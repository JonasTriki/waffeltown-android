package no.triki.waffeltown.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.triki.waffeltown.R;
import no.triki.waffeltown.models.Waffel;
import no.triki.waffeltown.waffels.IWaffesView;
import no.triki.waffeltown.waffels.WaffelItemListener;
import no.triki.waffeltown.waffels.WaffelListAdapter;
import no.triki.waffeltown.waffels.WaffelsPresenter;

public class MainActivity extends AppCompatActivity implements IWaffesView, WaffelItemListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int CAPTURE_WAFFEL = 1337;
    public static final int ERROR_UPLOADING_WAFFEL = 1338;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layoutSwipe)
    SwipeRefreshLayout layoutSwipe;

    @BindView(R.id.waffelList)
    RecyclerView waffelList;

    private WaffelsPresenter presenter;
    private ArrayList<Waffel> waffels;
    private WaffelListAdapter adapter;
    private boolean isRequestingWaffels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new WaffelsPresenter(this);
        setSupportActionBar(toolbar);
        initUi();
    }

    private void initUi() {

        // Load waffels
        presenter.loadIntent(getIntent());

        // Swipe layout
        layoutSwipe.setOnRefreshListener(this);
    }

    private void reloadWaffels() {
        if (!isRequestingWaffels) {
            isRequestingWaffels = true;
            presenter.getWaffels();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                Intent i = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(i, CAPTURE_WAFFEL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_WAFFEL) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, R.string.waffel_uploaded, Toast.LENGTH_LONG).show();
                reloadWaffels();
            } else if (resultCode == ERROR_UPLOADING_WAFFEL) {
                Toast.makeText(this, R.string.waffel_error_upload, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void waffelsLoaded(ArrayList<Waffel> waffels) {

        // Set waffel adapter
        if (adapter == null) {
            this.waffels = waffels;
            adapter = new WaffelListAdapter(this, this.waffels, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            waffelList.setLayoutManager(mLayoutManager);
            waffelList.setItemAnimator(new DefaultItemAnimator());
            waffelList.setHasFixedSize(true);
            waffelList.setAdapter(adapter);
        } else if (isRequestingWaffels) {
            this.waffels.clear();
            this.waffels.addAll(waffels);
            adapter.notifyDataSetChanged();

            if (layoutSwipe.isRefreshing()) layoutSwipe.setRefreshing(false);
            isRequestingWaffels = false;
        }
    }

    @Override
    public void onUpvoteClicked(Waffel waffel) {
        // TODO: 24/08/2017 Upwaffel if possible
    }

    @Override
    public void onDownvoteClicked(Waffel waffel) {
        // TODO: 24/08/2017 Downwaffel if possible
    }

    @Override
    public void onRefresh() {
        reloadWaffels();
    }
}
