package com.mich.android.mich.transport.requests;

import com.google.gson.Gson;

public class Request {

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
