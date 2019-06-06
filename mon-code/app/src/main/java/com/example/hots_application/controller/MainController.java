package com.example.hots_application.controller;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.hots_application.HotsPlayersRestApi;
import com.example.hots_application.model.HotsPlayers;
import com.example.hots_application.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private MainActivity view;
    private HotsPlayersRestApi hotsPlayersRestApi;

    private SharedPreferences sharedPreferences;

    public MainController(MainActivity view,HotsPlayersRestApi hotsPlayersRestApii, SharedPreferences sharedPreferences) {
        this.view = view;
        this.hotsPlayersRestApi = hotsPlayersRestApi;
        this.sharedPreferences = sharedPreferences;
    }
    public void start() {
        Call<List<HotsPlayers>> call = hotsPlayersRestApi.getHotsPlayersList();
        call.enqueue(new Callback<List<HotsPlayers>>() {
            @Override
            public void onResponse(Call<List<HotsPlayers>> call, Response<List<HotsPlayers>> response) {
                if(response.isSuccessful()) {
                    List<HotsPlayers> listHotsPlayers = response.body();
                    List<HotsPlayers> hotsPlayersList = getDataFromCache();
                    view.showList(listHotsPlayers);
                } else {
                    System.out.println(response.errorBody());
                }
                Log.d("API ERROR 75", "onFailure");
            }

            @Override
            public void onFailure(Call<List<HotsPlayers>> call, Throwable t) {
                Log.d("API ERROR", "onFailure");
                List<HotsPlayers> hotsPlayersList = getDataFromCache();
                view.showList(hotsPlayersList);
            }
        });
    }
    private void storeData(List<HotsPlayers> pokemonList) {
        Gson gson = new Gson();
        String listPokemonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString("cle_string", listPokemonString)
                .apply();
    }

    private List<HotsPlayers> getDataFromCache() {
        String listPokemonString = sharedPreferences.getString("cle_string", "");
        if(listPokemonString != null && !TextUtils.isEmpty(listPokemonString)){
            Type listType = new TypeToken<List<HotsPlayers>>(){}.getType();
            List<HotsPlayers> pokemonList = new Gson().fromJson(listPokemonString, listType);
            return pokemonList;
        }
        return new ArrayList<>();
    }

}
