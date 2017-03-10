package com.mich.android.mich.transport;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mich.android.mich.App;
import com.mich.android.mich.transport.requests.AcceptBattleRequest;
import com.mich.android.mich.transport.requests.AddCommentRequest;
import com.mich.android.mich.transport.requests.BaseAuthorizedRequest;
import com.mich.android.mich.transport.requests.ChangePasswordRequest;
import com.mich.android.mich.transport.requests.PostRequest;
import com.mich.android.mich.transport.requests.RegisterRequest;
import com.mich.android.mich.transport.requests.SearchRequest;
import com.mich.android.mich.transport.requests.UploadPostRequest;
import com.mich.android.mich.transport.requests.UserByIdRequest;
import com.mich.android.mich.transport.responses.BaseResponse;
import com.mich.android.mich.transport.requests.UsernameLoginRequest;
import com.mich.android.mich.transport.responses.BoolResponse;
import com.mich.android.mich.transport.responses.CommentResponse;
import com.mich.android.mich.transport.responses.LoginResponse;
import com.mich.android.mich.transport.responses.PostResponse;
import com.mich.android.mich.transport.responses.UserResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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


    public void doPost(final Context context , final String url, final Object request, final Type type, final DoPostCallback callBack ){


        new AsyncTask<Object,Void,String>(){

            @Override
            protected String doInBackground(Object... params) {
                Gson gson = new Gson();
                return gson.toJson(params[0]);
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



    // ========== Auth requests ==========

    public void register(Context context, String userName, String email,String password, String name, DoPostCallback<Void> callback){
        doPost(context, BASE_URL + "auth/register",new RegisterRequest(userName,email,password,name), Void.class, callback);
    }

    public void userNameLogin(Context context, String userName, String password, DoPostCallback<LoginResponse> callback){
        doPost(context, BASE_URL + "auth/login",new UsernameLoginRequest(userName,password), LoginResponse.class, callback);
    }

    public void logout(Context context, DoPostCallback<Void> callback){
        doPost(context, BASE_URL + "auth/logout", new BaseAuthorizedRequest(), Void.class, callback);
    }

    public void sendRecovery(Context context,String username, DoPostCallback<LoginResponse> callback){
        HashMap<String ,String> map = new HashMap<>();
        map.put("username",username);
        doPost(context, BASE_URL+ "auth/sendRecovery", map, LoginResponse.class, callback);
    }

    public void checkCode(Context context, String token, String code, DoPostCallback<Void> callback){
        HashMap<String ,String> map = new HashMap<>();
        map.put("token",token);
        map.put("code",code);
        doPost(context, BASE_URL+ "auth/checkCode", map, Void.class, callback);
    }

    public void recover(Context context,String token,String password, DoPostCallback<Void> callback){
        HashMap<String ,String> map = new HashMap<>();
        map.put("token",token);
        map.put("password",password);
        doPost(context, BASE_URL+ "auth/recover", map, Void.class, callback);
    }




    // ========== User requests ==========

    public void changePassword(Context context, String password, DoPostCallback<Void> callback){
        doPost(context,BASE_URL + "user/changePassword",new ChangePasswordRequest(password),Void.class, callback);
    }

    public void loadCurrentUserData(Context context, DoPostCallback<UserResponse> callback){
        doPost(context,BASE_URL+"user/get",new BaseAuthorizedRequest(),UserResponse.class, callback);
    }

    public void loadUserById(Context context,int userID, DoPostCallback<UserResponse> callback){
        doPost(context,BASE_URL+"user/get",new UserByIdRequest(userID), UserResponse.class, callback);
    }

    public void loadUserPosts(Context context,int userID, DoPostCallback<ArrayList<PostResponse>> callback){
        doPost(context,BASE_URL+"user/posts",new UserByIdRequest(userID), new TypeToken<ArrayList<PostResponse>>(){}.getType(), callback);
    }




    // ========== Relation requests ==========

    public void followUserByID(Context context,int userID, DoPostCallback<Void> callback){
        doPost(context,BASE_URL+"user/relation/follow",new UserByIdRequest(userID), Void.class, callback);
    }

    public void loadCurrentUserFollowers(Context context, DoPostCallback<ArrayList<UserResponse>> callback){
        doPost(context,BASE_URL+"user/relation/getFollowers",new BaseAuthorizedRequest(), new TypeToken<ArrayList<UserResponse>>(){}.getType(), callback);
    }

    public void loadCurrentUserFollowings(Context context, DoPostCallback<ArrayList<UserResponse>> callback){
        doPost(context,BASE_URL+"user/relation/getFollowing",new BaseAuthorizedRequest(), new TypeToken<ArrayList<UserResponse>>(){}.getType(), callback);
    }

    public void loadFollowersByUserId(Context context, int userID, DoPostCallback<ArrayList<UserResponse>> callback){
        doPost(context,BASE_URL+"user/relation/getFollowers",new UserByIdRequest(userID), new TypeToken<ArrayList<UserResponse>>(){}.getType(), callback);
    }

    public void loadFollowingsByUserId(Context context, int userID, DoPostCallback<ArrayList<UserResponse>> callback){
        doPost(context,BASE_URL+"user/relation/getFollowing",new UserByIdRequest(userID), new TypeToken<ArrayList<UserResponse>>(){}.getType(), callback);
    }

    public void isFollower(Context context, int userID, DoPostCallback<BoolResponse> callback){
        doPost(context,BASE_URL+"user/relation/isFollower",new UserByIdRequest(userID), BoolResponse.class, callback);
    }

    public void isFollowing(Context context, int userID, DoPostCallback<BoolResponse> callback){
        doPost(context,BASE_URL+"user/relation/isFollowing",new UserByIdRequest(userID), BoolResponse.class, callback);
    }

    public void unfollow(Context context, int userID, DoPostCallback<Void> callback){
        doPost(context,BASE_URL+"user/relation/unfollow",new UserByIdRequest(userID), Void.class, callback);
    }

    // ========== Post requests ==========


    public void addComment(Context context,int postID, String comment, DoPostCallback<Void> callback){
        doPost(context, BASE_URL+ "post/comment", new AddCommentRequest(postID, comment), Void.class, callback);
    }

    public void uploadPost(Context context,String title, String image, DoPostCallback<Void> callback){
        doPost(context, BASE_URL+ "post/create", new UploadPostRequest(title,image), Void.class, callback);
    }

    public void explore(Context context, DoPostCallback<ArrayList<PostResponse>> callback){
        doPost(context, BASE_URL+ "post/explore", new BaseAuthorizedRequest(), new TypeToken<ArrayList<PostResponse>>(){}.getType(), callback);
    }

    public void loadFeed(Context context, DoPostCallback<ArrayList<PostResponse>> callback){
        doPost(context, BASE_URL+ "post/feed", new BaseAuthorizedRequest(), new TypeToken<ArrayList<PostResponse>>(){}.getType(), callback);
    }

    public void getPost(Context context, int postID, DoPostCallback<PostResponse> callback){
        doPost(context, BASE_URL+ "post/get", new PostRequest(postID), PostResponse.class, callback);
    }

    public void getPostComments(Context context, int postID, DoPostCallback<ArrayList<CommentResponse>> callback){
        doPost(context, BASE_URL+ "post/comments", new PostRequest(postID), new TypeToken<ArrayList<CommentResponse>>(){}.getType(), callback);
    }

    public void like(Context context, int postID, DoPostCallback<Void> callback){
        doPost(context, BASE_URL+ "post/like", new PostRequest(postID), Void.class, callback);
    }

    public void unlike(Context context, int postID, DoPostCallback<Void> callback){
        doPost(context, BASE_URL+ "post/unlike", new PostRequest(postID), Void.class, callback);
    }

    // ========== Search requests ==========

    public void searchUsers(Context context,String term, DoPostCallback<ArrayList<UserResponse>> callback){
        doPost(context,BASE_URL+"search/users/",new SearchRequest(term), new TypeToken<ArrayList<UserResponse>>(){}.getType(), callback);
    }

    // ========== Battle requests ==========

    public void inviteUserIntoBattle(Context context,int userID, DoPostCallback<Void> callback){
        doPost(context,BASE_URL+"battle/invite",new UserByIdRequest(userID), Void.class, callback);
    }

    public void acceptBattle(Context context,int battleID, DoPostCallback<Void> callback){
        doPost(context,BASE_URL+"battle/accept",new AcceptBattleRequest(battleID), Void.class, callback);
    }




}
