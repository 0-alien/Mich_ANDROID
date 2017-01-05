package com.mich.android.mich.transport.requests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LoginRequest {

    LoginType loginType;

    public JsonObject toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return gson.fromJson(json,JsonObject.class);
    }

}
