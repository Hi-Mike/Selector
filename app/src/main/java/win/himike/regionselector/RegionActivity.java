package win.himike.regionselector;

import android.content.Intent;

import java.util.ArrayList;

import win.himike.selector.BaseRegionActivity;
import win.himike.selector.entity.City;

/**
 * Created by HiMike on 2017/9/3.
 */

public class RegionActivity extends BaseRegionActivity {
    @Override
    public void onSelect(ArrayList<City> cities) {
        setResult(RESULT_OK,new Intent().putParcelableArrayListExtra(SELECTED,cities));
        finish();
    }
}
