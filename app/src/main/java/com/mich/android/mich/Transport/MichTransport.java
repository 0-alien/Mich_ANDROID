package com.mich.android.mich.Transport;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mich.android.mich.Transport.Responses.RegisterResponse;

public class MichTransport {

    private static MichTransport instance;
    private static final String BASE_URL = "აქ უნდა იყოს url იიიიიიიიი";

    public static MichTransport getInstance(){
        if(instance == null){
            instance = new MichTransport();
        }
        return instance;
    }

    private MichTransport(){

    }

    public void registerUser(Context context, FutureCallback<RegisterResponse> callBack, String username){
        Ion.with(context).
                load(BASE_URL + "/რეგისტრაციის მისამართიიიიიიიიი").
                as(RegisterResponse.class).
                setCallback(callBack);
    }

}
