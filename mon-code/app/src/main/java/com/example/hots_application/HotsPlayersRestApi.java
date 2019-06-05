package com.example.hots_application;

import com.example.hots_application.model.RestHotsPlayersReponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HotsPlayersRestApi {
    @GET("HotsPlayers/")
    Call<RestHotsPlayersReponse> getHotsPlayersList();
}
