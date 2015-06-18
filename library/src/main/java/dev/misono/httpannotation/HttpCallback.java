package dev.misono.httpannotation;

public interface HttpCallback {
	// call it back
    public void callback(String result, Exception e);
}
