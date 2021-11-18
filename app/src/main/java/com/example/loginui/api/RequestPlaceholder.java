package com.example.loginui.api;

import com.example.loginui.pojos.Login;
import com.example.loginui.pojos.Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestPlaceholder {
    @POST("login")
    Call<Login> login(@Body Login login);

    @GET("get-all-orders")
    Call<List<Orders>> getAllOrders(@Query("t") String t, @Query("U") String u);
}
