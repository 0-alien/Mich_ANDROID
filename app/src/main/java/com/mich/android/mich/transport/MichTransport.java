package com.mich.android.mich.transport;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mich.android.mich.App;
import com.mich.android.mich.transport.requests.BaseAuthorizedRequest;
import com.mich.android.mich.transport.requests.RegisterRequest;
import com.mich.android.mich.transport.requests.Request;
import com.mich.android.mich.transport.requests.UploadPostRequest;
import com.mich.android.mich.transport.responses.BaseResponse;
import com.mich.android.mich.transport.requests.UsernameLoginRequest;
import com.mich.android.mich.transport.responses.LoginResponse;
import com.mich.android.mich.transport.responses.PostResponse;
import com.mich.android.mich.transport.responses.UserDataResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MichTransport {

    private static MichTransport instance;
    private static final String BASE_URL = "http://46.101.196.48/public/index.php/api/";
    public static final int LOAD_ERROR_NO_RESULT = -1;
    public static final int LOAD_SUCCESS = 10;


    public static MichTransport getInstance(){
        if(instance == null){
            instance = new MichTransport();
        }
        return instance;
    }

    private MichTransport(){

    }


    public void doPost(final Context context , final String url, final Request request, final Type type, final DoPostCallback callBack ){


        new AsyncTask<Request,Void,String>(){

            @Override
            protected String doInBackground(Request... params) {
                return params[0].toJson();
            }

            @Override
            protected void onPostExecute(String requestToJson) {
                super.onPostExecute(requestToJson);

                Log.d("REQUEST", url + "\n" + requestToJson);
                Ion.with(context).
                        load(url).
                        setHeader("X-Mashape-Key","9cmTk6a4R5mshxwFT8LXGrOwhSk1p1ngAIMjsnvr9Z9dIoeDHT").
                        setHeader("Content-Type","application/x-www-form-urlencoded").
                        setHeader("Accept","application/json").
                        setTimeout(3600000).
                        setStringBody(requestToJson).
                        as(BaseResponse.class).
                        setCallback(new FutureCallback<BaseResponse>() {
                            @Override
                            public void onCompleted(Exception e, BaseResponse result) {
                                Log.d("RESPONSE_CAME_FROM", url + "\n" + result);
                                if(e != null){
                                    Log.d("RESPONSE_ERROR_MESSAGE",url + "\n" + e.getMessage());
                                }
                                if( result != null){
                                    Log.d("RESPONSE_OBJECT", url + "\n" + result.toJson());
                                    Gson gson = new Gson();
                                    Object o;
                                    try {
                                        String json = gson.toJson(result.data);
                                        o = gson.fromJson(json, type);
                                    }catch (Exception ex){
                                        o = null;
                                    }
                                    callBack.onLoad(result.code,result.message,o);
                                } else {
                                    callBack.onLoad(LOAD_ERROR_NO_RESULT,null,null);
                                }
                            }
                        });


            }
        }.execute(request);


    }


    public void register(Context context, String userName, String email,String password, DoPostCallback<Void> callback){
        doPost(context, BASE_URL + "auth/register",new RegisterRequest(userName,email,password), Void.class, callback);
    }

    public void userNameLogin(Context context, String userName, String password, DoPostCallback<LoginResponse> callback){
        doPost(context, BASE_URL + "auth/login",new UsernameLoginRequest(userName,password), LoginResponse.class, callback);
    }

    public void loadUserData(Context context,DoPostCallback<UserDataResponse> callback){
        doPost(context,BASE_URL+"user/get",new BaseAuthorizedRequest(),UserDataResponse.class,callback);
    }


    public void loadPosts(Context context, DoPostCallback<ArrayList<PostResponse>> callback){
        doPost(context, BASE_URL+ "post/feed", new BaseAuthorizedRequest(App.getInstance().getLoginToken()), new TypeToken<ArrayList<PostResponse>>(){}.getType(), callback);
    }

    public void uploadPost(Context context,String title, String image, DoPostCallback<Void> callback){
        doPost(context, BASE_URL+ "post/create", new UploadPostRequest(title,image), Void.class, callback);
    }



}
