# HttpAnnotation
通过注解方法编写Http接口，
使用HttpAdapter创建接口后即可直接使用

```Java

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

```

调用后访问URL为

http://m.weather.com.cn/atad/xxxxxx.html?time=xxxxx

调用方法

```Java

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


```

其中AsyncHttpAnalyzer 是基于[android-async-http](http://loopj.com/android-async-http/)的简单实现

可以根据项目业务需求更换
