package com.example.hots_application.controller;

import android.util.Log;

import com.example.hots_application.HotsPlayersRestApi;
import com.example.hots_application.model.HotsPlayers;
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
        Call<List<HotsPlayers>> call = hotsPlayersRestApi.getHotsPlayersList();
        call.enqueue(new Callback<List<HotsPlayers>>() {
            @Override
            public void onResponse(Call<List<HotsPlayers>> call, Response<List<HotsPlayers>> response) {
                if(response.isSuccessful()) {
                    List<HotsPlayers> listHotsPlayers = response.body();

                    view.showList(listHotsPlayers);
                } else {
                    System.out.println(response.errorBody());
                }
                Log.d("API ERROR 75", "onFailure");
            }

            @Override
            public void onFailure(Call<List<HotsPlayers>> call, Throwable t) {
                Log.d("API ERROR", "onFailure");
            }
        });
    }
}
