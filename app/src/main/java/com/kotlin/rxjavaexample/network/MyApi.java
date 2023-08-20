package com.kotlin.rxjavaexample.network;

import com.kotlin.rxjavaexample.data.remote.Response;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    @GET("filter.php")
    Observable<Response> getFoodList(@Query("a") String a);
}
