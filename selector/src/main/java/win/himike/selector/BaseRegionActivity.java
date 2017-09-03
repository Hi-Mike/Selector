package win.himike.selector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import win.himike.selector.entity.City;

/**
 * Created by HiMike on 2017/9/3.
 */

public abstract class BaseRegionActivity extends AppCompatActivity implements CallBack {
    public static final int REQUEST_CITY = 233;
    public static final String CITY = "city";
    public static final String SELECTED = "selected";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayout() != 0) {
            setContentView(getLayout());
        } else {
            setContentView(R.layout.activity_region);
        }

        inflateData();
    }

    private void inflateData() {
        InputStream inputStream = getResources().openRawResource(R.raw.city);
        final ArrayList<City> cities = new Gson().fromJson(new InputStreamReader(inputStream), new TypeToken<ArrayList<City>>() {
        }.getType());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SelectorFragment.newInstance(cities, new ArrayList<City>()))
                .commit();
    }

    protected int getLayout() {
        return 0;
    }
}
