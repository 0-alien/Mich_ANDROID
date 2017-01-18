package com.mich.android.mich.transport.responses;

import com.google.gson.Gson;

public class BaseResponse {
    public int code;
    public String message;
    public Object data;


    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
