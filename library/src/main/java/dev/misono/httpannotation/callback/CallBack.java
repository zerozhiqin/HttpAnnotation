package dev.misono.httpannotation.callback;

public abstract class CallBack<Success, Error> {

    Class<Success> clazzSuccess;
    Class<Error> clazzError;

    public CallBack(Class<Success> clazzSuccess, Class<Error> clazzError) {
        this.clazzSuccess = clazzSuccess;
        this.clazzError = clazzError;
    }

    public abstract void callback(Success success, Error error, Exception e);

}
