package win.himike.regionselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import win.himike.selector.entity.City;

import static win.himike.selector.BaseRegionActivity.MAX_LEVEL;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv_select);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, RegionActivity.class).putExtra(MAX_LEVEL, 2), RegionActivity.REQUEST_CITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            City city = data.getParcelableExtra(RegionActivity.SELECTED);
            mTextView.setText(city.getName());
        }
    }
}
