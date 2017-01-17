package com.mich.android.mich.transport;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mich.android.mich.transport.requests.BaseResponse;
import com.mich.android.mich.transport.requests.UsernameLoginRequest;
import com.mich.android.mich.transport.responses.LoginResponse;
import com.mich.android.mich.transport.responses.RegisterResponse;

public class MichTransport {

    private static MichTransport instance;
    private static final String BASE_URL = "https://0-alien-mich-v1.p.mashape.com/";
    public static final int LOAD_ERROR_NO_RESULT = -1;


    public static MichTransport getInstance(){
        if(instance == null){
            instance = new MichTransport();
        }
        return instance;
    }

    private MichTransport(){

    }


    public void doPost(Context context ,String url, final Request request, final Class<?> type, final DoPostCallback callBack ){
        Log.d("REQUEST",request.toJson());
        Ion.with(context).
                load(url).
                setHeader("X-Mashape-Key","9cmTk6a4R5mshxwFT8LXGrOwhSk1p1ngAIMjsnvr9Z9dIoeDHT").setBodyParameter("payload",request.toJson()).
                asString().
                setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        Log.d("RESPONSE",e + "");
                        Log.d("RESPONSE",result + "");
                        if(e != null)Log.d("RESPONSE",e.getMessage());
                        if( result != null){
//                            Log.d("RESPONSE",result.toJson());
                            Gson gson = new Gson();
                            Object o = null;
                            try {
//                                o = gson.fromJson(result.data, type);
                            }catch (Exception ex){
                                o = null;
                            }
//                            callBack.onLoad(result.code,result.message,o);
                        } else {
                            callBack.onLoad(LOAD_ERROR_NO_RESULT,null,null);
                        }
                    }
                });
    }

    public void registerUser(Context context, FutureCallback<RegisterResponse> callBack, String username){
        Ion.with(context).
                load(BASE_URL + "/რეგისტრაციის მისამართიიიიიიიიი").
                as(RegisterResponse.class).
                setCallback(callBack);
    }

    public void userNameLogin(Context context, String userName, String password, DoPostCallback<LoginResponse> callback){
        doPost(context, BASE_URL + "auth/login",new UsernameLoginRequest(userName,password), LoginResponse.class, callback);
    }



}
