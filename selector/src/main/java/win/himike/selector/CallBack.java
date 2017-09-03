package win.himike.selector;

import java.util.ArrayList;

import win.himike.selector.entity.City;

/**
 * Created by HiMike on 2017/9/3.
 */

public interface CallBack {
    void onSelect(ArrayList<City> cities);
}
