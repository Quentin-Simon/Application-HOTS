package com.example.hots_application.controller;

import android.util.Log;

import com.example.hots_application.HotsPlayersRestApi;
import com.example.hots_application.model.HotsPlayers;
import com.example.hots_application.model.RestHotsPlayersReponse;
import com.example.hots_application.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private MainActivity view;
    private HotsPlayersRestApi hotsPlayersRestApi;

    public MainController(MainActivity view, HotsPlayersRestApi hotsPlayersRestApi) {
        this.view = view;
        this.hotsPlayersRestApi = hotsPlayersRestApi;
    }
    public void start() {
        Call<RestHotsPlayersReponse> call = hotsPlayersRestApi.getHotsPlayersList();
        call.enqueue(new Callback<RestHotsPlayersReponse>() {
            @Override
            public void onResponse(Call<RestHotsPlayersReponse> call, Response<RestHotsPlayersReponse> response) {
                if(response.isSuccessful()) {
                    RestHotsPlayersReponse restHotsPlayersReponse = response.body();
                    List<HotsPlayers> pokemonList = restHotsPlayersReponse.getResults();
                    view.showList(pokemonList);
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RestHotsPlayersReponse> call, Throwable t) {
                Log.d("API ERROR", "onFailure");
            }
        });
    }
}
