package dev.misono.httpannotation;

import android.content.Context;

public interface HttpAnalyzer {

    public String toJson(Object obj);

    public void workWith(Context ctx, HttpWorker httpWorker, HttpCallback callback);
}
