package com.electricsheeps.myreception.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Sample

public interface ServerAPI {
    String SERVER_DOMEN = "https://shrouded-beyond-33870.herokuapp.com";

    @GET("/question")
    Single<Result>
    getMessages(@Query("userId") String userId);

    @GET("/treatment/severity")
    Single<State>
    getState(@Query("userId") String userId);

    @POST("/question/{id}/reply")
    Single<State>
    sendState(@Path("id") int id,
              @Body() SendState userId);

}
