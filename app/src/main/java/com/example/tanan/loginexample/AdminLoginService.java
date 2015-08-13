package com.example.tanan.loginexample;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by tanan on 30/7/15.
 */
public interface AdminLoginService {
    @POST("/api/sessions")
    void signInAdmin(@Body UserObject userObject, Callback<Object> cb);
}
