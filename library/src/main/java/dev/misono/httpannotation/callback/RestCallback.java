package dev.misono.httpannotation.callback;


public abstract class RestCallback<Result> {
    Class<Result> clazz;

    public RestCallback(Class<Result> clazz) {
        this.clazz = clazz;
    }

    public abstract void callback(Result result, Exception e);
}
