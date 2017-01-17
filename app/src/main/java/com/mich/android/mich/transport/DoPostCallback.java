package com.mich.android.mich.transport;

/**
 * Created by sabagogolidze on 1/17/17.
 */

public interface DoPostCallback<T> {

    void onLoad(int code, String message, T data);

}
