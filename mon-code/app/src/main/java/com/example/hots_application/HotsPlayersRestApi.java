package com.example.hots_application;

import com.example.hots_application.model.HotsPlayers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HotsPlayersRestApi {
    @GET("heroes/")
    Call<List<HotsPlayers>> getHotsPlayersList();
}
