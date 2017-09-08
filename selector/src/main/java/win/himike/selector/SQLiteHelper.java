package win.himike.selector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import win.himike.selector.entity.City;

/**
 * Created by mike on 17-9-8.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private Context mContext;

    public SQLiteHelper(Context context) {
        super(context, "city", null, 1);
        this.mContext = context;
    }

    public SQLiteHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
//            db.beginTransaction();
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.citycode);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String sql;
            while ((sql = bufferedReader.readLine()) != null) {
                db.execSQL(sql);
            }
//            db.endTransaction();
//            db.setTransactionSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<City> queryCity(int cid) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("citycode", null, "parentId=?", new String[]{cid + ""}, null, null, null);
        ArrayList<City> cityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            City city = new City();
            city.setId(cursor.getInt(0));
            city.setLevel(cursor.getInt(1));
            city.setCid(cursor.getInt(2));
            city.setParentId(cursor.getInt(3));
            city.setName(cursor.getString(4));
            cityList.add(city);
        }
        cursor.close();
        return cityList;
    }

    public List<City> queryCities(int parentId) {
        ArrayList<City> cityList = new ArrayList<>();
        City city = queryCityByCid(parentId);
        while (city.getLevel() != 1) {
            cityList.add(city);
            city = queryCityByCid(city.getParentId());
        }
        cityList.add(city);
        return cityList;
    }

    private City queryCityByCid(int parentId) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("citycode", null, "cid=?", new String[]{parentId + ""}, null, null, null);

        City city = new City();
        if (cursor.moveToNext()) {
            city.setId(cursor.getInt(0));
            city.setLevel(cursor.getInt(1));
            city.setCid(cursor.getInt(2));
            city.setParentId(cursor.getInt(3));
            city.setName(cursor.getString(4));
        }
        cursor.close();
        return city;
    }
}
