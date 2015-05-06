package dev.misono.sample.withasynchttp;


import android.content.Context;

import dev.misono.httpannotation.HttpCallback;
import dev.misono.httpannotation.annotation.Field;
import dev.misono.httpannotation.annotation.Format;
import dev.misono.httpannotation.annotation.Host;
import dev.misono.httpannotation.annotation.HttpMethod;
import dev.misono.httpannotation.annotation.TimeOut;
import dev.misono.httpannotation.annotation.URL;
import dev.misono.httpannotation.annotation.UrlEncoding;
import dev.misono.httpannotation.annotation.UserAgent;
import dev.misono.httpannotation.annotation.WithJson;

@Host("http://m.weather.com.cn")
public interface SampleInterface {


    @URL("/atad/{cityid}.html")
    @HttpMethod(HttpMethod.Type.POST)
    @UrlEncoding(false)
    @TimeOut(5000)
    @UserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0b8pre) Gecko/20101114 Firefox/4.0b8pre")
    public void getWeather(
            Context context,
            @Format("cityid") @WithJson(false) String cityid,
            @Field("time") @WithJson(false) String time,
            HttpCallback callback
    );

}
