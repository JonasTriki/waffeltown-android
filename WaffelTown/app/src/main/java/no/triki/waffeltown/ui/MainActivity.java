package no.triki.waffeltown.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.triki.waffeltown.R;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_WAFFEL = 1337;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
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
}
