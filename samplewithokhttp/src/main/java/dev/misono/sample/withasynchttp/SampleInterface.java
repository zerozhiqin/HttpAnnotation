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

@Host("http://m.weather.com.cn")            // 接口访问的host地址
public interface SampleInterface {


    @URL("/atad/{cityid}.html")             // 接口Url
    @HttpMethod(HttpMethod.Type.POST)       // 访问方式
    @UrlEncoding(false)                     // 是否使用UrlEncoding
    @TimeOut(5000)                          // 超时时间
    @UserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0b8pre) Gecko/20101114 Firefox/4.0b8pre")
    public void getWeather(
            Context context,                //
            @Format("cityid") @WithJson(false) String cityid,       // Format注解会将内容替换
                                                                    // URL中的{cityid}

            @Field("time") @WithJson(false) String time,            // Field注解为传入参数

            HttpCallback callback           // 访问回调
    );

}
