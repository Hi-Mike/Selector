package win.himike.selector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import win.himike.selector.entity.City;

/**
 * Created by HiMike on 2017/9/3.
 */

public abstract class BaseRegionActivity extends AppCompatActivity implements CallBack {
    public static final int REQUEST_CITY = 233;
    public static final String CITY = "city";
    public static final String SELECTED = "selected";
    public static final String MAX_LEVEL = "level";

    private int level = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayout() != 0) {
            setContentView(getLayout());
        } else {
            setContentView(R.layout.activity_region);
        }

        if (getIntent().hasExtra(MAX_LEVEL)) {
            level = getIntent().getIntExtra(MAX_LEVEL, 3);
        }

        inflateData();
    }

    private void inflateData() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        ArrayList<City> cities = sqLiteHelper.queryCityList(1, level);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SelectorFragment.newInstance(cities, level))
                .commit();
    }

    protected int getLayout() {
        return 0;
    }
}
