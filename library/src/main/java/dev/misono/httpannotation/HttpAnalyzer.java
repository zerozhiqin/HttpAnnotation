package dev.misono.httpannotation;

import android.content.Context;

/**
 * 实际使用需要自己实现HttpAnalyzer
 *
 * 原因是各项目使用的HTTP库不尽相同，示例中有使用android-async-http的简易版本
 */
public interface HttpAnalyzer {

    public String toJson(Object obj);

    public void workWith(Context ctx, HttpWorker httpWorker, HttpCallback callback);
}
