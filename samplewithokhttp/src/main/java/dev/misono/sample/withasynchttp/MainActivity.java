package dev.misono.sample.withasynchttp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import dev.misono.httpannotation.HttpAdapter;
import dev.misono.httpannotation.HttpCallback;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AsyncHttpAnalyzer httpAnalyzer = new AsyncHttpAnalyzer();
        SampleInterface sampleInterface = HttpAdapter.with(httpAnalyzer).create(SampleInterface.class);

        sampleInterface.getWeather(this,
                "101190101",
                "20101101",
                new HttpCallback() {
                    @Override
                    public void callback(String result, Exception e) {
                        if (e == null) {
                            Log.v("Result", result);
                        } else {
                            Log.e("Error", e.getLocalizedMessage());
                        }
                    }
                });


    }

}
